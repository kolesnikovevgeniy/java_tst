package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by MyK on 28.01.17.
 */
public class TestEditGroup extends TestBase{
    @Test
    public void testEditGroup() {
        app.getNavigationHelper().gotoGroups();
        app.getGroupHelper().selectAnyGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupParams(new GroupData("test11111111111111111111", "test1", "test2"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnGroupsPage();
    }
}
