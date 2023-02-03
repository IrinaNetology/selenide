import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegister() {

        Configuration.holdBrowserOpen = true;

        String dt = generateDate(4);  // dt is now the new date
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dt);
        $("[name=name]").setValue("Петр Петров");
        $("[name=phone]").setValue("+79102002020");
        $("[data-test-id= 'agreement']").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $x("//*[contains(text(), 'Успешно')]").should(appear, Duration.ofSeconds(20));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + dt), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
