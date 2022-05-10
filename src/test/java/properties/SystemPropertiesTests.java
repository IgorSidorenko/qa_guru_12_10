package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    @Tag("RegistrationFormTests")
    void TestBase() {
        String baseUrl = System.getProperty("url", "https://demoqa.com");
        String version = System.getProperty("version", "100");
        String browserSize = System.getProperty("browserSize", "1920x1080");
    }
}
