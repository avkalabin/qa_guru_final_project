package web.testops.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement USERNAME = $(byName("username")),
            PASSWORD = $(byName("password")),
            SUBMIT_BUTTON = $("button[type='submit']"),
            MAIN_PAGE_LAYOUT = $(".BasicLayout");

    public LoginPage openLoginPage() {

        open("/login");
        return this;
    }

    public LoginPage setUsername(String username) {
        USERNAME.setValue(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        PASSWORD.setValue(password);
        return this;
    }

    public void clickSubmitButton() {
        SUBMIT_BUTTON.click();
    }

    public void verifyMainPageHaveText(String text) {
        MAIN_PAGE_LAYOUT.shouldHave(text(text));
    }
}
