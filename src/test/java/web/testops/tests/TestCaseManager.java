package web.testops.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import web.testops.models.*;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static web.testops.specs.Spec.requestSpec;
import static web.testops.specs.Spec.responseSpec;

public class TestCaseManager {

    final static public String PROJECT_ID = "2264",
            X_XSRF_TOKEN = 	"95d9870b-d739-4d36-8a32-44a4a332e997",
            ALLURE_TESTOPS_SESSION = "5cfc68ab-f3de-404d-914c-dca53e427c09";

    public static CreateTestCaseResponse createTestCaseResponse;
    Integer testCaseId;
    Faker faker = new Faker();
    String testCaseName = faker.name().fullName();

    @DisplayName("Создание тест-кейса")
    void createTestCase() {

        CreateTestCaseBody createTestCaseBody = new CreateTestCaseBody();
        createTestCaseBody.setName(testCaseName);

        createTestCaseResponse = step("Создание тест-кейса", () -> given(requestSpec)
                .body(createTestCaseBody)
                .when()
                .post("/api/rs/testcasetree/leaf")
                .then()
                .spec(responseSpec)
                .extract().as(CreateTestCaseResponse.class));

    }

    @DisplayName("Добавление шагов в тест-кейс")
    void addSteps() {

        CreateStepBody.Steps step1 = new CreateStepBody.Steps();
        step1.setName("Step 1");

        CreateStepBody.Steps step2 = new CreateStepBody.Steps();
        step2.setName("Step 2");

        CreateStepBody createStepBody = new CreateStepBody();
        List<CreateStepBody.Steps> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        createStepBody.setSteps(steps);

        testCaseId = createTestCaseResponse.getId();
        String testCaseStepUrl = format("api/rs/testcase/%s/scenario", testCaseId);

        step("Добавление шагов в тест-кейс", () -> given(requestSpec)
                .body(createStepBody)
                .when()
                .post(testCaseStepUrl)
                .then()
                .spec(responseSpec)
                .extract().as(CreateStepResponse.class));
    }

    @DisplayName("Удаление тест-кейса")
    void deleteTestCase() {

        DeleteStepCaseBody deleteStepCaseBody = new DeleteStepCaseBody();
        DeleteStepCaseBody.Selection selection = new DeleteStepCaseBody.Selection();
        selection.setInverted(false);
        selection.setLeafsInclude(new int[]{testCaseId});
        selection.setKind("TreeSelectionDto");
        selection.setProjectId(2264);
        deleteStepCaseBody.setSelection(selection);

        step("Удаление тест-кейса", () -> given(requestSpec)
                .body(deleteStepCaseBody)
                .when()
                .post("/api/rs/testcase/bulk/remove")
                .then()
                .log().status()
                .log().body()
                .statusCode(204));
    }
}