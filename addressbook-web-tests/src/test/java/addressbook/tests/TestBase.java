package addressbook.tests;

import addressbook.appmanager.ApllicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Created by MyK on 28.01.17.
 */
public class TestBase {

    public static ApllicationManager app = new ApllicationManager(BrowserType.FIREFOX);

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
