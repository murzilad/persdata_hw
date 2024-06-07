package components.popups;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPopup extends AbsCommon implements IPopup {

    public SearchPopup(WebDriver driver) {
        super(driver);
    }

    private By loginButtonSelector = By.cssSelector("#__PORTAL__ > div");

    @Override
    public void popupShouldNotBeVisible() {
        assertThat(waiters.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(loginButtonSelector)))
                .as("Error")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(waiters.waitForCondition(ExpectedConditions.visibilityOfElementLocated(loginButtonSelector)))
                .as("Error")
                .isTrue();
    }
}
