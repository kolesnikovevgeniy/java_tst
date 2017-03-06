package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteGroup extends TestBase{

    @BeforeMethod
    public void before() {
        app.goTo().groups();
        if (app.groups().all().size() == 0)
        {
            app.groups().create( new GroupData().withName("test1").withHeader("testheader").withFooter("blabla"));
        }
    }

    @Test
    public void testDeleteGroup() {

        Set<GroupData> before = app.groups().all();
        GroupData deletedGroup = before.iterator().next();
        //int idToDelete = before.get(before.size()-1).getId();
        app.groups().delete(deletedGroup.getId());
        Set<GroupData> after = app.groups().all();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(deletedGroup);
        Assert.assertEquals(after, before);
    }
}
