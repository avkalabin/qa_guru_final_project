package web.testops.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import web.testops.helpers.CookieAuth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.testops.helpers.TestCaseLifecycleExtension;
import web.testops.pages.TestCasesPage;

import static io.qameta.allure.Allure.step;
import static web.testops.helpers.TestCaseLifecycleExtension.testCaseName;

@DisplayName("Работа с тест кейсами")
@Owner("avkalabin")
@Feature("UI тесты allure.autotests.cloud")
@Story("Работа с тест кейсами")
@Tag("web")
@ExtendWith(TestCaseLifecycleExtension.class)
public class TestCasesTests extends TestBase {

    TestCasesPage testCasesPage = new TestCasesPage();
    CookieAuth cookieAuth = new CookieAuth();

    @DisplayName("Проверка имени созданного тест-кейса")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    void verifyCreationTestCaseTest() {

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Проверка имени созданного тест-кейса", () -> {
            testCasesPage.verifyTestCaseName(testCaseName);
        });
    }

    @DisplayName("Проверка редактирования имени тест-кейса")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    void editTestCaseTest() {

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Редактирование имени тест-кейса", () -> {
            testCasesPage.verifyTestCaseName(testCaseName)
                    .clickRenameTestCase()
                    .editTestCaseName(" edited");
        });

        step("Проверка имени отредактированного тест-кейса", () -> {
            testCasesPage.verifyTestCaseName(testCaseName + " edited");
        });
    }


    @DisplayName("Проверка создания шагов тест-кейса")
    @Test
    @Severity(SeverityLevel.NORMAL)
    void verifyCreationStepsTest() {
        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Проверка создания шагов тест-кейса", () -> {
            testCasesPage.verifyStepsCreation();
        });
    }

    @DisplayName("Проверка добавления аттача к шагам")
    @ValueSource(strings = {"Attached First", "Attached Second"})
    @ParameterizedTest(name = "{0}")
    @Severity(SeverityLevel.NORMAL)
    void editStepsAttachTest(String attached) {

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Добавление аттача", () -> {
            testCasesPage.verifyTestCaseName(testCaseName)
                    .editStepsMenuClick()
                    .attachTextToStep(attached);
        });

        step("Проверка добавленного аттача", () -> {
            testCasesPage.verifyAddedAttach(attached);
        });
    }

}
