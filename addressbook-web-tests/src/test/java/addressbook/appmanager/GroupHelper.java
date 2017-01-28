package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by MyK on 28.01.17.
 */
public class GroupHelper {

    FirefoxDriver wd;

    public GroupHelper(FirefoxDriver wd)
    {
        this.wd = wd;
    }

    public void returnGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void clickAddGroup() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGroupParams(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.xpath("//div[@id='content']/form")).click();
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void clickCreateNewGroup() {
        wd.findElement(By.name("new")).click();
    }
}
