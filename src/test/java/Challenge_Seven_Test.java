
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Challenge_Seven_Test {

    private  WebDriver driver;

    @BeforeClass
    public  void setupClass() {

        //WebDriverManager.chromedriver().version("2.33").setup();
        WebDriverManager.iedriver().arch32().setup();
        driver = new InternetExplorerDriver();
    }

    @AfterClass(alwaysRun = true)
    public void teardown() throws IOException {
        System.out.println("close driver");
        if (driver != null) {
            System.out.println("closed");
            driver.quit();
        }
//        WindowsUtils.killByName("IEDriverServer.exe");
    }

    @Test
    public void test() {
        driver.get("https://google.com");
        // Your test code here
    }
}
