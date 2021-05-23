package ru.netology;


import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Form {
    @Test
    void shouldInputIsCurrent() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Евгений Петров");
        $("[data-test-id='phone'] input").setValue("+79787654321");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInputNameNotCurrent() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Evgeniy Petrov");
        $("[data-test-id='phone'] input").setValue("+79787654321");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        $("[data-test-id='name']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldInputPhoneNotCorrect() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Евгений Петров");
        $("[data-test-id='phone'] input").setValue("89787654321");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldChecboxNotClick() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Евгений Петров");
        $("[data-test-id='phone'] input").setValue("+79787654321");
        $$("button").find(exactText("Продолжить")).click();
        $("[data-test-id=agreement]").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
        $(".input_invalid[data-test-id=agreement]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNameNotInput() {
        open("http://localhost:9999");
        $("[data-test-id='phone'] input").setValue("+79787654321");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Продолжить")).click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }


}
