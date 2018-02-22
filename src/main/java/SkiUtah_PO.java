import Common.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SkiUtah_PO {
    WebDriver driver;
    WebDriverWait wait;
    Report report;
    public SkiUtah_PO(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(this.driver, 60);
        report= new Report();

    }

    public void clickonTopNavLink(String page){
        try {
            String linkX= "//a[@title = '"+page+"']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linkX)));
            WebElement link = driver.findElement(By.xpath(linkX));
            link.click();
            link.click();
        } catch (Exception e) {
            report.fail("Failed to navigate to " + page);
        }
    }
    public void hooverOver(String link){
        try {
            String linkX= "//a[@title = '"+link+"']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linkX)));
            Actions action = new Actions(driver);
            WebElement hoover = driver.findElement(By.xpath(linkX));
            action.moveToElement(hoover).build().perform();
        } catch (Exception e) {
            report.fail("Failed to hoover over navigation link " + link);
        }
    }
    public void clickOnSubMenu(String linkText){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
            WebElement link = driver.findElement(By.linkText(linkText));
            link.click();
        } catch (Exception e) {
            report.fail("Failed to hoover over navigation link " + linkText);
        }

    }






}
