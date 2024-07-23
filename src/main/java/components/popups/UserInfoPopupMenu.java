package components.popups;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoPopupMenu extends AbsCommon implements IPopup {

    public UserInfoPopupMenu(WebDriver driver) {
        super(driver);
    }

    private By navUserMenuLocator = By.xpath("//img[contains(@class, 'cQzahz')]/ancestor::div[contains(@class, 'hiQDuY')]");
    private By userMenuPopupLocator = By.xpath("//div[contains(@class, 'hiQDuY')]/div[contains(@class, 'DnSJp')]");
    private By myProfileButtonLocator = By.xpath("//*[text() = 'Мой профиль']");

    Actions actions = new Actions(driver);

    @Override
    public void popupShouldNotBeVisible() {
        assertThat(waiters.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(userMenuPopupLocator)))
                .as("Error: element is present in the dom")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(waiters.waitForCondition(ExpectedConditions.visibilityOfElementLocated(userMenuPopupLocator)))
                .as("Error: element is missing in the dom")
                .isTrue();
    }

    public UserInfoPopupMenu moveToMenu() {
        waitElementVisible(navUserMenuLocator);
        actions.moveToElement($(navUserMenuLocator)).perform();
        return this;
    }

    public UserInfoPopupMenu clickMyProfileButton() {
        waitElementVisible(myProfileButtonLocator);
        $(myProfileButtonLocator).click();
        return this;
    }
}
