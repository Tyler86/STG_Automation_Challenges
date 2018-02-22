import Common.Navigate;
import Common.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Challenge_Two_Test {
    WebDriver driver;
    Report report;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        report = new Report();

    }

    @Test
    public void challengeOne(ITestContext ctx) {
        Navigate nav = new Navigate(driver);
        nav.loadPage(ctx.getCurrentXmlTest().getParameter("url"));
        String title = "Ski Utah";
        if (!nav.getTitle().contains(title)){
            report.fail("Failed to load Home page");
        }
        SkiUtah_PO ski = new SkiUtah_PO(driver);
        String goToPage = "Deals";
        ski.clickonTopNavLink(goToPage);
        if (!nav.getTitle().contains(title)){
            report.fail("Failed to load Home page");
        }

    }
    @AfterClass
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }


    }

}
