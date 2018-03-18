package Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LinkCrawler {
    WebDriver driver;
    WebDriverWait wait;
    Report report;
    UrlChecker urlChecker;


    public LinkCrawler(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(this.driver, 60);
        report= new Report();
        urlChecker = new UrlChecker();
    }

    public List<String> findLinks(String baseUrl){
        List<String> links = new ArrayList<String>();

        try {
            try {
            } catch (Exception e) {
                System.out.println("no link tags are present on the page");
            }
            List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@href,'"+baseUrl+"')]"));
            elements.addAll(driver.findElements(By.tagName("//img[contains(@href,'"+baseUrl+"')]")));
            String url="";
            for (WebElement e: elements ) {
                try {
                        url = e.getAttribute("href");
                        if (!links.contains(url)){
                                links.add(url);
                        }
                } catch (Exception e1) {
                    System.out.println("unable to get url from element "+url);
                }
            }
        } catch (Exception e) {
            System.out.println("unable to get links On this url " + driver.getCurrentUrl());
        }
        return links;
    }

    public List<String> checkForDuplicates(List<String> originalLinks, List<String> newLinks ){
        try {
            for (String link: newLinks) {
                if (!originalLinks.contains(link)){
                    originalLinks.add(link);
                }
            }
        } catch (Exception e) {
            System.out.println("unable to check for duplicates On this url " + driver.getCurrentUrl());
        }
        return originalLinks;
    }




/*    public String linkStatus(URL url){
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.connect();
            String responseMessage = http.getResponseMessage();
            http.disconnect();
            return responseMessage;
        } catch (Exception e) {
           return e.getMessage();

    }

 }*/



}
