import Common.Navigate;
import Common.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by micro on 3/5/2018.
 */
public class Challenge_Five_Test {
    WebDriver driver;
    Report report ;
    ITestContext ctx;

    @BeforeClass
    public void setup(ITestContext ctx){
        driver = new ChromeDriver();
        this.ctx = ctx;
        report =  new Report();
    }

    @Test
    public void challengeOne(){
        Navigate nav = new Navigate(driver);
        nav.loadPage(ctx.getCurrentXmlTest().getParameter("url"));
        String title = "Utah Ski Trip";
        if (!nav.getTitle().contains(title)){
            report.fail("Failed to load Search page");
        }
        Members_Listing_PO member = new Members_Listing_PO(driver);

        String what = ctx.getCurrentXmlTest().getParameter("what");
        String resort = ctx.getCurrentXmlTest().getParameter("resort");
        String subCategory = ctx.getCurrentXmlTest().getParameter("subCategory");

        List<WebElement> results = member.enterSearch(what,resort,subCategory);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
