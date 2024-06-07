package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;


public class OrderPage {

    private WebDriver driver;
    // Форма заказа
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");

    // Поле "Имя"
    private final By nameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Имя')]");

    // Поле "Фамилия"
    private final By surnameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Фамилия')]");

    // Поле "Адрес"
    private final By addressInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Адрес')]");

    // Поле "Станция метро"
    private final By metroInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Станция метро')]");

    // Контейнер для списка доступных станций метро
    private final By metroList = By.className("select-search__select");

    // Список станций метро для выбора
    private final By itemsMetroList = By.xpath(".//div[@class='select-search__select']//div[starts-with(@class,'Order_Text')]");

    // Поле "Телефон"
    private final By phoneInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Телефон')]");

    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button");

    // Поле "Когда привезти самокат" - дата в календаре
    private final By dateSelected = By.className("react-datepicker__day--selected");

    // Поле "Когда привезти самокат" - поле для ввода
    private final By dateInput = By.xpath(".//div[starts-with(@class, 'react-datepicker__input-container')]//input");

    // Контейнер для выпадающего списка в поле "Срок аренды"
    private final By termDropdownRoot = By.className("Dropdown-root");

    // Выпадающий список в поле "Срок аренды"
    private final By termDropdownOption = By.className("Dropdown-option");

    // Поле "Цвет самоката" с чек-боксами
    private final By colorLabels = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label");

    // Поле "Комментарий"
    private final By commentInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Комментарий')]");

    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[not(contains(@class,'Button_Inverted'))]");

    // Кнопка "Да" в окне подтверждения заказа
    private final By acceptOrderButton = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[not(contains(@class,'Button_Inverted'))]");

    // Всплывающее окно с сообщением об успешном создании заказа
    private final By newOrderSuccessMessage = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//div[(starts-with(@class,'Order_ModalHeader'))]");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для ожидания загрузки формы заказа
    public void waitForLoadForm(int index) {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(this.driver.findElements(this.orderForm).get(index)));
    }

    // Метод для ожидания загрузки элемента страницы
    private void waitForElementLoad(By elementToLoad) {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(elementToLoad)));
    }

    // Метод для ввода значения в поле "Имя"
    public void setName(String name) {
        this.driver.findElement(this.nameInput).sendKeys(name);
    }

    // Метод для ввода значения в поле "Фамилия"
    public void setSurname(String surname) {
        this.driver.findElement(this.surnameInput).sendKeys(surname);
    }

    // Метод для ввода значения в поле "Адрес"
    public void setAddress(String address) {
        this.driver.findElement(this.addressInput).sendKeys(address);
    }


    // Метод для ввода значения в поле "Метро"
    public void setMetro(String metro) {
        this.driver.findElement(this.metroInput).sendKeys(metro);
        this.waitForElementLoad(this.metroList);
        this.chooseElementFromDropdown(this.itemsMetroList, metro);
    }

    // Метод для выбора элемента выпадающего списка
    private void chooseElementFromDropdown(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = this.driver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }

    // Метод для ввода значения в поле "Телефон"
    public void setPhone(String phone) {
        this.driver.findElement(this.phoneInput).sendKeys(phone);
    }

    // Метод для нажатия на кнопку "Далее"
    public void clickNextButton() {
        this.driver.findElement(this.nextButton).click();
    }

    // Метод для нажатия на выбранную дату в календаре
    private void clickSelectedDate() {
        this.driver.findElement(this.dateSelected).click();
    }

    // Метод для ввода значения в поле "Дата"
    public void setDate(String date) {
        this.driver.findElement(this.dateInput).sendKeys(date);
        this.waitForElementLoad(this.dateSelected);
        this.clickSelectedDate();
    }

    // Метод для ракрытия выпадающего списка в поле "Срок аренды"
    private void clickTermDropdown() {
        this. driver.findElement(this.termDropdownRoot).click();
    }

    // Метод для ввода значения в поле "Срок аренды"
    public void setTerm(String termToChoose) {
        this.clickTermDropdown();
        this.chooseElementFromDropdown(this.termDropdownOption, termToChoose);
    }

    // Метод для ввода значения в поле "Цвет самоката"
    public void setColor(String colorToChoose) {
        this.chooseElementFromDropdown(this.colorLabels, colorToChoose);
    }

    // Метод для ввода значения в поле "Комментарий для курьера"
    public void setComment(String comment) {
        this.driver.findElement(this.commentInput).sendKeys(comment);
    }

    // Метод для нажатия на кнопку "Заказать"
    private void clickButtonOrder() {
        this.driver.findElement(this.orderButton).click();
    }

    // Метод для нажатия на кнопку "Да" при подтверждении заказа
    private void clickAcceptButtonOrder() {
        this.driver.findElement(this.acceptOrderButton).click();
    }

    // Метод для оформления заказа
    public void makeOrder() {
        this.clickButtonOrder();
        this.waitForElementLoad(this.acceptOrderButton);
        this.clickAcceptButtonOrder();
    }

    // Метод для получения сообщения об успешном создании заказа в всплывающем окне
    public String getNewOrderSuccessMessage() {
        return this.driver.findElement(this.newOrderSuccessMessage).getText();
    }

}
