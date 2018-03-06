import Common.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by micro on 3/5/2018.
 */
public class Members_Listing_PO {
    WebDriver driver;
    WebDriverWait wait;
    Report report;
    public Members_Listing_PO(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(this.driver, 60);
        report= new Report();

    }

    private void whatDropDown(String field){
        try {
            String whatx ="//select[@name=\"filter-category-select\"]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(whatx)));
            WebElement element =  driver.findElement(By.xpath(whatx));
            Select sel = new Select(element);
            sel.selectByVisibleText(field);
        } catch (Exception e) {
            report.fail("Failed to click on What drop down value");
        }


    }
    private void byResortDropDown(String field){
        try {
            String resortx ="//select[@name=\"filter-resort-select\"]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resortx)));
            WebElement element =  driver.findElement(By.xpath(resortx));
            Select sel = new Select(element);
            sel.selectByVisibleText(field);
        } catch (Exception e) {
            report.fail("Failed to click on By Resort drop down value");

        }
    }
    private void subCategory(String field){
        try {
            String subCategoryx ="//select[@name='filter-sub-category-select']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(subCategoryx)));
            WebElement element =  driver.findElement(By.xpath(subCategoryx));
            Select sel = new Select(element);
            sel.selectByVisibleText(field);
        } catch (Exception e) {
            report.fail("faild to click on Subcategory drop down value");
        }
    }
    private void ok(){
        try {
            String okBtnx = "//input[@name='filter-form-submit']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(okBtnx)));
            WebElement okBtn = driver.findElement(By.xpath(okBtnx));
            okBtn.click();
        } catch (Exception e) {
            report.fail("Failed to click on OK");
        }

    }
    private List<WebElement> getResults(String what){
        String waitForx ="//h1[contains(text(),'"+what+"')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitForx)));
        String resultsx = "//div[@class = 'ListingResults-item']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultsx)));
        return driver.findElements(By.xpath(resultsx));

    }
    public List<WebElement> enterSearch(String what, String resort,String subCategory){
        whatDropDown(what);
        byResortDropDown(resort);
        subCategory(subCategory);
        ok();
        return getResults(what);
    }




}
