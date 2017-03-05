package addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

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

    // настройки таймаутов
    public static int WAIT_ELEMENT_TIMEOUT = 3;
    public static int STANDART_TIMEOUT = 60;

    public ApllicationManager(String browser){
        this.browser = browser;
    }
    public void init() {
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
        wd.get("http://localhost/addressbook/");
        sessionHelper.login("admin", "secret");
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
