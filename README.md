Start jenkins
```
docker-compose up -V --build
docker-compose --compatibility up -V --build
```

Test connectivity to Kubernetes (e.g. Docker for Desktop)
```
kubectl --context=docker-for-desktop cluster-info

# Install some dashboards (http://localhost:4654)
kubectl --context=docker-for-desktop apply -f https://raw.githubusercontent.com/herbrandson/k8dash/master/kubernetes-k8dash-nodeport.yaml


```

```
Relevant log files for docker-on-desktop
tail -f /c/Users/jan-willem/AppData/Local/Docker/log.txt
rm -rf /c/ProgramData/DockerDesktop/pki
```

Jenkins configuration
See http://localhost:8080/configuration-as-code

Jenkins Job DSL docs: https://jenkinsci.github.io/job-dsl-plugin