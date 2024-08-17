import orderPage.EnvConfig;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;

public class QuestionPage {
    private final WebDriver driver;

    public QuestionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() { // метод для перехода на сайт
        driver.get(EnvConfig.BASE_URL);
    }

    /**
     * Метод нажатия на кнопку куков
     */
    public void clickCookieButton() {
        By buttonCookies = By.id("rcc-confirm-button"); // кнопка принятия использования куков
        driver.findElement(buttonCookies).click();
    }

    public void checkQuestion_1(int numberField, String question, String answer) {

        String fieldQuestion_1 = String.format("accordion__heading-%d", numberField); // локатор для поля Вопрос
        String fieldAnswer_1 = String.format("accordion__panel-%s", numberField); // локатор для поля Ответ
        String question_1 = driver.findElement(By.id(fieldQuestion_1)).getText(); // переменная с локатором для текста поля Вопрос

        WebElement element = driver.findElement(By.className("Home_FourPart__1uthg"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element); // скролл до раздела с вопросами

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.WAIT))
                .until(ExpectedConditions.elementToBeClickable(By.id(fieldQuestion_1))); // ожидание кликабельности поля Вопрос

        driver.findElement(By.id(fieldQuestion_1)).click(); // клик на поле Вопрос

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldAnswer_1))); // ожидание поля Ответ (анимация)

        String answer_1 = driver.findElement(By.id(fieldAnswer_1)).getText(); // переменная с локатором для текста поля Ответ

        assertTrue(driver.findElement(By.id(fieldAnswer_1)).isDisplayed()); // проверка, что поле Ответ появилось на экране

        MatcherAssert.assertThat(question_1, containsString(question)); // проверка фактического текста вопроса с реальным текстом вопроса

        MatcherAssert.assertThat(answer_1, containsString(answer));// проверка фактического текста ответа с реальным текстом ответа
    }

    public void checkQNA (int numberField, String question, String answer) { // метод с шагами
        open();
        clickCookieButton();
        checkQuestion_1(numberField, question, answer);

    }
}
