package config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credentials.properties"

})

public interface WebDriverConfig extends Config {

    String username();
    String password();

}
