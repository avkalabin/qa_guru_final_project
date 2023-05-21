package web.testops.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestCasesPage {

    private final SelenideElement TESTCASE_NAME = $(".TestCaseLayout__name"),
            TESTCASE_ACTIONS_BUTTON = $(".Menu__trigger"),
            RENAME_TESTCASE_MENU_BUTTON = $("div:nth-child(1) .Menu__item > span"),
            TESTCASE_NAME_INPUT = $(".FormLabel input[name='name']"),
            RENAME_TESTCASE_SUBMIT_BUTTON = $(".Button_size_base:nth-child(2)"),
            EDIT_SCENARIO_SECTION_BUTTON = $("[data-testid='section__scenario'] button"),
            STEP_EDIT_ACTIONS_BUTTON = $(".TestCaseScenarioStepEdit__wrapper [name = 'Step menu']"),
            ATTACH_TEXT_BUTTON = $(byTagAndText("span", "Attach text")),
            ATTACH_TEXT_TEXTAREA = $(".FormLabel [name = 'content']"),
            ADD_ATTACH_SUBMIT_BUTTON = $(".Form__controls:nth-child(3) .Button_style_primary > span"),
            EDIT_SCENARIO_SUBMIT_BUTTON = $(".Editable Button[type = 'submit']"),
            SCENARIO_SECTION = $(".Scenario");

    private final ElementsCollection TESTCASE_SCENARIO = $$(".Editable");

    public TestCasesPage verifyTestCaseName(String testCaseName) {
        TESTCASE_NAME.shouldHave(text(testCaseName));
        return this;
    }

    public TestCasesPage clickRenameTestCase() {
        TESTCASE_ACTIONS_BUTTON.click();
        RENAME_TESTCASE_MENU_BUTTON.click();
        return this;
    }

    public void editTestCaseName(String text) {
        TESTCASE_NAME_INPUT.setValue(text);
        RENAME_TESTCASE_SUBMIT_BUTTON.click();
    }

    public void verifyStepsCreation() {
        TESTCASE_SCENARIO.shouldHave(sizeGreaterThanOrEqual(1));
    }

    public TestCasesPage editStepsMenuClick() {
        EDIT_SCENARIO_SECTION_BUTTON.click();
        STEP_EDIT_ACTIONS_BUTTON.click();
        ATTACH_TEXT_BUTTON.click();
        return this;
    }

    public void attachTextToStep(String attachedText) {
        ATTACH_TEXT_TEXTAREA.setValue(attachedText);
        ADD_ATTACH_SUBMIT_BUTTON.click();
        EDIT_SCENARIO_SUBMIT_BUTTON.click();
    }

    public void verifyAddedAttach(String attachedText) {
        SCENARIO_SECTION.shouldHave(text(attachedText));
    }

}

