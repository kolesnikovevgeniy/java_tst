package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MyK on 28.01.17.
 */
public class TestEditGroup extends TestBase{

    @BeforeMethod
    public void before() {
        app.goTo().groups();
        if (app.groups().all().size() == 0)
        {
            app.groups().create( new GroupData().withName("test1").withHeader("testheader").withFooter("blabla"));
        }
    }

    // так как мы знаем id, то можем просто модифицировать список на нужные значения
    @Test
    public void testEditGroupByID() {
        Groups groups = app.groups().all();

        app.groups().edit(groups, new GroupData().withName("test1").withHeader("testheader").withFooter("blabla"), groups.iterator().next().getId());
        Groups after = app.groups().all();

        //проверяем размерность
        assertThat(after.size(), equalTo(groups.size()));

        //проверяем идентификаторы
        assertThat(after, equalTo(groups));
    }
}
