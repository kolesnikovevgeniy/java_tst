package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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

    public void createGroup(GroupData groupData) {
        clickCreateNewGroup();
        fillGroupParams(groupData);
        clickAddGroup();
        returnGroupsPage();
    }

    public void selectGroup(int num)
    {
        wd.findElements(By.name("selected[]")).get(num).click();
    }
    public void selectGroupById(int id)
    {
        wd.findElement(By.xpath("//input[@value='"+id+"']")).click();
    }

    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> getListGroup()
    {
        List<GroupData> groupsData = new ArrayList<GroupData>();
        setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        setTimeout(ApllicationManager.STANDART_TIMEOUT);
        for(WebElement e : elements)
        {
            groupsData.add(new GroupData(Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value")), e.getText(), null, null));
        }
        return groupsData;
    }
}
