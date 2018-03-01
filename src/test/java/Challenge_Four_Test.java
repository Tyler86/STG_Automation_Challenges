import Common.Navigate;
import Common.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Challenge_Four_Test {
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
        String title = "Ski Utah";
        if (!nav.getTitle().contains(title)){
            report.fail("Failed to load Home page");
        }
        SkiUtah_PO skiUtah  = new SkiUtah_PO(driver);
        skiUtah.clickOnExploreUtah();
        String resort = "nordic valley";
        skiUtah.clickOnResort(resort);
        skiUtah.printTimeFromAirport(resort);


    }
    @AfterClass
    public void tearDown(){
       if (driver!=null){
            driver.quit();
        }


    }
}
