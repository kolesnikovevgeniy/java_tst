package addressbook.tests;

import addressbook.appmanager.ApllicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


/**
 * Created by MyK on 28.01.17.
 */
public class TestBase {

    public static ApllicationManager app = new ApllicationManager(BrowserType.CHROME);

    public TestBase()
    {

    }

    @BeforeSuite
    public void setUp() throws Exception
    {
        app.init();
    }


    @AfterSuite
    public void tearDown()
    {
        app.stop();
    }


}
