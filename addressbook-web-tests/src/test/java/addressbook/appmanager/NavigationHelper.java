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

    public void gotoHomePage()
    {
        if (isElementPresent(By.id("maintable")))
            return;
        click(By.linkText("home"));
    }

    public void gotoGroups()
    {
        if (isElementPresent(By.tagName("h1")) &&
                wd.findElement(By.tagName("h1")).getText().equals("Groups") &&
                isElementPresent(By.name("new")))
        {
            return;
        }
        click(By.linkText("groups"));
    }
}
