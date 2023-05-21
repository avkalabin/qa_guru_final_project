package web.testops.tests;

import com.codeborne.selenide.Configuration;
import web.testops.helpers.Attach;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static io.qameta.allure.Allure.step;

public class TestBase {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("api_uri", "https://allure.autotests.cloud");
        Configuration.baseUrl = System.getProperty("base_url", "https://allure.autotests.cloud");
        Configuration.remote = "https://user1:1234@" + System.getProperty("selenoid_url", "selenoid.autotests.cloud/wd/hub");
        Configuration.browserSize = System.getProperty("browser_size", "1920x800");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version", "100.0");
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLog();
        Attach.addVideo();
        step("Очищаем cookies", () -> {
            clearBrowserCookies();
            clearBrowserLocalStorage();
        });
    }

}