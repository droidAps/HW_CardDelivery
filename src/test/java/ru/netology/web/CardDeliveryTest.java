package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    public void shouldSendApplication() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Смоленск");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        LocalDate date = LocalDate.now();
        date = date.plusDays(14);
        String testDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(".calendar-input input").doubleClick().sendKeys("BackSpace");
        $("[data-test-id='date'] input").setValue(testDate);
        $("[data-test-id='phone'] input").setValue("+79111234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
