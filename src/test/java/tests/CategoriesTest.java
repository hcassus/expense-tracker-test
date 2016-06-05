package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import steps.CategoriesSteps;
import steps.ExpensesSteps;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

/**
 * Created by hcassus on 04/06/16.
 */
public class CategoriesTest {

    WebDriver driver;
    LoginSteps loginSteps;
    CategoriesSteps categoriesSteps;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.get("http://thawing-shelf-73260.herokuapp.com/index.jsp");
        loginSteps = new LoginSteps(driver);
        categoriesSteps = new CategoriesSteps(driver);
    }

    @Test
    public void createValidCategory(){
        loginSteps
                .performValidLogin();
        categoriesSteps
                .createValidCategory()
                .checkCategoryIsCreated();
    }

    @Test
    public void deleteCategory(){
        loginSteps
                .performValidLogin();
        categoriesSteps
                .createValidCategory()
                .deleteCreatedCategory()
                .checkCategoryDeleted();
    }

    @Test
    public void editCategory(){
        loginSteps
                .performValidLogin();
        categoriesSteps
                .createValidCategory()
                .editCreatedCategory()
                .checkCategoryEdited();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}