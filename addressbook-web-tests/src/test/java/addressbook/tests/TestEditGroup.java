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
        if (!app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("test8", "tes54", null));
        }
        app.getGroupHelper().selectAnyGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupParams(new GroupData("test11ret1", null, "test56"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnGroupsPage();
    }
}
