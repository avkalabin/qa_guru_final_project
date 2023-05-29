package web.testops.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import web.testops.authorization.AuthorizationApi;

import static web.testops.helpers.CookieAuth.*;
import static web.testops.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class Spec {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("Authorization", "Bearer " + AuthorizationApi.getAuthorization().getAccessToken())
            .queryParam("projectId", PROJECT_ID)
            .contentType("application/json;charset=UTF-8");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

}
