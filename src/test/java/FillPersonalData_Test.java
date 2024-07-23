import components.AuthorizationDataForm;
import components.HeaderComponent;
import components.PersonalDataForm;
import components.popups.UserInfoPopupMenu;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.PersonalAccountPage;

public class FillPersonalData_Test {

    private final static Logger logger = LogManager.getLogger(FillPersonalData_Test.class);
    private WebDriver driver = null;

    @BeforeEach
    public void init() {
        logger.trace("Открытие браузера начато");
        driver = new WebDriverFactory().create();
        logger.trace("Открытие браузера завершено");
    }

    @AfterEach //после каждого теста, если окно браузера не закрыто, то закрывается
    public void close() {
        logger.trace("Закрытие браузера начато");
        if (driver!=null) {
            driver.quit(); //close - текущ вкладка, quit - весь браузер со всеми вкладками
            logger.trace("Закрытие браузера завершено");
        }
    }

    @Test
    public void fillPersonalData() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        UserInfoPopupMenu userInfoPopupMenu = new UserInfoPopupMenu(driver);

        HeaderComponent headerComponent = new HeaderComponent(driver);

        headerComponent
                .clickLoginButton();

        AuthorizationDataForm authorizationDataForm = new AuthorizationDataForm(driver);
        authorizationDataForm.fillAuthorizationFields()
                .authorizationShouldBeSuccessful();

        userInfoPopupMenu
                .popupShouldNotBeVisible();

        userInfoPopupMenu
                .moveToMenu()
                .popupShouldBeVisible();

        userInfoPopupMenu
                .moveToMenu()
                .clickMyProfileButton();

        new PersonalAccountPage(driver)
                .pageHeaderShouldBeSameAs("Личный кабинет");

        PersonalDataForm personalDataForm = new PersonalDataForm(driver);

        personalDataForm.fillPersonalDataForm();

        driver.manage().deleteAllCookies();

        mainPage.open();
        headerComponent.clickLoginButton();
        authorizationDataForm.fillAuthorizationFields()
                .authorizationShouldBeSuccessful();
        userInfoPopupMenu.moveToMenu()
                .clickMyProfileButton();
        personalDataForm.checkPersonalDataForm();
    }
}
