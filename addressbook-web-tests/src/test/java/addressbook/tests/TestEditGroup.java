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
        app.getGroupHelper().editGroupById(groups, new GroupData("testEdited", null, "test56"), groups.get(groups.size() - 1).getId());
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
