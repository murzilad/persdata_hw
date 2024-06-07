package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonalAccountPage extends AbsBasePage {

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    public PersonalAccountPage pageHeaderShouldBeSameAs(String expectedHeader) {
        By headerSelector = By.cssSelector("div > h1");
        waiters.waitForCondition(ExpectedConditions.presenceOfElementLocated(headerSelector));
        assertThat($(headerSelector).getText())
                .as("Header of page should be same as {}", expectedHeader)
                .isEqualTo(expectedHeader);

        return this;
    }
}
