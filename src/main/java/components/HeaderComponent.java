package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbsCommon {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text() = 'Войти']")
    private WebElement loginButton;

    public void clickLoginButton() {
        loginButton.click();
    }
}
