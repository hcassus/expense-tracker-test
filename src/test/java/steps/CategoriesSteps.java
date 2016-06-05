package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CategoriesPage;

/**
 * Created by hcassus on 05/06/16.
 */
public class CategoriesSteps {

    private static final String CATEGORY_NAME = "cat %s";
    private String createdName;

    private CategoriesPage page;
    private String newName;

    public CategoriesSteps(WebDriver driver){
        page = new CategoriesPage(driver);
    }

    public CategoriesSteps createValidCategory() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        createdName = String.format(CATEGORY_NAME, timestamp);
        createCategory(createdName);
        return this;
    }

    private void navigateToCategoryMenu() {
        page.navigateToMenu("List Categories");
    }

    public void checkCategoryIsCreated() {
        Assert.assertEquals(1, page.countCategoriesByName(createdName));
    }

    public CategoriesSteps deleteCreatedCategory() {
        page.deleteCategoryByName(createdName);
        page.confirmDeletion();
        return this;
    }

    public void checkCategoryDeleted() {
        Assert.assertEquals(0, page.countCategoriesByName(createdName));
    }

    public CategoriesSteps editCreatedCategory() {
        page.editCategoryByName(createdName);
        String timestamp = String.valueOf(System.currentTimeMillis());
        newName = String.format(CATEGORY_NAME, timestamp);
        page.fillCategoryName(newName);
        page.clickSaveCategory();
        return this;
    }

    public void checkCategoryEdited() {
        Assert.assertEquals(0, page.countCategoriesByName(createdName));
        Assert.assertEquals(1, page.countCategoriesByName(newName));
    }

    public void createCategory(String name) {
        navigateToCategoryMenu();
        page.clickAddCategoryLink();
        page.fillCategoryName(name);
        page.clickCreateCategory();
    }

    public void clearCategories() {
        navigateToCategoryMenu();
        page.clearAllCategories();
    }
}
