package web.testops.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import web.testops.helpers.CookieAuth;
import web.testops.pages.TestPlansPage;

import static io.qameta.allure.Allure.step;

@TestMethodOrder(OrderAnnotation.class)
public class TestPlansTests extends TestBase {

    CookieAuth cookieAuth = new CookieAuth();
    TestPlansPage testPlansPage = new TestPlansPage();
    Faker faker = new Faker();
    String testPlanName = faker.name().nameWithMiddle();

    @Order(1)
    @DisplayName("Проверка создания тест плана")
    @Test
    void createTestPlan() {

        step("Авторизация", () -> cookieAuth.authWithCookie());

        step("Открываем страницу \"Test plans\"", () -> {
            testPlansPage.openTestPlansPage();
        });

        step("Создаем новый тест план", () -> {
            testPlansPage.clickTestPlanLink()
                    .setTestPlanName(testPlanName)
                    .clickNextButton()
                    .clickCreateTestPlan();
        });

        step("Проверяем, что в списке есть созданный тест план", () -> {
            testPlansPage.verifyTestPlanCreation(testPlanName);
        });
    }

    @Order(2)
    @DisplayName("Проверка удаления тест плана")
    @Test
    void deleteTestPlan() {

        step("Авторизация", () -> cookieAuth.authWithCookie());

        step("Открываем страницу \"Test plans\"", () -> {
            testPlansPage.openTestPlansPage();
        });

        step("Удаляем тест план", () -> {
            testPlansPage.clickTestPlanMenuDelete()
                    .confirmTestPlanDeletion();
        });

        step("Проверяем, страница не содержит созданных тест планов", () -> {
            testPlansPage.verifyTestPlanColumnHaveText("No test plans found");
        });
    }
}
