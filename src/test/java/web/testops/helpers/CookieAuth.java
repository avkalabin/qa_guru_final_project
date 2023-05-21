package web.testops.helpers;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class CookieAuth {

    final static public String PROJECT_ID = "2264",
            X_XSRF_TOKEN = 	"cf1b166e-1cef-4033-bf70-d4ad4f3dd67d",
            ALLURE_TESTOPS_SESSION = "6fea0f28-885e-4833-b273-def9121c9f97";

    public CookieAuth authWithCookie() {
        open("/favicon.ico");
        Cookie authorizationCookie = new Cookie("ALLURE_TESTOPS_SESSION", ALLURE_TESTOPS_SESSION);
        getWebDriver().manage().addCookie(authorizationCookie);
        return this;
    }
    public void openTestCaseUrl() {
        Integer testCaseId = TestCaseManager.createTestCaseResponse.getId();
        String testCaseUrl = format("/project/%s/test-cases/%s", PROJECT_ID, testCaseId);
        open(testCaseUrl);
    }

}
