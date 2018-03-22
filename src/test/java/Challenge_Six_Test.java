import Common.LinkCrawler;
import Common.Navigate;
import Common.UrlChecker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Challenge_Six_Test {
    WebDriver driver;
    UrlChecker urlchecker;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        urlchecker = new UrlChecker();
    }

    @Test
    public void challengeSix(ITestContext ctx)  {
        Navigate nav = new Navigate(driver);
        String url = ctx.getCurrentXmlTest().getParameter("url");
        nav.loadPage(url);
        SkiUtah_PO ski = new SkiUtah_PO(driver);
        ski.verifyPageLoaded();
        LinkCrawler linkcrawler = new LinkCrawler(driver);


        /// crawls Website for links
        String baseUrl = "skiutah.com";
        linkcrawler.crawlpages(baseUrl,false,false);

    }
    @AfterClass
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }


    }
}
