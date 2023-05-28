package web.testops.helpers;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class CookieAuth {

    final static public String PROJECT_ID = "2264",
            X_XSRF_TOKEN = 	"bd3b6890-ab8f-4b2c-83ea-a439f90acc6e",
            ALLURE_TESTOPS_SESSION = "25a1b72a-c9c3-490d-9761-f57eddab3170";

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
