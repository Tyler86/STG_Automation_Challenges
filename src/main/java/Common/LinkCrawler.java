package Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkCrawler {
    WebDriver driver;
    WebDriverWait wait;
    Report report;
    UrlChecker urlChecker;
    HashMap<String,Integer> dictionary = new HashMap<String, Integer>();
    List<String> urls= new ArrayList<String>();
    List<String> visitedPages = new ArrayList<String>();
    List<String> newUrls;
    List<String> badUrls = new ArrayList<String>();



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
           // elements.addAll(driver.findElements(By.tagName("//img[contains(@href,'"+baseUrl+"')]")));
            String url="";
            for (WebElement e: elements ) {
                try {
                        url = e.getAttribute("href");
                    if (url.endsWith("/")){
                        url = url.substring(0,url.lastIndexOf("/"));
                    }
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
    public void crawlpages(String baseUrl, Boolean createDictionary, Boolean checkPictures){

        /// crawls  page for links
        urls.add(0,baseUrl);

        String link = "";
        int status=0;
        for (int i=0; i<urls.size(); i++) {

            System.out.println(urls.size() + " current index " + i);
            link = urls.get(i);

            if (!visitedPages.contains(link)) {
                System.out.println(link);
                status = getUrlStatus(link);

                if (status == 200) {
                    driver.navigate().to(link);
                    visitedPages.add(link);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
                    if (createDictionary == true) {
                        getWords();
                    }
                    if (checkPictures == true) {
                        crawlImages();
                    }

                    newUrls = findLinks(baseUrl);
                    for (String newLink : newUrls) {
                        if (!urls.contains(newLink)) {
                            urls.add(newLink);
                        }
                    }
                } else {
                    badUrls.add(link + "returned status of " + status);
                }
                if (i == 6) {
                    break;
                }
            }

            }
            System.out.println("------There were " + badUrls.size() + " Bad Urls found-------");
            for (String badurl : badUrls) {
                System.out.println(badurl);
            }

        // print dictionary to screen and save to a file
       if (createDictionary==true){
            printDictionary();
       }
    }


    public void crawlImages(){
        try {
            String imageListx = "//img[contains(@src,'')]";
            List<WebElement> imageList;
            List<String> badImages = new ArrayList<String>();
            imageList = driver.findElements(By.xpath(imageListx));
            String url ="";
            int urlStatus;
            for(int i=0; i < imageList.size(); i++){

                if (imageList.get(i).getAttribute("src") !=null) {
                    url = imageList.get(i).getAttribute("src");

                    try {
                        urlStatus = urlChecker.checkUrl(url);
                        if (urlStatus !=200){
                            badImages.add("bad image link: "+ url);
                        }
                    } catch (Exception e) {
                        badImages.add(" failed to get url status of: " +url);
                    }
                }
            }
            if (badImages.size()>0) {
                System.out.println("----------------Troubled Images------------------------------");
                for (String troubledImage : badImages) {
                    System.out.println(troubledImage);
                }
                badImages.clear();
            }
        } catch (Exception e) {
            System.out.println("Failed to Crawl Images");
        }

    }



    public void printDictionary(){
        try {
            File file = new File("C:\\temp\\dictionary.txt");
            file.delete();
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("-------------------------Dictionary-----------------------");
            bw.newLine();
            for ( String key: dictionary.keySet()) {
                System.out.println(key +": " +dictionary.get(key));
                bw.write(key +": " +dictionary.get(key));
                bw.newLine();
            }
            bw.write(" some new test");

            // Close connection
            bw.close();
        } catch (Exception e) {
            System.out.println("failed to write dictionary to screen and text document");
        }




    }
    private int getUrlStatus(String link){
        int status=0;
        try {
            status = urlChecker.checkUrl(link);
        } catch (IOException e) {
            System.out.println("unable to get link status");
        }
        return status;
    }

    public void getWords(){
        String text;
        try {
                text = driver.findElement(By.xpath("//body")).getText();
                String[] alltext = text.split(" ");
                for (String word : alltext) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z-]", "");
                    if (!dictionary.containsKey(word) && !word.equals("")) {
                        dictionary.put(word, 1);
                    } else if (!word.equals("")) {
                        int temp = dictionary.get(word);
                        dictionary.remove(word);
                        dictionary.put(word, temp + 1);
                    }
                }

        } catch (Exception e) {
            System.out.println("failed to text on " + driver.getCurrentUrl() );
        }
    }




}
