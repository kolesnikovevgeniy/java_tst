package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TestCreateGroup extends TestBase {
    @Test
    public void testCreateGroup() {
        app.goTo().groups();
        Set<GroupData> before = app.groups().all();
        GroupData group = new GroupData().withName("test1").withHeader("testheader").withFooter("blabla");
        app.groups().create(group);
        app.goTo().groups();
        Set<GroupData> after = app.groups().all();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //after.sort(byId);
        //before.sort(byId);

        Assert.assertEquals(after, before);
    }

}
