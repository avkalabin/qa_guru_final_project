package web.testops.tests;

import web.testops.helpers.CookieAuth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.testops.helpers.TestCaseManager;
import web.testops.pages.TestCasesPage;

import static io.qameta.allure.Allure.step;

public class TestCasesTests extends TestBase {
    TestCaseManager testCaseManager = new TestCaseManager();
    TestCasesPage testCasesPage = new TestCasesPage();
    CookieAuth cookieAuth = new CookieAuth();


    @DisplayName("Проверка имени созданного тест-кейса")
    @Test
    void verifyCreationTestCaseTest() {

        testCaseManager.createTestCase();
        testCaseManager.addSteps();

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Проверка имени созданного тест-кейса", () -> {

            testCasesPage.verifyTestCaseName(testCaseManager.testCaseName);
        });

        testCaseManager.deleteTestCase();
    }

    @DisplayName("Проверка редактирования имени тест-кейса")
    @Test
    void editTestCaseTest() {

        testCaseManager.createTestCase();
        testCaseManager.addSteps();

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Редактирование имени тест-кейса", () -> {

            testCasesPage.verifyTestCaseName(testCaseManager.testCaseName)
                    .clickRenameTestCase()
                    .editTestCaseName();
        });

        step("Проверка имени отредактированного тест-кейса", () -> {

            testCasesPage.verifyTestCaseName(testCaseManager.testCaseName + " edited");
        });

        testCaseManager.deleteTestCase();
    }


    @DisplayName("Проверка создания шагов тест-кейса")
    @Test
    void verifyCreationStepsTest() {

        testCaseManager.createTestCase();
        testCaseManager.addSteps();

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Проверка создания шагов тест-кейса", () -> {

            testCasesPage.verifyStepsCreation();
        });

        testCaseManager.deleteTestCase();

    }

    @DisplayName("Проверка добавления аттача к шагам")
    @Test
    void editStepsAttachTest() {

        testCaseManager.createTestCase();
        testCaseManager.addSteps();

        step("Авторизация", () -> cookieAuth.authWithCookie()
                .openTestCaseUrl());

        step("Добавление аттача", () -> {

            testCasesPage.verifyTestCaseName(testCaseManager.testCaseName)
                    .editStepsMenuClick()
                    .attachTextToStep("Attached text");
        });

        step("Проверка добавленного аттача", () -> {

            testCasesPage.verifyAddedAttach("Attached text");
        });

        testCaseManager.deleteTestCase();
    }


}
