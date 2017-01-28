package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by MyK on 28.01.17.
 */
public class NavigationHelper extends BaseHelper{

    public NavigationHelper(FirefoxDriver wd)
    {
        super(wd);
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

    public void gotoGroupCreate() {
        click(By.linkText("groups"));
    }
}
