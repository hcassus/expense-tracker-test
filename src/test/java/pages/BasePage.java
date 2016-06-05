package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hcassus on 04/06/16.
 */
public class BasePage {

    WebDriver driver;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void navigateToMenu(String menu){
        driver.findElement(By.linkText(menu)).click();
    }
}
