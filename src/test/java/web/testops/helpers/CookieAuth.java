package web.testops.helpers;

import org.openqa.selenium.Cookie;
import web.testops.authorization.AuthorizationApi;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class CookieAuth {

    final static public String PROJECT_ID = "2261";

    public CookieAuth authWithCookie() {
        open("/favicon.ico");

        String cookies = AuthorizationApi.getAuthorizationCookie();
        Cookie authorizationCookie = new Cookie("ALLURE_TESTOPS_SESSION", cookies);
        getWebDriver().manage().addCookie(authorizationCookie);
        return this;
    }
    public void openTestCaseUrl() {
        Integer testCaseId = TestCaseManager.createTestCaseResponse.getId();
        String testCaseUrl = format("/project/%s/test-cases/%s", PROJECT_ID, testCaseId);
        open(testCaseUrl);
    }

}
