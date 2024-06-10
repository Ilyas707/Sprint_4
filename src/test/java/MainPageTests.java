import pageobjects.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.hamcrest.MatcherAssert;
import static org.junit.Assert.fail;
import static org.hamcrest.Matchers.equalTo;


    @RunWith(Parameterized.class)
    public class MainPageTests {
        private WebDriver driver;
        // Порядковый номер вопроса в FAQ
        private final int numberOfElement;
        // Ожидаемый текст вопроса в FAQ
        private final String expectedHeaderText;
        // Ожидаемый текст расскрытого ответа в FAQ
        private final String expectedItemText;


        public MainPageTests(int numberOfAccordionItem, String expectedHeaderText, String expectedItemText) {
            this.numberOfElement = numberOfAccordionItem;
            this.expectedHeaderText = expectedHeaderText;
            this.expectedItemText = expectedItemText;
        }


        @Parameterized.Parameters
        public static Object[][] setTestData() {
            return new Object[][] {
                    {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." },
            };
        }


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


        @Test
        public void checkIsCorrectAccordion() {
            MainPage mainPage = new MainPage(driver);

            mainPage.clickCookieAppButton();
            mainPage.clickAccordionHeader(this.numberOfElement);
            mainPage.waitForLoadItem(this.numberOfElement);

            assert mainPage.isAccordionItemDisplayed(this.numberOfElement) : "Вопрос в FAQ №" + this.numberOfElement + " не раскрылся";
            MatcherAssert.assertThat("Не совпадает текст вопроса №" + this.numberOfElement, this.expectedHeaderText, equalTo(mainPage.getAccordionHeaderText(this.numberOfElement)));
            MatcherAssert.assertThat("Не совпадает текст ответа №" + this.numberOfElement, this.expectedItemText, equalTo(mainPage.getAccordionItemText(this.numberOfElement)));

        }
    }
