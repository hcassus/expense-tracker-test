package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ExpensesPage;

public class ExpensesSteps {

    private static final String DAY = "01";
    private static final String MONTH = "06";
    private static final String YEAR = "2015";
    private static final String CATEGORY = "Leisure";
    private static final String AMOUNT = "215,00";
    private static final String REASON = "Expensive Dinner %s";
    private String createdReason;
    private String newReason;

    private ExpensesPage page;

    public ExpensesSteps(WebDriver driver){
        page = new ExpensesPage(driver);
    }

    public ExpensesSteps createValidExpense(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        createdReason = String.format(REASON, timestamp);
        navigateToAddExpenseMenu();
        page.fillDayField(DAY);
        page.fillMonthField(MONTH);
        page.fillYearField(YEAR);
        page.selectCategoryBox(CATEGORY);
        page.fillAmountField(AMOUNT);
        page.fillReasonField(createdReason);
        page.clickSubmitButton();
        return this;
    }

    public void checkExpenseIsCreated(){
        checkDate();
        checkCategory();
        checkAmount();
    }


    private void checkDate(){
        String date = String.format("%s.%s.%s", DAY, MONTH, YEAR.substring(2));
        Assert.assertEquals(date, page.obtainTaskDateByReason(createdReason));
    }

    private void checkCategory(){
        Assert.assertEquals(CATEGORY, page.obtainTaskCategoryByReason(createdReason));
    }

    private void checkAmount(){
        String amount = String.format("%s €", AMOUNT);
        Assert.assertEquals(amount, page.obtainTaskAmountByReason(createdReason));
    }

    private void navigateToAddExpenseMenu(){
        page.navigateToMenu("Add Expense");
    }

    public ExpensesSteps deleteCreatedExpense() {
        page.deleteExpenseByReason(createdReason);
        page.confirmDeletion();
        return this;
    }

    public ExpensesSteps editCreatedExpense() {
        page.editExpenseByReason(createdReason);
        String timestamp = String.valueOf(System.currentTimeMillis());
        newReason = String.format(REASON, timestamp);
        page.fillReasonField(newReason);
        page.clickSubmitButton();
        return this;
    }

    public ExpensesSteps cloneCreatedExpense() {
        page.cloneExpenseByReason(createdReason);
        page.clickSubmitButton();
        return this;
    }

    public void checkExpenseDeleted() {
        Assert.assertEquals(0, page.countExpensesByReason(createdReason));
    }

    public void checkExpenseEdited() {
        Assert.assertEquals(1, page.countExpensesByReason(newReason));
        Assert.assertEquals(0, page.countExpensesByReason(createdReason));
    }

    public void checkExpenseCloned() {
        Assert.assertEquals(2, page.countExpensesByReason(createdReason));
    }
}
