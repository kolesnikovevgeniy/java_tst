package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteGroup extends TestBase{



    @Test
    public void testDeleteGroup() {
        app.goTo().groups();
        if (app.groups().list().size() == 0)
        {
            app.groups().create(new GroupData("testDelete", "tes54", null));
        }
        List<GroupData> before = app.groups().list();
        int idToDelete = before.get(before.size()-1).getId();
        app.groups().delete(idToDelete);
        List<GroupData> after = app.groups().list();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() -1);

        try {
            before.remove(GroupData.getIndexById(before, idToDelete));
        }catch(IndexOutOfBoundsException ioobe) {
            throw new TestException("Идентификатор ("+ idToDelete +") в списке групп не найден.");
        }

        Comparator<? super GroupData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }
}
