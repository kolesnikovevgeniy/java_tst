package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class TestCreateGroup extends TestBase {
    @Test
    public void testCreateGroup() {
        app.goTo().groups();
        List<GroupData> before = app.groups().list();
        GroupData group = new GroupData("tes5", "tes54", null);
        app.groups().create(group);
        app.goTo().groups();
        List<GroupData> after = app.groups().list();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(after, before);
    }

}
