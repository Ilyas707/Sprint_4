import pageobjects.MainPage;
import pageobjects.OrderPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.CoreMatchers.containsString;
import org.openqa.selenium.firefox.FirefoxDriver;



@RunWith(Parameterized.class)
public class OrderPageTests {

    private WebDriver driver;
    // Переменные данных для оформления заказа
    private final String name, surname, address, metro, phone, date, term, color, comment;
    // Сообщение об успешном оформлении заказа
    private final String expectedOrderSuccessText = "Заказ оформлен";


    public OrderPageTests(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String term,
            String color,
            String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] setDataForOrder() {
        return new Object[][] {
                {"Михаил", "Булгаков", "Москва, ул. Большая Садовая, д. 10, кв. 50", "Маяковская", "+79998887766", "08.06.2024", "пятеро суток", "чёрный жемчуг", "Привет, Курьер"},
                {"Иванов", "Иван", "Москва, Озерковская наб., 8, стр. 1", "Новокузнецкая", "+78482581496", "10.06.2024", "сутки", "серая безысходность", "Покатаемся вместе?"},
        };
    }

    @Before
    public void startUp() {
        //driver = new ChromeDriver(); // черех Хром тест валится при подтверждении оформлении заказа
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

    // Тест для проверки оформления заказа при нажатии на верхнюю кнопку "Заказать"
    @Test
    public void orderWithTopButtonWhenSuccess() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickCookieAppButton();
        mainPage.clickOrderButtonHeader();
        makeOrder(orderPage);
        MatcherAssert.assertThat(
                "Ошибка оформления нового заказа",
                orderPage.getNewOrderSuccessMessage(),
                containsString(this.expectedOrderSuccessText)
        );
    }

    // Тест для проверки оформления заказа при нажатии на нижнюю кнопку "Заказать"
    @Test
    public void orderWithBottomButtonWhenSuccess() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickCookieAppButton();
        mainPage.clickOrderButtonBody();
        makeOrder(orderPage);

        MatcherAssert.assertThat(
                "Ошибка оформления нового заказа",
                orderPage.getNewOrderSuccessMessage(),
                containsString(this.expectedOrderSuccessText)
        );
    }

    //Метод, описывающий оформление заказа

    private void makeOrder(OrderPage orderPage) {
        orderPage.waitForLoadForm(0);

        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setMetro(metro);
        orderPage.setPhone(phone);

        orderPage.clickNextButton();

        orderPage.setDate(date);
        orderPage.setTerm(term);
        orderPage.setColor(color);
        orderPage.setComment(comment);

        orderPage.makeOrder();
    }
}
