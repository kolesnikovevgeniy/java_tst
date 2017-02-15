package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
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
        if (after.size() < 1)
        {
            return;
        }
        Assert.assertEquals(after.size() - 1, before.size());

        before.remove(before.size() - 1);

        Comparator<? super GroupData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }
}
