package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by MyK on 28.01.17.
 */
public class GroupHelper extends BaseHelper{

    public GroupHelper(WebDriver wd)
    {
       super(wd);
    }

    public void returnGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void clickAddGroup() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGroupParams(GroupData groupData) {

        type(By.name("group_name"),groupData.getName());
        type(By.name("group_header"),groupData.getHeader());
        type(By.name("group_footer"),groupData.getFooter());
    }

    public void clickCreateNewGroup() {
        click(By.name("new"));
    }

    public void selectAnyGroup()
    {
        click(By.name("selected[]"));
    }

    public void editGroup()
    {
        click(By.name("edit"));
    }

    public void updateGroup()
    {
        click(By.name("update"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }
}
