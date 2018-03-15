import Common.LinkCrawler;
import Common.Navigate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by micro on 3/13/2018.
 */
public class Challenge_Six_Test {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();

    }

    @Test
    public void challengeSix(ITestContext ctx){
        Navigate nav = new Navigate(driver);
        String url = ctx.getCurrentXmlTest().getParameter("url");
        nav.loadPage(url);
        SkiUtah_PO ski = new SkiUtah_PO(driver);
        ski.verifyPageLoaded();
        LinkCrawler crawler = new LinkCrawler(driver);
        crawler.getlinks(url);
        crawler.crawlLinks(url);


    }
    @AfterClass
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }


    }
}
