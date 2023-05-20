package web.testops.tests;

import org.junit.jupiter.api.Test;
import web.testops.pages.LoginPage;

import static io.qameta.allure.Allure.step;

public class LoginTests extends TestBase {
    LoginPage loginPage = new LoginPage();

    @Test
    void loginTest() {

        step("Authorize", () -> {

            loginPage.openLoginPage()
                    .setUsername(webDriverConfig.username())
                    .setPassword(webDriverConfig.password())
                    .clickSubmitButton();
        });

        step("Verify authorization success", () -> {
            loginPage.verifyMainPageHaveText("Projects");
        });
    }
}
