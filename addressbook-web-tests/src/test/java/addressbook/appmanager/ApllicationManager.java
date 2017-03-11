package addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.*;
/**
 * Created by MyK on 28.01.17.
 */
public class ApllicationManager {
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private WebDriver wd;
    private String browser;

    private Properties properties;
    // настройки таймаутов
    public static int WAIT_ELEMENT_TIMEOUT = 3;
    public static int STANDART_TIMEOUT = 60;

    public ApllicationManager(String browser){
        this.browser = browser;
        this.properties = new Properties();
    }
    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File("src/test/resources/" +target+ ".prop")));
        if (browser.equals(BrowserType.FIREFOX))
        {
            wd = new FirefoxDriver();
        }
        else if (browser.equals(BrowserType.IE))
        {
            wd = new InternetExplorerDriver();
        }
        else if (browser.equals(BrowserType.CHROME))
        {
            wd = new ChromeDriver();
        }

        wd.manage().timeouts().implicitlyWait(ApllicationManager.STANDART_TIMEOUT, TimeUnit.SECONDS);
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        wd.get(properties.getProperty("web.targetURL"));
        sessionHelper.login(properties.getProperty("web.login"),properties.getProperty("web.password"));
    }


    public void stop() {
        wd.quit();
    }

    public ContactHelper contacts() {
        return contactHelper;
    }

    public GroupHelper groups() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }
}
