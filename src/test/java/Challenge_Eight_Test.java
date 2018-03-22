import Common.LinkCrawler;
import Common.Navigate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Challenge_Eight_Test {
        private WebDriver driver;

        @BeforeClass
        public  void setupClass() {
            driver = new ChromeDriver();
        }

        @AfterClass(alwaysRun = true)
        public void teardown() throws IOException {
            if (driver != null) {
                driver.quit();
            }
        }

        @Test
        public void test(ITestContext ctx) {
            Navigate nav = new Navigate(driver);
            String url = ctx.getCurrentXmlTest().getParameter("url");
            nav.loadPage(url);
            SkiUtah_PO ski = new SkiUtah_PO(driver);
            ski.verifyPageLoaded();
            LinkCrawler linkcrawler = new LinkCrawler(driver);
            linkcrawler.crawlpages(url,false,true);

        }

}
