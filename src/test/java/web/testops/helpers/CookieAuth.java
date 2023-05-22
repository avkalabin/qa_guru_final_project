package web.testops.helpers;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class CookieAuth {

    final static public String PROJECT_ID = "2264",
            X_XSRF_TOKEN = 	"2f9220e0-3075-4c58-ac9d-6078fab166fe",
            ALLURE_TESTOPS_SESSION = "918bcf23-bca0-4c73-b40d-fd9bcc74c9d6";

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
