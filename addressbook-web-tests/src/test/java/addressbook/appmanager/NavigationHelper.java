package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by MyK on 28.01.17.
 */
public class NavigationHelper extends BaseHelper{

    public NavigationHelper(WebDriver wd)
    {
        super(wd);
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

    public void gotoGroups() {
        click(By.linkText("groups"));
    }
}
