package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZeroWebappPage {

    public ZeroWebappPage() {
        // Initializes the WebElements in this class using PageFactory
        // It binds the driver with the elements defined using @FindBy
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "signin_button")
    public WebElement signInLinkOnHomePage;
    // The "Sign In" link on the homepage

    @FindBy(id = "user_login")
    public WebElement loginInputBox;
    // The input box for entering the username

    @FindBy(id = "user_password")
    public WebElement passwordInputBox;
    // The input box for entering the password

    @FindBy(name = "submit")
    public WebElement signInButtonOnLoginPage;
    // The "Sign In" button on the login page

    @FindBy(xpath = "(//a[@class='dropdown-toggle'])[1]")
    public WebElement settingsLink;
    // The first dropdown menu link, usually a settings menu

    @FindBy(xpath = "//strong[.='Online Banking']")
    public WebElement onlineBankingMenu;
    // The menu item labeled "Online Banking"

    @FindBy(id = "pay_bills_link")
    public WebElement payBillsLink;
    // The link to the "Pay Bills" section

    @FindBy(xpath = "//a[text()='Purchase Foreign Currency']")
    public WebElement purchaseForeignCurrencyLink;
    // The link to the "Purchase Foreign Currency" page

}
