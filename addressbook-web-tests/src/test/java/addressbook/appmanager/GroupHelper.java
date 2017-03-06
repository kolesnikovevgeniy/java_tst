package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

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

    public void clickEditGroup()
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

    public void create(GroupData groupData) {
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

    public List<GroupData> list()
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

    public Set<GroupData> all()
    {
        Set<GroupData> groupsData = new HashSet<GroupData>();
        setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        setTimeout(ApllicationManager.STANDART_TIMEOUT);
        for(WebElement e : elements)
        {
            groupsData.add(new GroupData(Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value")), e.getText(), null, null));
        }
        return groupsData;
    }

    public void delete(int idToDelete) {
        selectGroupById(idToDelete);
        deleteSelectedGroup();
        returnGroupsPage();
    }

    public void edit(Set<GroupData> groups, GroupData group, int idToEdit) {
        selectGroupById(idToEdit);
        clickEditGroup();
        for (Iterator<GroupData> it = groups.iterator(); it.hasNext(); ) {
            GroupData g = it.next();
            if (g.equals(new GroupData().withId(idToEdit))) {
                g.setData(group);
            }
        }
        //groups.get(GroupData.getIndexById(groups, idToEdit)).setData(group);
        fillGroupParams(group);
        updateGroup();
        returnGroupsPage();

    }
}
