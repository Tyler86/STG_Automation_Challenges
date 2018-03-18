
import Common.LinkCrawler;
import Common.Navigate;
import Common.UrlChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Challenge_Seven_Test {

    private  WebDriver driver;    UrlChecker urlchecker;


    @BeforeClass
    public  void setupClass() {
        driver = new ChromeDriver();
        urlchecker = new UrlChecker();

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
        List<String> urls;
        List<String> newUrls;
        List<String> badUrls = new ArrayList<String>();
        String baseUrl = "skiutah.com";

        HashMap<String,Integer> dictionary = new HashMap<String, Integer>();


        /// crawls home page for links
        urls = linkcrawler.findLinks(baseUrl);
        String link = "";
        int status=0;
        for (int i=0; i<urls.size(); i++){
            try {
                String text = driver.findElement(By.xpath("//body")).getText();
                String[] alltext = text.split(" ");
                for (String word: alltext) {
                    word =  word.toLowerCase().replaceAll("[^a-zA-Z-]", "");
                    if (!dictionary.containsKey(word)&& !word.equals("")){
                        dictionary.put(word,1);
                    }
                    else if(!word.equals("")){
                        int temp = dictionary.get(word);
                        dictionary.remove(word);
                        dictionary.put(word,temp+1);
                    }
                }
            } catch (Exception e) {
                System.out.println("failed to text on " + driver.getCurrentUrl() );
            }

            System.out.println(urls.size() + " current index " + i );
            link = urls.get(i);
            System.out.println(link);
            try {
                status = urlchecker.checkUrl(link);
            } catch (IOException e) {
                System.out.println("unable to get link status");
            }
            if (status == 200) {
                driver.navigate().to(link);

                newUrls = linkcrawler.findLinks(baseUrl);
                for (String newLink : newUrls) {
                    if (!urls.contains(newLink)) {
                        urls.add(newLink);
                    }
                }

            }
            else{
                badUrls.add(url + "returned status of " + status);
            }

        }


        // print dictionary to screen and file
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
}
