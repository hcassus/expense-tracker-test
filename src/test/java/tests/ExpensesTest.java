package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CategoriesSteps;
import steps.ExpensesSteps;

public class ExpensesTest extends BaseTest{

    private ExpensesSteps expensesSteps;
    private CategoriesSteps categoriesSteps;

    @Before
    public void setup(){
        super.setup();
        expensesSteps = new ExpensesSteps(driver);
        categoriesSteps = new CategoriesSteps(driver);
        categoriesSteps.createCategory("Leisure");
    }

    @Test
    public void createValidExpense(){
        expensesSteps
                .createValidExpense()
                .checkExpenseIsCreated();
    }

    @Test
    public void deleteExpense(){
        expensesSteps
                .createValidExpense()
                .deleteCreatedExpense()
                .checkExpenseDeleted();
    }

    @Test
    public void editExpense(){
        expensesSteps
                .createValidExpense()
                .editCreatedExpense()
                .checkExpenseEdited();
    }

    @Test
    public void cloneExpense(){
        expensesSteps
                .createValidExpense()
                .cloneCreatedExpense()
                .checkExpenseCloned();
    }

    @After
    public void tearDown(){
        expensesSteps
                .clearExpenses();
        categoriesSteps
                .clearCategories();
        driver.quit();
    }
}
