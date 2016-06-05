package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by hcassus on 04/06/16.
 */
public class CategoriesPage extends BasePage{

    public static final String ROW_XPATH_BY_NAME = "//tr[td[position() = 1 and text() = '%s']]";

    public CategoriesPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "go_add_category")
    WebElement addCategoryLink;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "submit")
    WebElement createCategoryButton;

    @FindBy(id = "submit")
    WebElement saveCategoryButton;

    public void clickCreateCategory(){
        createCategoryButton.click();
    }

    public void clickSaveCategory(){
        saveCategoryButton.click();
    }

    public String getTextByColumn(WebElement row, int columnNumber){
        return row.findElement(By.xpath("./td["+columnNumber+"]")).getText();
    }

    private WebElement findRowByName(String reason) {
        String rowXPath = String.format(ROW_XPATH_BY_NAME, reason);
        return driver.findElement(By.xpath(rowXPath));
    }

    public int countCategoriesByName(String name) {
        String rowXPath = String.format(ROW_XPATH_BY_NAME, name);
        return driver.findElements(By.xpath(rowXPath)).size();
    }

    public void confirmDeletion() {
        driver.switchTo().alert().accept();
    }

    public void clickAddCategoryLink() {
        addCategoryLink.click();
    }

    public void fillCategoryName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void deleteCategoryByName(String name) {
        WebElement row = findRowByName(name);
        String deleteButtonXPath = "./td/a[contains(@id, 'delete')]";
        row.findElement(By.xpath(deleteButtonXPath)).click();
    }

    public void editCategoryByName(String name) {
        WebElement row = findRowByName(name);
        String deleteButtonXPath = "./td/a[contains(@id, 'edit')]";
        row.findElement(By.xpath(deleteButtonXPath)).click();
    }
}
