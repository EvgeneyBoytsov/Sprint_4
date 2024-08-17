package orderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage { // главная страница приложения
    private final WebDriver driver;
    private final By buttonCookies = By.id("rcc-confirm-button"); // кнопка принятия использования куков

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void open() { // метод для перехода на сайт
        driver.get(EnvConfig.BASE_URL);
    }

    public void clickCookieButton() { // метод нажатия на кнопку куков
        driver.findElement(buttonCookies).click();
    }

    public void clickOrderButton(String orderButton) { // клик на кнопку Заказать. Кнопка выбирается в параметрах теста
        var buttonOrder = String.format("%s",orderButton); // переменная с локатором кнопки Заказать
        driver.findElement(By.className(buttonOrder)).click();
    }

    public void order (String orderButton) { // метод с шагами
        open();
        clickCookieButton();
        clickOrderButton(orderButton);
    }
}
