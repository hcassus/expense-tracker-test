package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import steps.ExpensesSteps;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

/**
 * Created by hcassus on 04/06/16.
 */
public class ExpensesTest {

    WebDriver driver;
    LoginSteps loginSteps;
    ExpensesSteps expensesSteps;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.get("http://thawing-shelf-73260.herokuapp.com/index.jsp");
        loginSteps = new LoginSteps(driver);
        expensesSteps = new ExpensesSteps(driver);

    }

    @Test
    public void createValidExpense(){
        loginSteps
                .performValidLogin();
        expensesSteps
                .createValidExpense()
                .checkExpenseIsCreated();
    }

    @Test
    public void deleteExpense(){
        loginSteps
                .performValidLogin();
        expensesSteps
                .createValidExpense()
                .deleteCreatedExpense()
                .checkExpenseDeleted();
    }

    @Test
    public void editExpense(){
        loginSteps
                .performValidLogin();
        expensesSteps
                .createValidExpense()
                .editCreatedExpense()
                .checkExpenseEdited();
    }

    @Test
    public void cloneExpense(){
        loginSteps
                .performValidLogin();
        expensesSteps
                .createValidExpense()
                .cloneCreatedExpense()
                .checkExpenseCloned();
    }



    @After
    public void tearDown(){
        driver.quit();
    }
}
