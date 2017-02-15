package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
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
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().editGroup();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "testEdited", null, "test56");
        app.getGroupHelper().fillGroupParams(group);
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnGroupsPage();
        List<GroupData> after = app.getGroupHelper().getListGroup();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

        before.remove(before.size() - 1);
        before.add(group);

        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(before, after);
    }

    // так как мы знаем id, то можем просто модифицировать список на нужные значения
    @Test
    public void testEditGroupByID() {
        app.getNavigationHelper().gotoGroups();
        if (!app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("testEdit", "tes54", null));
        }

        List<GroupData> groups = app.getGroupHelper().getListGroup();
        // так как парядковый номер не меняется, избегаем дублирования за счет создания переменной
        int numGroup = groups.size() - 1;

        app.getGroupHelper().selectGroup(numGroup);
        app.getGroupHelper().editGroup();
        groups.get(numGroup).setData(new GroupData("testEdited", null, "test56"));
        app.getGroupHelper().fillGroupParams(groups.get(numGroup));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnGroupsPage();
        List<GroupData> after = app.getGroupHelper().getListGroup();

        //проверяем размерность
        Assert.assertEquals(after.size(), groups.size());

        Comparator<? super GroupData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        groups.sort(byId);
        after.sort(byId);

        //проверяем, что все совпадает
        Assert.assertEquals(groups, after);
    }
}
