package orderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage { // страница с данными заказчика

    private final WebDriver driver;

    public  OrderPage (WebDriver driver) {
        this.driver = driver;
    }

    public void clickFieldName(String name) { // ввод в поле Имя
        String fieldName = "input[placeholder='* Имя']"; // переменная с локатором для поля Имя
        driver.findElement(By.cssSelector(fieldName)).sendKeys(name);
    }

    public void clickFieldSecondName(String secondName) { // ввод в поле Фамилия
        String fieldSecondName = "input[placeholder='* Фамилия']"; // переменная с локатором для поля Фамилия
        driver.findElement(By.cssSelector(fieldSecondName)).sendKeys(secondName);
    }

    public void clickFieldAddress(String address) { // ввод в поле Адрес
        String fieldAddress = "input[placeholder='* Адрес: куда привезти заказ']"; // переменная с локатором для поля Адрес
        driver.findElement(By.cssSelector(fieldAddress)).sendKeys(address);
    }

    public void clickFieldMetro() { // клик по полю Станция метро
        String fieldMetroS = "input[placeholder='* Станция метро']"; // переменная с локатором для поля Станция метро
        driver.findElement(By.cssSelector(fieldMetroS)).click();
    }

    public void selectStation(int metroIndex) { // выбор станции метро
        var indexMetro = String.format("li[data-index=\"%d\"]", metroIndex); // переменная с локатором для списка станций
        driver.findElement(By.cssSelector(indexMetro)).click();
    }

    public void clickFieldNumber(String number) { // ввод в поле Телефон
        String fieldNumber= "input[placeholder='* Телефон: на него позвонит курьер']"; // переменная с локатором для поля Телефон
        driver.findElement(By.cssSelector(fieldNumber)).sendKeys(number);
    }

    public void clickButtonNext() { // клик по кнопке Далее
        String buttonNext = "Button_Middle__1CSJM"; // переменная с локатором для кнопки Далее
        driver.findElement(By.className(buttonNext)).click();
    }

    public void customerOrder(String name,String secondName, String address, int indexStation, String number) {
        clickFieldName(name);
        clickFieldSecondName(secondName);
        clickFieldAddress(address);
        clickFieldMetro();
        selectStation(indexStation);
        clickFieldNumber(number);
        clickButtonNext();
    }
}
