#!/usr/bin/env bash
set -ex

KUBE_HOST_IP=kubernetes.docker.internal
KUBECTL_CMD='kubectl --context=docker-for-desktop'

# K8-Dash
$KUBECTL_CMD apply -f https://raw.githubusercontent.com/herbrandson/k8dash/master/kubernetes-k8dash-nodeport.yaml
$KUBECTL_CMD rollout status -n kube-system --watch deployment k8dash

K8DASH_NODEPORT=$($KUBECTL_CMD get -n kube-system svc k8dash -o=jsonpath='{.spec.ports[?(@.port==4654)].nodePort}')
K8DASH_URL=http://$KUBE_HOST_IP:$K8DASH_NODEPORT

# K8-Dash tokens
kubectl create serviceaccount k8dash-sa || true
kubectl create clusterrolebinding k8dash-sa --clusterrole=cluster-admin --serviceaccount=default:k8dash-sa || true

# Kubernetes Dashboard
$KUBECTL_CMD apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.0/src/deploy/recommended/kubernetes-dashboard.yaml
$KUBECTL_CMD rollout status -n kube-system --watch deployment kubernetes-dashboard
KUBERNETES_DASHBOARD_URL=http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/#!/overview?namespace=default

set +x

# Print useful information
echo ""
$KUBECTL_CMD cluster-info
echo ""

echo "kubernetes dashboard is running at $KUBERNETES_DASHBOARD_URL"
echo "k8-dash is running at $K8DASH_URL"
echo "k8-dash $(kubectl describe secret k8dash-sa-token | grep token:)"

# Start proxy
echo ""
$KUBECTL_CMD proxy
