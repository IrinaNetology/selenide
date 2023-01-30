import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @Test
    void shouldRegister() {

        Configuration.holdBrowserOpen = true;

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 3);  // number of days to add
        String dt = sdf.format(c.getTime());  // dt is now the new date
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Казань");
        $("[placeholder='Дата встречи']").setValue(dt);
        $("[name=name]").setValue("Петр Петров");
        $("[name=phone]").setValue("+79102002020");
        $("[data-test-id= 'agreement']").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $x("//*[contains(text(), 'Успешно')]").should(appear, Duration.ofSeconds(20));

    }
}
