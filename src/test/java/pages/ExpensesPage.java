package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ExpensesPage extends BasePage{

    public static final String ROW_XPATH_BY_REASON = "//tr[td[position() = 4 and text() = '%s']]";

    public ExpensesPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "day")
    private WebElement dayField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(id = "category")
    private WebElement categoryBox;

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "reason")
    private WebElement reassonField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public void fillDayField(String day){
        dayField.sendKeys(day);
    }

    public void fillMonthField(String month){
        monthField.clear();
        monthField.sendKeys(month);
    }

    public void fillYearField(String year){
        yearField.sendKeys(year);
    }

    public void selectCategoryBox(String option){
        categoryBox.sendKeys(option);
    }

    public void fillAmountField(String amount){
        amountField.sendKeys(amount);
    }

    public void fillReasonField(String reason){
        reassonField.clear();
        reassonField.sendKeys(reason);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public String obtainTaskDateByReason(String reason) {
        WebElement row = findRowByReason(reason);
        String date = getTextByColumn(row, 1);
        return date;
    }

    public String obtainTaskCategoryByReason(String reason) {
        WebElement row = findRowByReason(reason);
        String category = getTextByColumn(row, 2);
        return category;
    }

    public String obtainTaskAmountByReason(String reason) {
        WebElement row = findRowByReason(reason);
        String amount = getTextByColumn(row, 3);
        return amount;
    }

    public String getTextByColumn(WebElement row, int columnNumber){
        return row.findElement(By.xpath("./td["+columnNumber+"]")).getText();
    }

    public void deleteExpenseByReason(String reason) {
        WebElement row = findRowByReason(reason);
        String deleteButtonXPath = "./td/a[contains(@id, 'delete')]";
        row.findElement(By.xpath(deleteButtonXPath)).click();
    }

    public void editExpenseByReason(String reason) {
        WebElement row = findRowByReason(reason);
        String editButtonXPath = "./td/a[contains(@id, 'edit')]";
        row.findElement(By.xpath(editButtonXPath)).click();
    }

    public void cloneExpenseByReason(String reason) {
        WebElement row = findRowByReason(reason);
        String cloneButtonXPath = "./td/a[contains(@id, 'copy')]";
        row.findElement(By.xpath(cloneButtonXPath)).click();
    }

    private WebElement findRowByReason(String reason) {
        String rowXPath = String.format(ROW_XPATH_BY_REASON, reason);
        return driver.findElement(By.xpath(rowXPath));
    }

    public int  countExpensesByReason(String reason) {
        String rowXPath = String.format(ROW_XPATH_BY_REASON, reason);
        return driver.findElements(By.xpath(rowXPath)).size();
    }

    public void confirmDeletion() {
        driver.switchTo().alert().accept();
    }

    public void deleteAllExpenses() {
        WebElement element;
        do {
            try {
                element = driver.findElement(By.xpath("//td/a[contains(@id, 'delete')]"));
                element.click();
                confirmDeletion();
            } catch (NoSuchElementException e){
                break;
            }
        } while (element != null);
    }
}
