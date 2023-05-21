package web.testops.helpers;

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

    public static CreateTestCaseResponse createTestCaseResponse;
    Integer testCaseId;

    @DisplayName("Создание тест-кейса")
    public void createTestCase(String testCaseName) {

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
    public void addSteps() {

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
    public void deleteTestCase() {

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