package DBTest;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//div/div/div/div[1]/button")
    private static WebElement buttonAddProduct;

    @FindBy(xpath = "//input [@id='name']")
    private static WebElement inputName;

    @FindBy(xpath = "//select [@id =\"type\"]")
    private static WebElement selectorIsFruit;
    @FindBy(xpath = "//select/option [@value=\"FRUIT\"]")
    private static WebElement listType_Fruit;
    @FindBy(xpath = "//select/option [@value=\"VEGETABLE\"]")
    private static WebElement listType_Vegetable;

    @FindBy(xpath = "//input [@id = \"exotic\"]")
    private static WebElement checkboxExotic;

    @FindBy(xpath = "//button [@id = \"save\"]")
    private static WebElement buttonSave;


    public ProductsPage() {
        driver.get("http://localhost:8080/food");
        PageFactory.initElements(driver, this);

    }

    public static void addProduct(String productName, boolean isFruit, boolean isExotic)
    {

        wait.until(ExpectedConditions.elementToBeClickable(buttonAddProduct));
        buttonAddProduct.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body [@class=\"modal-open\"]")));


        inputName.sendKeys(productName);
        if (!inputName.getAttribute("value").equals(productName)) {
            Assert.fail("Введённые значения параметра НАИМЕНОВАНИЕ не соответствуют полученным значениям с кода страницы");
        }



        /*listTypeFruit.click*/
        if (isFruit) {
            listType_Fruit.click();

        } else listType_Vegetable.click();
        if (selectorIsFruit.getAttribute("value").equals("VEGETABLE")) {
            if (isFruit) {
                Assert.fail("Введённые значения параметра ТИП не соответствуют полученным значениям с кода страницы");
            }}
             else if (selectorIsFruit.getAttribute("value").equals("FRUIT")) {
                if (!isFruit) {
                    Assert.fail("Введённые значения параметра ТИП не соответствуют полученным значениям с кода страницы");
                }} else Assert.fail("В коде страницы, в окне добавления товара, " +
                        "в параметре ТИП(овощ, фрукт), внутри атрибута VALUE лежат непредполагаемые значения");

//Стоит ли в ошибке прописывать эти текстовые комментарии? Эта команда всё равно укажет, где был остановлен процесс,
                //а там уже по коду будет понятно, что сломалось






                /*checkboxExotic.click*/
//                if (checkboxExotic.getAttribute ("checked").equals(false)) {
//                    if (isExotic) {
//                        checkboxExotic.click();
//                    }
//                } else if (!isExotic) {
//                    checkboxExotic.click();
//                }

//                if (checkboxExotic.getAttribute("checked").equals(false)) {
//                    if (isExotic) {
//                        Assert.fail("Введённые значения параметра ЭКЗОТИЧЕСКИЙ не соответствуют полученным значениям с кода страницы");
//                    }
//                } else if (!isExotic) {
//                    Assert.fail("Введённые значения параметра ЭКЗОТИЧЕСКИЙ не соответствуют полученным значениям с кода страницы");
//                }


                buttonSave.click();


    }





    public static void hasDataInFieldElements(String productName, boolean isFruit, boolean isExotic, int tableScopeToCheck) {

        String prePathForTableCheck = "//tr[" + tableScopeToCheck + "]";
        By colomnNumberPath = By.xpath(prePathForTableCheck + "/th[text()=" + tableScopeToCheck + "]");
        WebElement rowNumber = driver.findElement(colomnNumberPath);

        String rowNumberText = rowNumber.getText();

        int rowNumberInt = Integer.parseInt(rowNumberText);


        if (rowNumberInt == tableScopeToCheck) {
            By colomnNamePath = By.xpath(prePathForTableCheck + "/td[1]");
            WebElement rowName = driver.findElement(colomnNamePath);
            String rowNameText = rowName.getText();

            if (!rowNameText.equals(productName) ) {
                Assert.fail();
            }
        } else Assert.fail();
        //Проверка полей таблицы # и Наименование


        By rowFruitt = By.xpath(prePathForTableCheck + "/td[2]");
        WebElement rowFruit = driver.findElement(rowFruitt);
        String rowFruitText = rowFruit.getText();
        //сам не понял что написал, но работает
        // с именем переменной тоже самое
        if (rowFruitText.equals("Фрукт") ) {
            if (!isFruit) {
                Assert.fail();
            }
        } else if (isFruit){Assert.fail();}
        //Проверка поля таблицы Тип


//        By rowExoticc = By.xpath(prePathForTableCheck + "/td[3]");
//        WebElement rowExotic = driver.findElement(rowExoticc);
//        String rowExoticText = rowExotic.getText();
//
//        if (rowExoticText.equals("true") ) {
//            if (!isExotic) {
//                Assert.fail();
//            }
//        } else Assert.fail();
//        //Проверка поля таблицы Экзотический

    }
















}
