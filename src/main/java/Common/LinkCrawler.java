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




    /*public void crawlLinks(String baseUrl){
        try {
            getLinks(baseUrl);
            for (String url:urls) {
                System.out.println(urls.size());
                    System.out.println("Navigating to " + url);
                    driver.navigate().to(url);
                    getLinks(baseUrl);

            }
        } catch (Exception e) {
            report.fail("Failed to navigate Links List");
        }
    }*/

   /* public void printBadUrls(){
        System.out.println("------------Bad Urls--------------------");

        for (String badurl: badUrls) {
            System.out.println(badurl);

        }
    }*/

   /* public void getLinks(String baseUrl){
        System.out.println("Crawling for links");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("a")));
        } catch (Exception e) {
            System.out.println("no link tags are present on the page");
        }
        final List<String> linksToClick = new ArrayList<String>();
        final List<String> tempBadLinks = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        elements.addAll(driver.findElements(By.tagName("img")));
        String url="";
        int status;
        for (WebElement e: elements ) {
            try {
                if (e.getAttribute("href")!=null){
                    url = e.getAttribute("href");
                    if (url.contains(baseUrl)
                         && !linksToClick.contains(url)
                         && !urls.contains(url)){
                        status = urlChecker.checkUrl(url);
                        if  (status == 200) {
                            linksToClick.add(url);
                        }
                        else{
                            tempBadLinks.add(url + " returned " + status);
                        }
                    }
                }
            } catch (Exception e1) {
                System.out.println(" unable to get url from element "+ e.getAttribute("href"));
            }
        }
        urls = new ArrayList<String>() { { addAll(urls); addAll(linksToClick); } };
        badUrls = new ArrayList<String>() { { addAll(urls); addAll(tempBadLinks); } };
        System.out.println("completed page crawl");

    }
*/
    public List<String> findLinks(String baseUrl){
        List<String> links = new ArrayList<String>();

        try {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("a")));
            } catch (Exception e) {
                System.out.println("no link tags are present on the page");
            }
            List<WebElement> elements = driver.findElements(By.tagName("a"));
            elements.addAll(driver.findElements(By.tagName("img")));
            String url="";
            for (WebElement e: elements ) {
                try {
                    if (e.getAttribute("href")!=null){
                        url = e.getAttribute("href");
                        if (url.contains(baseUrl)
                             && !links.contains(url)){
                                links.add(url);
                        }
                    }
                } catch (Exception e1) {
                    System.out.println("unable to get url from element "+ e.getAttribute("href"));
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
