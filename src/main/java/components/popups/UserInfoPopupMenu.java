package components.popups;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class UserInfoPopupMenu extends AbsCommon implements IPopup {

    public UserInfoPopupMenu(WebDriver driver) {
        super(driver);
    }

    private By navUserMenuLocator = By.xpath("/html/body/div[1]/div[1]/div[1]/div/section/div[2]");
    private By myProfileButtonLocator = By.xpath("//*[text() = 'Мой профиль']");

    Actions actions = new Actions(driver);

    @Override
    public void popupShouldNotBeVisible() {
    }

    @Override
    public void popupShouldBeVisible() {
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
