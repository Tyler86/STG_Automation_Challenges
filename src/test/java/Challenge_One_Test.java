import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Common.Navigate;

public class Challenge_One_Test {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();

    }

    @Test
    public void challengeOne(ITestContext ctx){
        Navigate nav = new Navigate(driver);
        nav.loadPage(ctx.getCurrentXmlTest().getParameter("url"));
        String title = "";
        title = nav.getTitle();
        System.out.println(title);

    }
    @AfterClass
    public void tearDown(){
       if (driver!=null){
           driver.quit();
       }


    }
}
