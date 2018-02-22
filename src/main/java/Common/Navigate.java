package Common;

import Common.Report;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigate {
    WebDriver driver;
    WebDriverWait wait;
    Report report;

    public Navigate(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(this.driver, 60);
        report= new Report();
    }


    public void loadPage(String url){
        try {
            driver.get(url);
        } catch (Exception e) {
            report.fail("Failed to navigate to " + url);
        }
    }

    public String getTitle(){
        String title="";
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title")));
            title = driver.findElement(By.xpath("//title")).getAttribute("innerHTML");
        } catch (Exception e) {
            report.fail("Failed to get title");
        }
        return  title;
    }

}
