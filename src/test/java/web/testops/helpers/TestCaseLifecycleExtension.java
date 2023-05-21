package web.testops.helpers;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestCaseLifecycleExtension implements BeforeEachCallback, AfterEachCallback {
    private final TestCaseManager testCaseManager = new TestCaseManager();
    Faker faker = new Faker();
    static public String testCaseName;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        testCaseName = faker.name().fullName();
        testCaseManager.createTestCase(testCaseName);
        testCaseManager.addSteps();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        testCaseManager.deleteTestCase();
    }
}
