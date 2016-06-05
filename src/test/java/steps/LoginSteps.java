package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {

    LoginPage page;

    public LoginSteps(WebDriver driver){
        page = new LoginPage(driver);
    }

    public void performValidLogin(){
        String valid_login_env = System.getenv("VALID_LOGIN");
        String valid_password_env = System.getenv("VALID_PASSWORD");

        String valid_login = valid_login_env != null ? valid_login_env : "";
        String valid_password = valid_password_env != null ? valid_password_env : "";

        page.fillUserField(valid_login);
        page.fillPasswordField(valid_password);
        page.clickSubmitButton();
    }

}
