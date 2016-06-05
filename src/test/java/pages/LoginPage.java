package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by hcassus on 04/06/16.
 */
public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "login")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement submitButton;

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
