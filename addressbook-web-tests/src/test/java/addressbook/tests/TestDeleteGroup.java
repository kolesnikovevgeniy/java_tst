package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteGroup extends TestBase{

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("test8", "tes54", null));
        }
        app.getGroupHelper().selectAnyGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnGroupsPage();
    }
}
