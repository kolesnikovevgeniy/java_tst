package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteGroup extends TestBase{

    @BeforeMethod
    public void before() {
        if (app.db().groups().size() == 0)
        {
            app.goTo().groups();
            app.groups().create( new GroupData().withName("test1").withHeader("testheader").withFooter("blabla"));
        }
    }

    @Test
    public void testDeleteGroup() {

        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().groups();
        app.groups().delete(deletedGroup.getId());


        //проверяем размерность
        assertThat(app.groups().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();

        //проверяем идентификаторы
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGroupListInUI();
    }
}
