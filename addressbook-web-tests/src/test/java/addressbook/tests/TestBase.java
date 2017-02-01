package addressbook.tests;

import addressbook.appmanager.ApllicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by MyK on 28.01.17.
 */
public class TestBase {

    protected ApllicationManager app = new ApllicationManager(BrowserType.IE);

    public TestBase()
    {

    }

    @BeforeMethod
    public void setUp() throws Exception
    {
        app.init();
    }


    @AfterMethod
    public void tearDown()
    {
        app.stop();
    }


}