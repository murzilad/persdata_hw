package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationDataForm extends AbsCommon {

    public AuthorizationDataForm(WebDriver driver) {
        super(driver);
    }

    private By enterButtonLocator = By.xpath("//button[text() = 'Войти']");
    private By emailFieldLocator = By.xpath("//div[./input[@name='email']]");
    private By emailInputSelector = By.cssSelector("input[name = 'email']");
    private By passwordFieldLocator = By.xpath("//div[./input[@type='password']]");
    private By passwordInputSelector = By.cssSelector("input[type = 'password']");
    private By submitButtonLocator = By.xpath("//button[./*[text()='Войти']]");

    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    public AuthorizationDataForm fillAuthorizationFields() {

        //в поле email ввести адрес эл почты
        waitElementVisible(emailFieldLocator).click();
        driver.findElement(emailInputSelector).sendKeys(username);

        //в поле password ввести пароль
        waitElementVisible(passwordFieldLocator).click();
        driver.findElement(passwordInputSelector).sendKeys(password);

        //клик на кнопку войти
        waitElementVisible(submitButtonLocator).click();
        return this;
    }

    //проверка успешной авторизации, где не отображается кнопка "войти"
    public AuthorizationDataForm authorizationShouldBeSuccessful() {
        boolean enterButtonNotVisible = waitElementNotVisible(enterButtonLocator);

        assertThat(enterButtonNotVisible)
                .as("Error: item displays in DOM")
                .isTrue();

        return this;
    }
}
