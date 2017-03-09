package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateGroup extends TestBase {
    @Test
    public void testCreateGroup() {
        app.goTo().groups();
        Groups before = app.groups().all();
        GroupData group = new GroupData().withName("test1").withHeader("testheader").withFooter("blabla");
        app.groups().create(group);
        app.goTo().groups();


        //проверяем размерность
        assertThat(before.size() + 1, equalTo(app.groups().count()));
        Groups after = app.groups().all();
        //проверяем идентификаторы
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testCreateBadGroup() {
        app.goTo().groups();
        Groups before = app.groups().all();
        GroupData group = new GroupData().withName("test1'").withHeader("testheader").withFooter("blabla");
        app.groups().create(group);
        app.goTo().groups();

        //проверяем размерность
        assertThat(before.size(), equalTo(app.groups().count()));

        Groups after = app.groups().all();
        //проверяем идентификаторы
        assertThat(after, equalTo(before));
    }
}
