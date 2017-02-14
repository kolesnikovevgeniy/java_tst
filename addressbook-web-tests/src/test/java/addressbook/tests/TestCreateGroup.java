package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class TestCreateGroup extends TestBase {
    @Test
    public void testCreateGroup() {
        app.getNavigationHelper().gotoGroups();
        List<GroupData> before = app.getGroupHelper().getListGroup();
        GroupData group = new GroupData("tes5", "tes54", null);
        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().gotoGroups();
        List<GroupData> after = app.getGroupHelper().getListGroup();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(after, before);
    }

}
