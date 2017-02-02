package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class TestCreateGroup extends TestBase {
    public TestCreateGroup()
    {

    }

    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().gotoGroups();
        app.getGroupHelper().clickCreateNewGroup();
        app.getGroupHelper().fillGroupParams(new GroupData("test8", "tes54", null));
        app.getGroupHelper().clickAddGroup();
        app.getGroupHelper().returnGroupsPage();
    }

}
