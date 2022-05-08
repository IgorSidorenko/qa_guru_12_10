package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationFormTests extends TestBase {
    @BeforeAll
    static void setUpTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Successful fill registration test")
    void fillFormTest() {
        step("Open registration form", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
            Selenide.zoom(0.75);
        });
        step("Fill registration form", () -> {
            $("#firstName").setValue("Konstantin");
            $("#lastName").setValue("Konstatinopolski");
            $("#userEmail").setValue("test@mail.ru");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("7991000000");
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#uploadPicture").uploadFromClasspath("selenide-logo-big.png");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("June");
            $(".react-datepicker__year-select").selectOption("1995");
            $("div[aria-label='Choose Sunday, June 25th, 1995']").click();
            $("#subjectsInput").setValue("Computer science").pressEnter();
            $("#currentAddress").setValue("Baumana 10");
            $("#state").click();
            $(byText("NCR")).click();
            $("#city").click();
            $(byText("Delhi")).click();
            $("#submit").click();
        });
        step("Verify form data", () -> {
            $("[class=modal-open]").shouldHave(text("Konstantin"), text("Konstatinopolski"),
                    text("test@mail.ru"), text("Male"), text("7991000000"),
                    text("25 June,1995"), text("Sports"), text("selenide-logo-big"), text("Computer science"), text("Baumana 10"), text("NCR Delhi"));
        });

    }
}
