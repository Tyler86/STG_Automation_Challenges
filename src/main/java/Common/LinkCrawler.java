package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkCrawler {
    WebDriver driver;
    WebDriverWait wait;
    Report report;
    List<String> urls;

    public LinkCrawler(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(this.driver, 60);
        report= new Report();
        urls = new LinkedList<String>();
    }


    public void crawlLinks(String baseUrl){
        try {
            System.out.println(urls.size());
            getlinks(baseUrl);
            for (String url:urls) {
                System.out.println("Navigating to " + url);
                driver.navigate().to(url);
                //getlinks(baseUrl);
            }
        } catch (Exception e) {
            report.fail("Failed to get urls");
        }
    }
    public void getlinks(String baseUrl){
        System.out.println("Crawling for links");
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@href,'skiutah.com')]"));
        String tempurl ="";
        for (WebElement element: elements ) {
            System.out.println("Checking link");
            if (element.getAttribute("href")!=null) {
                tempurl = element.getAttribute("href");
                if (tempurl.contains(baseUrl) && !urls.contains(tempurl))
                    System.out.println(tempurl);
                    urls.add(tempurl);
            }
        }
        System.out.println("completed page crawl");
    }





}
