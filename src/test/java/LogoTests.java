import pageobjects.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

public class LogoTests {

    private WebDriver driver;

    // Ожидаемый URL для логотипа "Яндекс"
    private final String yandexUrl = "https://yandex.ru";

    /// Ожидаемый URL для логотипа "Самокат"
    private final String scooterUrl = "https://qa-scooter.praktikum-services.ru";

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

    // Тест для проерки: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
    @Test
    public void checkYandexLinkIsCorrect() {
        MainPage mainPage = new MainPage(driver);

        assertTrue(
                "По клику на логотип Яндекса не происходит переход на " + this.yandexUrl,
                mainPage.getYandexLogoLink().contains(this.yandexUrl)
        );

        assertTrue(
                "При нажатии на логотип Яндекса в новом окне не открывается главная страница Яндекса",
                mainPage.isYandexLogoLinkOpenedInNewTab()
        );
    }

    // Тест для проерки: если нажать на логотип Яндекса, в новом окне откроется главная страница Самоката
    @Test
    public void checkScooterLinkIsCorrect() {
        MainPage mainPage = new MainPage(driver);

        assertTrue(
                "По клику на логотип Самоката не происходит переход на " + this.scooterUrl,
                mainPage.getScooterLogoLink().contains(this.scooterUrl)
        );
    }
}
