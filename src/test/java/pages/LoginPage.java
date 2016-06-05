package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "login")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public void fillUserField(String user){
        loginField.sendKeys(user);
    }

    public void fillPasswordField(String password){
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }
}
