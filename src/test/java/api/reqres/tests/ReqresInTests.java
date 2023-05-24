package api.reqres.tests;

import api.reqres.models.User;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.reqres.specs.Spec.requestSpec;
import static api.reqres.specs.Spec.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("API тесты https://reqres.in/")
@Owner("avkalabin")
@Feature("API тесты https://reqres.in/")
@Story("ReqresIn")
@Tag("api")
public class ReqresInTests {

    User user = new User();

    @Test
    @DisplayName("Проверка успешного логина")
    @Severity(SeverityLevel.BLOCKER)
    void successfulLoginTest() {

        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        User responseUser = step("Make request to successfully login", () ->
                given(requestSpec)
                        .body(user)
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(200)
                        .spec(responseSpec)
                        .extract().as(User.class));

        step("Verify response", () ->
                assertThat(responseUser.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Проверка наличия Michael Lawson в списке пользователей")
    @Severity(SeverityLevel.NORMAL)
    void getListUsers() {

        step("Make request to get list of user and verify Michael Lawson with Groovy", () ->
                given(requestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .statusCode(200)
                        .spec(responseSpec)
                        .body("data.findAll { it.first_name == 'Michael' }",
                                hasItems(hasEntry("first_name", "Michael"), hasEntry("last_name", "Lawson"))));
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void createUser() {

        user.setFirstName("morpheus");
        user.setJob("leader");

        User responseUser = step("Make request to create user", () ->
                given(requestSpec)
                        .body(user)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(201)
                        .spec(responseSpec)
                        .extract().as(User.class));

        step("Verify response", () -> {
            assertThat(responseUser.getFirstName()).isEqualTo("morpheus");
            assertThat(responseUser.getJob()).isEqualTo("leader");
        });
    }

    @Test
    @DisplayName("Проверка обновления данных пользователя")
    @Severity(SeverityLevel.NORMAL)
    void updateUser() {

        user.setFirstName("morpheus");
        user.setJob("zion resident");

            User responseUser = step("Make request to update user", () ->
                given(requestSpec)
                        .body(user)
                        .when()
                        .put("/users/2")
                        .then()
                        .statusCode(200)
                        .spec(responseSpec)
                        .extract().as(User.class));

        step("Verify response", () -> {
            assertThat(responseUser.getFirstName()).isEqualTo("morpheus");
            assertThat(responseUser.getJob()).isEqualTo("zion resident");
        });
    }

    @Test
    @DisplayName("Проверка статус кода при удалении пользователя")
    @Severity(SeverityLevel.NORMAL)
    void verifyDeleteCode() {

        step("Make request to delete user", () ->
                given(requestSpec)
                        .when()
                        .delete("https://reqres.in/api/users/2")
                        .then()
                        .spec(responseSpec)
                        .statusCode(204));
    }


    @Test
    @DisplayName("Проверка успешной регистрации")
    @Severity(SeverityLevel.CRITICAL)
    void successfulRegisterTest() {

        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        User responseUser = step("Make request to successfully register", () ->
                given(requestSpec)
                        .body(user)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(User.class));


        step("Verify response", () -> {
            assertThat(responseUser.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
            assertThat(responseUser.getId()).isEqualTo(4);
        });
    }

    @Test
    @DisplayName("Проверка id и email пользователя")
    @Severity(SeverityLevel.MINOR)
    void checkIdAndEmailOfFeaturedUser() {
        User userResponse = given().spec(requestSpec)
                .when()
                .pathParam("id", "2")
                .get("/users/{id}")
                .then()
                .spec(responseSpec)
                .extract().jsonPath().getObject("data", User.class);

        assertEquals(2, userResponse.getId());
        assertTrue(userResponse.getEmail().endsWith("@reqres.in"));
    }
}