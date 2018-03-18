package Common;


import org.testng.Assert;

public class Report {

    public void fail(String failure){

        Assert.fail(failure);
    }


}
