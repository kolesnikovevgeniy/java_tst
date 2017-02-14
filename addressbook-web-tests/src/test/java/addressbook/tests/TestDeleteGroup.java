package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteGroup extends TestBase{

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("testDelete", "tes54", null));
        }
        List<GroupData> before = app.getGroupHelper().getListGroup();
        app.getGroupHelper().selectAnyGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnGroupsPage();
        List<GroupData> after = app.getGroupHelper().getListGroup();

        //проверяем размерность
        Assert.assertEquals(after.size() - 1, before.size());
    }
}
