package helpers;

import org.openqa.selenium.Cookie;
import web.testops.tests.TestCaseManager;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class CookieAuth {

    final static public String PROJECT_ID = "2264",
            X_XSRF_TOKEN = 	"6e0d39b3-f15d-4298-9373-e829f1cc2fc6",
            ALLURE_TESTOPS_SESSION = "ae8fb6e1-a83a-4a82-971b-fed9160e48e7";

    public void authWithCookie() {
        open("/favicon.ico");

        Cookie authorizationCookie = new Cookie("ALLURE_TESTOPS_SESSION", ALLURE_TESTOPS_SESSION);
        getWebDriver().manage().addCookie(authorizationCookie);

        Integer testCaseId = TestCaseManager.createTestCaseResponse.getId();
        String testCaseUrl = format("/project/%s/test-cases/%s", PROJECT_ID, testCaseId);
        open(testCaseUrl);
    }
}
