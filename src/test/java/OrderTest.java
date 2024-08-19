import orderPage.HomePage;
import orderPage.OrderPage;
import orderPage.RentPage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String orderButton;
    private final String name;
    private final String secondName;
    private final String address;
    private final String number;
    private final int indexStation;
    private final int rent;
    private final String color;
    private final String comments;

    public OrderTest(String orderButton, String name, String secondName, String address, int indexStation, String number, int rent, String color, String comments) {
        this.orderButton = orderButton;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.indexStation = indexStation;
        this.number = number;
        this.rent = rent;
        this.color = color;
        this.comments = comments;
    }

    @Rule
    public final DriverRule factory = new DriverRule();

    @Parameterized.Parameters
    public static Object[][] getCustomerData() {
        return new Object[][]{
                {"Button_Middle__1CSJM", "Ив", "Ив", "Москв", 15, "+71111111111", 1, "grey", ""},
                {"Button_Button__ra12g", "Иииииииииииииии", "Ивановна", "иииииииииииииииииииииииииииииииииииииииииииииииии", 70, "81111111111", 2, "black", "Тест номер 2"},
        };
    }

    @Test
    public void openMainPage() {

        WebDriver driver = factory.getDriver();

        HomePage objHomePage = new HomePage(driver);
        objHomePage.order(orderButton);

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.customerOrder(name, secondName, address, indexStation, number);

        RentPage objRentPage = new RentPage(driver);
        objRentPage.rentData(rent, color, comments);
    }
}