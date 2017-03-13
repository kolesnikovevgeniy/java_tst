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
        if (app.db().groups().size() == 0)
        {
            app.goTo().groups();
            app.groups().create( new GroupData().withName("test1").withHeader("testheader").withFooter("blabla"));
        }
    }

    // так как мы знаем id, то можем просто модифицировать список на нужные значения
    @Test
    public void testEditGroupByID() {
        Groups groups = app.db().groups();
        app.goTo().groups();
        GroupData gDeleted = groups.iterator().next();
        GroupData gAdded = new GroupData().withName("test1").withHeader("testheader").withFooter("blabla");
        gAdded.withId(gDeleted.getId());
        app.groups().edit(groups,gAdded, gDeleted.getId());

        //проверяем размерность
        assertThat(app.groups().count(), equalTo(groups.size()));

        Groups after = app.db().groups();

        //проверяем идентификаторы
        assertThat(after, equalTo(groups.without(gDeleted).withAdded(gAdded)));
    }
}
