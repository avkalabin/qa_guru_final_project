package web.testops.tests;

import config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import web.testops.pages.LoginPage;

import static io.qameta.allure.Allure.step;

public class LoginTests extends TestBase {
    LoginPage loginPage = new LoginPage();
    WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @Test
    void loginTest() {

        step("Вводим имя пользователя и пароль", () -> {

            loginPage.openLoginPage()
                    .setUsername(webDriverConfig.username())
                    .setPassword(webDriverConfig.password());
        });

        step("Нажимаем кнопку \"Continue\"", () -> {

            loginPage.clickSubmitButton();
        });

        step("Проверяем, что открылась главная страница с Projects", () -> {

            loginPage.verifyMainPageHaveText("Projects");
        });
    }
}
