package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class MainPage {
    private WebDriver driver;
    // Кнопка принятия куки
    private final By cookieAppButton = By.id("rcc-confirm-button");
    // Верхняя кнопка "Заказать"
    private final By topOrderButton = By.xpath(".//div[starts-with(@class, 'Header_Nav')]//button[text()='Заказать']");
    // Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath(".//div[starts-with(@class,'FinishButton')]//button[text()='Заказать']");
    // Вопрос для расскрытия в FAQ
    private final By accordionHeaders = By.className("accordion__heading");
    // Расскрытый ответ в FAQ
    private final By accordionItems = By.xpath(".//div[@class='accordion__panel']/p");
    // Логотип "Самокат" в шапке
    private final By scooterLogo = By.xpath(".//a[starts-with(@class,'Header_LogoScooter')]");
    // Логотип "Яндекс" в шапке
    private final By yandexLogo = By.xpath(".//a[starts-with(@class,'Header_LogoYandex')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для нажатия на кнопку принятия куки
    public void clickCookieAppButton() {
        driver.findElement(this.cookieAppButton).click();
    }


    // Метод для ожидания загрузки элемента "аккордеона"
    public void waitForLoadItem(int index) {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(this.driver.findElements(this.accordionItems).get(index)));
    }

    // Метод для получения текста Вопроса в FAQ
    public String getAccordionHeaderText(int index) {
        return this.driver.findElements(this.accordionHeaders).get(index).getText();
    }

    // Метод для получения текста Ответа в FAQ
    public String getAccordionItemText(int index) {
        return this.driver.findElements(this.accordionItems).get(index).getText();
    }

    // Метод для нажатия на вопрос в FAQ
    public void clickAccordionHeader(int index) {
        this.driver.findElements(this.accordionHeaders).get(index).click();
    }

    // Метод для проверки раскрытия ответа в FAQ
    public boolean isAccordionItemDisplayed(int index) {
        return this.driver.findElements(this.accordionItems).get(index).isDisplayed();
    }

    // Метод для нажатия верхней кнопки "Заказать"
    public void clickOrderButtonHeader() {
        this.driver.findElement(this.topOrderButton).click();
    }

    // Метод для нажатия нижней кнопки "Заказать"
    public void clickOrderButtonBody() {
        this.driver.findElement(this.bottomOrderButton).click();
    }

    // Метод для проверки, что логотип "Самокат" содержит ссылку на главную страницу Самоката
    public String getScooterLogoLink() {
        return this.driver.findElement(this.scooterLogo).getAttribute("href");
    }

    // Метод для проверки, что логотип "Яндекс" соержит ссылку на главную страницу Яндекса
    public String getYandexLogoLink() {
        return this.driver.findElement(this.yandexLogo).getAttribute("href");
    }

    // Метод для проверки открытия ссылки "Яндекс" в новой вкладке
    public boolean isYandexLogoLinkOpenedInNewTab() {
        String blank = "_blank";
        String value = this.driver.findElement(this.yandexLogo).getAttribute("target");
        return blank.equals(value);
    }

}
