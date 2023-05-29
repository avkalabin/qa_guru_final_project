package web.testops.authorization;

import org.aeonbits.owner.ConfigFactory;
import web.testops.config.WebDriverConfig;

import static io.restassured.RestAssured.with;
import static web.testops.helpers.CustomAllureListener.withCustomTemplates;

public class AuthorizationApi {

    public static final String OAUTH_TOKEN = "/api/uaa/oauth/token";
    public static final String LOGIN = "/api/login/system";
    private static final String ALLURE_TESTOPS_SESSION = "ALLURE_TESTOPS_SESSION";
    static WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static AuthorizationResponseDTO getAuthorization() {
        return with()
                .filter(withCustomTemplates())
                .formParam("grant_type", "apitoken")
                .formParam("scope", "openid")
                .formParam("token", webDriverConfig.token())
                .when()
                .post(OAUTH_TOKEN)
                .then()
                .statusCode(200)
                .extract().as(AuthorizationResponseDTO.class);
    }

    public static String getAuthorizationCookie() {

        String xsrfToken = getAuthorization().getJti();
        return with().filter(withCustomTemplates())
                .header("X-XSRF-TOKEN", xsrfToken)
                .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                .formParam("username", webDriverConfig.username())
                .formParam("password", webDriverConfig.password())
                .when()
                .post(LOGIN)
                .then()
                .statusCode(200).extract().response()
                .getCookie(ALLURE_TESTOPS_SESSION);
    }
}
