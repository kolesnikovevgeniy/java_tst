package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateGroup extends TestBase {
    @DataProvider
    public Iterator<Object[]> validGroupsXML() throws IOException {
        List<Object[]> groups = new ArrayList<>();

        File file = new File("src/test/resources/groups.xml");
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while(line != null)
        {
            String[] data = line.split(";");
            groups.add(new Object[] {new GroupData().withName(data[0]).withHeader(data[1]).withFooter(data[2])});
            line = reader.readLine();
        }
        XStream stream = new XStream();
        stream.processAnnotations(GroupData.class);
        List<GroupData> groupsXML = (List<GroupData>)stream.fromXML(file);
        return groupsXML.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        //return groups.iterator();
    }
    @Test(dataProvider = "validGroupsXML")
    public void testCreateGroup(GroupData group) {
        app.goTo().groups();
        Groups before = app.groups().all();
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
