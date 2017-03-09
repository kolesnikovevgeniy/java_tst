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
        app.goTo().groups();
        if (app.groups().all().size() == 0)
        {
            app.groups().create( new GroupData().withName("test1").withHeader("testheader").withFooter("blabla"));
        }
    }

    @Test
    public void testDeleteGroup() {

        Groups before = app.groups().all();
        GroupData deletedGroup = before.iterator().next();
        app.groups().delete(deletedGroup.getId());


        //проверяем размерность
        assertThat(app.groups().count(), equalTo(before.size() - 1));
        Groups after = app.groups().all();

        //проверяем идентификаторы
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
