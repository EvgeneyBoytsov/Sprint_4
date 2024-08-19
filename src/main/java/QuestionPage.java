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

    public void clickCookieButton() { // клик на кнопку принятия куков
        By buttonCookies = By.id("rcc-confirm-button");
        driver.findElement(buttonCookies).click();
    }

    public void scroll() { // скроллинг до части страницы с вопросами
        WebElement element = driver.findElement(By.className("Home_FourPart__1uthg"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickQuestionField(String field) { // клик по полям с вопросами
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.WAIT))
                .until(ExpectedConditions.elementToBeClickable(By.id(field)));
        driver.findElement(By.id(field)).click();
    }

    public String getQuestion(String field) { // получение фактического текста вопросов
        return driver.findElement(By.id(field)).getText();
    }

    public void checkQuestion(String actualQuestion,String expectedQuestion)  { // сравнение фактических текстов вопросов с ожидаемым
        MatcherAssert.assertThat(actualQuestion, containsString(expectedQuestion));
    }

    public String getAnswer(String field) { //получение фактического текста ответов
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(field)));
        return driver.findElement(By.id(field)).getText();
    }

    public void checkAnswer(String field, String actualAnswer, String expectedAnswer) {
        assertTrue(driver.findElement(By.id(field)).isDisplayed()); // проверка, что поле Ответ появилось на экране
        MatcherAssert.assertThat(actualAnswer, containsString(expectedAnswer));// сравнение фактических текстов ответов с ожидаемым
    }

    public void checkQNA(int numberField, String expectedQuestion, String expectedAnswer) { // метод с шагами
        open();
        clickCookieButton();
        scroll();

        String fieldQuestion = String.format("accordion__heading-%d", numberField);
        clickQuestionField(fieldQuestion);
        checkQuestion(getQuestion(fieldQuestion), expectedQuestion);

        String fieldAnswer = String.format("accordion__panel-%s", numberField);
        checkAnswer(fieldAnswer, getAnswer(fieldAnswer), expectedAnswer);
    }
}
