package orderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

public class RentPage { // страница с данными про аренду
    private final WebDriver driver;

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickFieldData() {
        String  fieldData = "[placeholder='* Когда привезти самокат']"; // переменная с локатором для поля Когда привезти самокат
        driver.findElement(By.cssSelector(fieldData)).click(); // клик по полю Когда привезти самокат
    }

    private void selectData() {
        String dataDelivery = "react-datepicker__day--0" + getNextDay(); // переменная для нового дня
        driver.findElement(By.className(dataDelivery)).click(); // выбираем дату доставки
    }

    private String getNextDay() { // метод выбора следующего дня от текущего
        LocalDate nextDate = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        return nextDate.format(formatter);
    }

    private void clickFieldRent() {
        String fieldRent = "Dropdown-placeholder"; // переменная с локатором для поля Срок аренды
        driver.findElement(By.className(fieldRent)).click(); // клик по полю Срок аренды
    }

    private void selectRentTime(int rentData) {
        String rentTime = String.format(".Dropdown-option:nth-child(%d)", rentData); // переменная с локатором для полей количества дней аренды
        driver.findElement(By.cssSelector(rentTime)).click(); // выбор продолжительности аренды
    }

    private void selectColor(String selectColor) {
        String color = String.format("[for='%s']", selectColor); // переменная с локатором для поля Цвет самоката
        driver.findElement(By.cssSelector(color)).click(); // выбор цвета самоката
    }

    private void inputComments(String comments) {
        String fieldComment = "input[placeholder='Комментарий для курьера']"; // переменная с локатором для поля Комментарий для курьера
        driver.findElement(By.cssSelector(fieldComment)).sendKeys(comments); // ввод в поле комментарий для курьера
    }

    private void clickButtonGive() {
        String orderButton = ".Order_Buttons__1xGrp .Button_Button__ra12g:last-child"; // переменная с локатором для кнопки Заказать
        driver.findElement(By.cssSelector(orderButton)).click(); // клик по кнопке Заказать
    }

    private void clickButtonYes() {
        String buttonYes = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']"; // переменная с локатором для кнопки Да
        driver.findElement(By.xpath(buttonYes)).click(); // клик по кнопке ДА
    }

    private void  checkStatusOrder() {
        String statusOrder =".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']"; // переменная с локатором для кнопки Посмотреть статус
        driver.findElement(By.xpath(statusOrder)).click(); // клик по кнопке Посмотреть статус
    }

    private void checkOrderPage() {
        String order = "Track_OrderColumns__2r_1F";
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(order)));
        assertTrue(driver.findElement(By.className(order)).isDisplayed()); // проверка открытия окна заказа в браузере
    }

    public void rentData ( int rent, String color, String comments) {
        clickFieldData();
        selectData();
        clickFieldRent();
        selectRentTime(rent);
        selectColor(color);
        inputComments(comments);
        clickButtonGive();
        clickButtonYes();
        checkStatusOrder();
        checkOrderPage();
    }
}
