package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by MyK on 28.01.17.
 */
public class NavigationHelper {

    FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd)
    {
        this.wd = wd;
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void gotoGroupCreate() {
        wd.findElement(By.linkText("groups")).click();
    }
}
