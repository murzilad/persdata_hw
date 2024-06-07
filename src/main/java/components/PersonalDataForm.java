package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class PersonalDataForm extends AbsCommon {

    public PersonalDataForm(WebDriver driver) {
        super(driver);
    }

    private By nameFieldSelector = By.cssSelector("input[name = 'fname']");
    private By nameLatinFieldSelector = By.cssSelector("input[name = 'fname_latin']");
    private By surnameFieldSelector = By.cssSelector("input[name = 'lname']");
    private By surnameLatinFieldSelector = By.cssSelector("input[name = 'lname_latin']");
    private By blogNameFieldSelector = By.cssSelector("input[name = 'blog_name']");
    private By dateOfBirthFieldSelector = By.cssSelector("input[name = 'date_of_birth']");

    private By countryDropdownLocator = By.xpath("//div[contains (@class, 'country')]/parent::div");
    private By countryFieldLocator = By.xpath("//input[@name = 'country']/ancestor::div[contains (@class, 'select')]");
    private By countrySelectValueSelector = By.cssSelector("button[title = 'Россия']");

    private By cityDropdownLocator = By.xpath("//div[contains (@class, 'scroll_city')]/parent::div");
    private By cityFieldLocator = By.xpath("//input[@name = 'city']/ancestor::div[contains (@class, 'select')]");
    private By citySelectValueSelector = By.cssSelector("button[title = 'Москва']");

    private By englishLevelDropdownLocator = By.xpath("//input[@name = 'english_level']/ancestor::div[contains (@class, 'select')]/*[2]");
    private By englishLevelFieldLocator = By.xpath("//input[@name = 'english_level']/ancestor::div[contains (@class, 'select')]");
    private By englishLevelSelectValueSelector = By.cssSelector("button[title = 'Продвинутый (Advanced)']");


    private By addContactDropdownLocator = By.xpath("//div[./span[text() = 'Способ связи']]/following::div[1]");
    private By addContactFieldLocator = By.xpath("//div[./span[text() = 'Способ связи']]");
    private By contactSelectTgSelector = By.cssSelector("button[data-value = 'telegram']");
    private By contactSelectVkSelector = By.cssSelector("div[data-num = '1'] button[data-value = 'vk']");
    private By contactFieldSelector = By.cssSelector("div[data-prefix = 'contact'] input[type = 'text']");
    private By addContactButtonLocator = By.xpath("//button[text() = 'Добавить']");

    private By addContactSecondDropdownLocator = By.xpath("//div[@data-num = 1]//div[./span[text() = 'Способ связи']]/following::div[1]");
    private By addContactSecondFieldLocator = By.xpath("//div[@data-num = 1]//div[./span[text() = 'Способ связи']]");
    private By contactSecondFieldSelector = By.cssSelector("input[name= 'contact-1-value']");

    private By genderSelector = By.cssSelector("#id_gender");
    private By companyNameSelector = By.cssSelector("input[name = 'company']");
    private By jobTitleSelector = By.cssSelector("input[name = 'work']");

    private By saveButtonSelector = By.cssSelector("button[name = 'continue']");

        //значения полей "Личные данные"
        String firstName = "Отус";
        String firstNameLatin = "Otus";
        String surname = "Отусов";
        String surnameLatin = "Otusov";
        String blogname = "superotus";
        String dateOfBirth = "10.05.1990";
        String companyName = "Компания otus.ru";
        String jobTitle = "Программист";

        String tgName = "@tg_otus";
        String vkName = "@vk_otus";

    public void fillPersonalDataFields(By locator, String value) {
        clearAndFillInputField(locator, value);
    }

    public void clearAndFillInputField(By locator, String value) {
        $(locator).clear();
        $(locator).sendKeys(value);
    }

    public void clickToElement(By locator) {
        waitElementVisible(locator);
        $(locator).click();
    }

    public void checkDropDownIsHidden(By locator) {
        assertThat(waitElementVisible(locator).getAttribute("class").contains("hide"))
                .as("Error: element has no attribute")
                .isTrue();
    }

    public boolean checkDropDownIsVisible(By locator) {
        assertThat(waitElementVisible(locator).getAttribute("class").contains("hide"))
                .as("Error: element has no attribute")
                .isFalse();
        return false;
    }

    //заполнение поля с выпадающим списком
    public void fillDropDownFields(By dropDownLocator, By fieldLocator, By valueLocator) {
        checkDropDownIsHidden(dropDownLocator);
        clickToElement(fieldLocator);
        checkDropDownIsVisible(dropDownLocator);
        clickToElement(valueLocator);
        checkDropDownIsHidden(dropDownLocator);
    }

    //добавление способа связи
    public void addCommunicationMethod(By dropDownLocator, By fieldLocator, By valueLocator, String value) {
        checkDropDownIsHidden(dropDownLocator);
        clickToElement(fieldLocator);
        checkDropDownIsVisible(dropDownLocator);
        clickToElement(valueLocator);
        checkDropDownIsHidden(dropDownLocator);
        clearAndFillInputField(valueLocator, value);
    }

    //выбор пола
    public void selectAge(By locator) {
        WebElement genderSelect = $(locator);
        Select genderDropdown = new Select(genderSelect);
        genderDropdown.selectByValue("f");

        assertThat($(locator).getAttribute("value").equals("f"))
                .as("Error: gender is not selected correctly")
                .isTrue();
    }

    //заполнение всех полей формы "персональные данные"
    public void fillPersonalDataForm() {
        fillPersonalDataFields(nameFieldSelector, firstName);
        fillPersonalDataFields(nameLatinFieldSelector, firstNameLatin);
        fillPersonalDataFields(surnameFieldSelector, surname);
        fillPersonalDataFields(surnameLatinFieldSelector, surnameLatin);
        fillPersonalDataFields(blogNameFieldSelector, blogname);
        fillPersonalDataFields(dateOfBirthFieldSelector, dateOfBirth);
        fillPersonalDataFields(companyNameSelector, companyName);
        fillPersonalDataFields(jobTitleSelector, jobTitle);

        fillDropDownFields(countryDropdownLocator, countryFieldLocator, countrySelectValueSelector);
        waitAttributeDisabled(countryFieldLocator);
        waitAttributeNotDisabled(countryFieldLocator);
        fillDropDownFields(cityDropdownLocator, cityFieldLocator, citySelectValueSelector);
        fillDropDownFields(englishLevelDropdownLocator, englishLevelFieldLocator, englishLevelSelectValueSelector);

        addCommunicationMethod(addContactDropdownLocator, addContactFieldLocator, contactSelectTgSelector, tgName);
        clickToElement(addContactButtonLocator);
        addCommunicationMethod(addContactSecondDropdownLocator, addContactSecondFieldLocator, contactSelectVkSelector, vkName);

        selectAge(genderSelector);

        clickToElement(saveButtonSelector);
    }

    public boolean waitAttributeDisabled(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeToBe(locator, "disabled", "disabled"));
    }

    public boolean waitAttributeNotDisabled(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.not(ExpectedConditions.attributeToBe(locator, "disabled", "disabled")));
    }

    public void checkField(By locator, String value) {
        String actualText = String.valueOf($(locator).getAttribute("value"));
        assertThat(actualText).isEqualTo(value);
    }

    public void checkDropDown(By locator, String value) {
        String actualText = String.valueOf($(locator).getText());
        assertThat(actualText).isEqualTo(value);
    }

    //проверка значений полей после сохранения формы
    public void checkPersonalDataForm() {
        checkField(nameFieldSelector, firstName);
        checkField(nameLatinFieldSelector, firstNameLatin);
        checkField(surnameFieldSelector, surname);
        checkField(surnameLatinFieldSelector, surnameLatin);
        checkField(blogNameFieldSelector, blogname);
        checkField(dateOfBirthFieldSelector, dateOfBirth);
        checkField(companyNameSelector, companyName);
        checkField(jobTitleSelector, jobTitle);

        checkDropDown(countryFieldLocator, "Россия");
        checkDropDown(cityFieldLocator, "Москва");
        checkDropDown(englishLevelFieldLocator, "Продвинутый (Advanced)");
        checkDropDown(addContactFieldLocator, "Telegram");
        checkDropDown(addContactSecondFieldLocator, "VK");
        checkField(contactFieldSelector, tgName);
        checkField(contactSecondFieldSelector, vkName);

        checkField(genderSelector, "f");
    }

}
