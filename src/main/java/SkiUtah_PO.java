import Common.Report;
import com.sun.imageio.plugins.wbmp.WBMPImageReader;
import org.openqa.selenium.*;
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

    public void clickOnExploreUtah(){
        try {


            String exploreUtahx = "(//h1[@class = 'map-Container-menuTitle'])[2]/span[1]";
           wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(exploreUtahx)));
            WebElement exploreUtah = driver.findElement(By.xpath(exploreUtahx));
            exploreUtah.click();//sendKeys(Keys.SPACE);
           // ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)", "");
        } catch (Exception e) {
            report.fail("Failed to click on Explore Utah");
        }
    }
    public void clickOnResort(String resortName) {
        try {
            String waitForMenuToClose = "//div[@class = 'map-Container-compare is-expanded']";
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(waitForMenuToClose)));
            resortName = resortName.toLowerCase().replace(" ", "-");
            String resortx= "(//label[contains(@for,'"+resortName+"')])[1]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resortx)));
            WebElement resort = driver.findElement(By.xpath(resortx));
            resort.click();
        } catch (Exception e) {
            report.fail("Failed To click on Resort");
        }
    }
    public void printTimeFromAirport(String resortName){
        try {
            resortName = resortName.toLowerCase().replace(" ", "-");
            String timeFromairportX = "(//div[contains(@class,'"+resortName+"')])[1]//span[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(timeFromairportX)));
            WebElement timeFromAirport = driver.findElement(By.xpath(timeFromairportX));
            String time = timeFromAirport.getText();
            System.out.println("Time from the airport to "+resortName+" is "+time +" minutes");
        } catch (Exception e) {
            report.fail("Failed to get The Time from the Airport");
        }
    }
    public void verifyPageLoaded(){
        try {
            String homePageTitle = "Ski Utah | Utah Ski Resorts";
            wait.until(ExpectedConditions.titleContains(homePageTitle));
        } catch (Exception e) {
            report.fail("Failed to load Home Page ");
        }
    }





}
