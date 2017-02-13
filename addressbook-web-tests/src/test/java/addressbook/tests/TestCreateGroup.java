package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.util.List;

public class TestCreateGroup extends TestBase {
    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().gotoGroups();
        List<GroupData> before = app.getGroupHelper().getListGroup();
        app.getGroupHelper().createGroup(new GroupData("test8", "tes54", null));
        app.getNavigationHelper().gotoGroups();
        List<GroupData> after = app.getGroupHelper().getListGroup();
    }

}
