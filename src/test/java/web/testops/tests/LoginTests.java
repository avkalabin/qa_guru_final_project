package web.testops.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import web.testops.config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import web.testops.pages.LoginPage;

import static io.qameta.allure.Allure.step;

@DisplayName("Авторизация пользователя")
@Owner("avkalabin")
@Feature("UI тесты allure.autotests.cloud")
@Story("Авторизация пользователя")
@Tag("web")
public class LoginTests extends TestBase {

    LoginPage loginPage = new LoginPage();
    WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @DisplayName("Проверка авторизации пользователя")
    @Test
    @Severity(SeverityLevel.BLOCKER)
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
