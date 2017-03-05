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
        app.goTo().groups();
        if (app.groups().list().size() == 0)
        {
            app.groups().create(new GroupData("testEdit", "tes54", null));
        }

        List<GroupData> groups = app.groups().list();
        // так как парядковый номер не меняется, избегаем дублирования за счет создания переменной
        app.groups().edit(groups, new GroupData("testEdited", null, "test56"), groups.get(groups.size() - 1).getId());
        List<GroupData> after = app.groups().list();

        //проверяем размерность
        Assert.assertEquals(after.size(), groups.size());

        Comparator<? super GroupData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        groups.sort(byId);
        after.sort(byId);

        //проверяем, что все совпадает
        Assert.assertEquals(groups, after);
    }
}
