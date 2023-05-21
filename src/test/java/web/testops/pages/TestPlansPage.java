package web.testops.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static web.testops.helpers.CookieAuth.PROJECT_ID;

public class TestPlansPage {

    private final SelenideElement CREATE_TEST_PLAN_LINK = $(byLinkText("Create new test plan")),
            TEST_PLAN_NAME_INPUT = $("input[name = 'name']"),
            NEXT_BUTTON = $(byTagAndText("span", "Next")),
            CREATE_TEST_PLAN_BUTTON = $(".TestPlanEdit__controls")
                    .$(byTagAndText("span", "Create test plan")),
            TEST_PLANS_HEADER = $(".TestPlanHeader"),
            TEST_PLAN_MENU = $(".Menu__trigger"),
            TEST_PLAN_MENU_DELETE = $(".Menu__item_danger").$(byTagAndText("span", "Delete")),
            DELETE_TEST_PLAN_BUTTON = $(".Button_style_danger").$(byTagAndText("span", "Delete")),
            TEST_PLAN_COLUMN = $(".ColumnHeaders");

    String testPlansUrl = format("/project/%s/testplans", PROJECT_ID);

    public void openTestPlansPage() {
        open(testPlansUrl);
    }

    public TestPlansPage clickTestPlanLink() {
        CREATE_TEST_PLAN_LINK.click();
        return this;
    }

    public TestPlansPage setTestPlanName(String testPlanName) {
        TEST_PLAN_NAME_INPUT.setValue(testPlanName);
        return this;
    }

    public TestPlansPage clickNextButton() {
        NEXT_BUTTON.click();
        return this;
    }

    public void clickCreateTestPlan() {
        CREATE_TEST_PLAN_BUTTON.click();
    }

    public void verifyTestPlanCreation(String testPlanName) {
        TEST_PLANS_HEADER.shouldHave(text(testPlanName));
    }

    public TestPlansPage clickTestPlanMenuDelete() {
        TEST_PLAN_MENU.click();
        TEST_PLAN_MENU_DELETE.click();
        return this;
    }

    public void confirmTestPlanDeletion() {
        DELETE_TEST_PLAN_BUTTON.click();
    }

    public void verifyTestPlanColumnHaveText(String text) {
        TEST_PLAN_COLUMN.shouldHave(text(text));
    }

}


