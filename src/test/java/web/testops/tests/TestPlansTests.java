package web.testops.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import web.testops.helpers.CookieAuth;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static web.testops.helpers.CookieAuth.PROJECT_ID;

@TestMethodOrder(OrderAnnotation.class)
public class TestPlansTests extends TestBase {

    CookieAuth cookieAuth = new CookieAuth();
    String testPlansUrl = format("/project/%s/testplans", PROJECT_ID);

    Faker faker = new Faker();
    String testPlanName = faker.name().nameWithMiddle();

    @Order(1)
    @Test
    void createTestPlan() {

        step("Авторизация", () -> cookieAuth.authWithCookie());

        step("Открываем страницу \"Test plans\"", () -> {
            open(testPlansUrl);
        });


        step("Создаем новый тест план", () -> {

            $(byLinkText("Create new test plan")).click();
            $("input[name = 'name']").setValue(testPlanName);
            $(byTagAndText("span", "Next")).click();
            $(".TestPlanEdit__controls").$(byTagAndText("span", "Create test plan")).click();
        });

        step("Проверяем, что в списке есть созданный тест план", () -> {

            $(".TestPlanHeader").shouldHave(text(testPlanName));
        });
    }

    @Order(2)
    @Test
    void deleteTestPlan() {

        step("Авторизация", () -> cookieAuth.authWithCookie());

        step("Открываем страницу \"Test plans\"", () -> {
            open(testPlansUrl);
        });

        step("Удаляем тест план", () -> {
            $(".Menu__trigger").click();
            $(".Menu__item_danger").$(byTagAndText("span", "Delete")).click();
            $(".Button_style_danger").$(byTagAndText("span", "Delete")).click();
        });

        step("Проверяем, страница не содержит созданных тест планов", () -> {
            $(".ColumnHeaders").shouldHave(text("No test plans found"));
        });
    }
}
