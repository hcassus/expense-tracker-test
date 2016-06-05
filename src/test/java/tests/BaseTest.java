package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    LoginSteps loginSteps;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.get("http://thawing-shelf-73260.herokuapp.com/index.jsp");
        loginSteps = new LoginSteps(driver);
        loginSteps.performValidLogin();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
