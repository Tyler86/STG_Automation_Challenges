import Common.LinkCrawler;
import Common.Navigate;
import Common.UrlChecker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by micro on 3/13/2018.
 */
public class Challenge_Six_Test {
    WebDriver driver;
    UrlChecker urlchecker;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        urlchecker = new UrlChecker();
    }

    @Test
    public void challengeSix(ITestContext ctx){
        Navigate nav = new Navigate(driver);
        String url = ctx.getCurrentXmlTest().getParameter("url");
        nav.loadPage(url);
        SkiUtah_PO ski = new SkiUtah_PO(driver);
        ski.verifyPageLoaded();
        LinkCrawler linkcrawler = new LinkCrawler(driver);
        List<String> urls = new ArrayList<String>();
        List<String> badUrls = new ArrayList<String>();
        String baseUrl = "://www.skiutah.com";

        /// crawls home page for links
        urls = linkcrawler.findLinks(baseUrl);

        int status=0;
        for (int i=0; i<urls.size(); i++){
            System.out.println(urls.size() + " current index " + i );
            System.out.println(urls.get(i));
            driver.navigate().to(urls.get(i));
            try {
                status = urlchecker.checkUrl(urls.get(i));
            } catch (IOException e) {
                System.out.println(" unable to get url status for "+urls.get(i) );
            }

            if (status == 200) {
                urls = linkcrawler.checkForDuplicates(urls, linkcrawler.findLinks(baseUrl));

            }
            else{
                badUrls.add(url +"returned status of " +status);
            }
        }

        System.out.println("------Bad Urls " +badUrls.size()+ "-------");
        for (String badurl: badUrls) {
            System.out.println(badurl);
        }
    }
    @AfterClass
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }


    }
}
