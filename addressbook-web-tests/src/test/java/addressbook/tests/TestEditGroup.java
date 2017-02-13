package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by MyK on 28.01.17.
 */
public class TestEditGroup extends TestBase{
    @Test
    public void testEditGroup() {
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("testEdit", "tes54", null));
        }
        List<GroupData> before = app.getGroupHelper().getListGroup();
        app.getGroupHelper().selectAnyGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupParams(new GroupData("testEdited", null, "test56"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnGroupsPage();
        List<GroupData> after = app.getGroupHelper().getListGroup();
    }
}
