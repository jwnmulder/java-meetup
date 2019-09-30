import jenkins.model.Jenkins

println("--- Configuring Locale")
def localePlugin = Jenkins.instance.getPlugin("locale")
localePlugin.systemLocale = "en_US"
localePlugin.@ignoreAcceptLanguage=true
