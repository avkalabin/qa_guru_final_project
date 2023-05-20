package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        //"classpath:${env}.properties",
        "classpath:credentials.properties"

})

public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browser_size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browser_version")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("baseUrl")
    @DefaultValue("https://allure.autotests.cloud")
    String getBaseUrl();

    @Key("baseUri")
    @DefaultValue("https://allure.autotests.cloud")
    String getBaseUri();

    @Key("remoteUrl")
    String getRemoteUrl();

    String username();
    String password();

}
