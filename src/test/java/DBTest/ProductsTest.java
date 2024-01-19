package DBTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


public class ProductsTest extends BaseSeleniumTest{




    private static int tableScopeToCheck = 4;
    @Test
    public void addNewProductToTable(){

        ProductsPage productsPage = new ProductsPage();

        String productName = "Danie 1443";
        boolean isFruit = true;
        boolean isExotic = true;

        productsPage.addProduct(productName,isFruit,isExotic);
        tableScopeToCheck++;
        productsPage.hasDataInFieldElements(productName,isFruit,isExotic,tableScopeToCheck);

        productName = "";
        isFruit = false;
        isExotic = false;

        ProductsPage.addProduct(productName,isFruit,isExotic);
        tableScopeToCheck++;
        ProductsPage.hasDataInFieldElements(productName,isFruit,isExotic,tableScopeToCheck);

    }




}
