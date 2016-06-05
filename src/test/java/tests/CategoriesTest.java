package tests;

import org.junit.Before;
import org.junit.Test;
import steps.CategoriesSteps;

public class CategoriesTest extends BaseTest{

    private CategoriesSteps categoriesSteps;

    @Before
    public void setup(){
        super.setup();
        categoriesSteps = new CategoriesSteps(driver);
    }

    @Test
    public void createValidCategory(){
        categoriesSteps
                .createValidCategory()
                .checkCategoryIsCreated();
    }

    @Test
    public void deleteCategory(){
        categoriesSteps
                .createValidCategory()
                .deleteCreatedCategory()
                .checkCategoryDeleted();
    }

    @Test
    public void editCategory(){
        categoriesSteps
                .createValidCategory()
                .editCreatedCategory()
                .checkCategoryEdited();
    }
}
