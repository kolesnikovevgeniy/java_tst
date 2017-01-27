package addressbook.control;

import addressbook.model.GroupData;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class TestCreateGroup {
    private FirefoxDriver wd;
    public TestCreateGroup(FirefoxDriver wd)
    {
        this.wd = wd;
    }

    public void testCreateGroup() {
        gotoGroupCreate();
        clickCreateNewGroup();
        fillGroupParams(new GroupData("test", "test1", "test2"));
        clickAddGroup();
        returnGroupsPage();
    }

    private void returnGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    private void clickAddGroup() {
        wd.findElement(By.name("submit")).click();
    }

    private void fillGroupParams(GroupData groupData) {
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

    private void clickCreateNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    private void gotoGroupCreate() {
        wd.findElement(By.linkText("groups")).click();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
