package helpers;

import org.openqa.selenium.Cookie;
import web.testops.tests.TestCaseManager;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class CookieAuth {

    final static public String PROJECT_ID = "2264",
            X_XSRF_TOKEN = 	"2e28340e-93b0-4e67-9274-8aef859400a3",
            ALLURE_TESTOPS_SESSION = "9afed535-340a-408e-aeab-cc8e36137766";

    public void authWithCookie() {
        open("/favicon.ico");

        Cookie authorizationCookie = new Cookie("ALLURE_TESTOPS_SESSION", ALLURE_TESTOPS_SESSION);
        getWebDriver().manage().addCookie(authorizationCookie);

        Integer testCaseId = TestCaseManager.createTestCaseResponse.getId();
        String testCaseUrl = format("/project/%s/test-cases/%s", PROJECT_ID, testCaseId);
        open(testCaseUrl);
    }
}
