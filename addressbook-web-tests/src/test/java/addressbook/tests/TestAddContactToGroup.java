package addressbook.tests;

import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MyK on 14.03.17.
 */
public class TestAddContactToGroup extends TestBase{

    @BeforeTest
    public void verifyGroups()
    {
        if (app.db().groups().size() > 0)
            return;

        app.goTo().groups();
        app.groups().create(new GroupData().withName("name1").withHeader("H1").withFooter("F1"));
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        Contacts contacts = app.contacts().all();
        app.goTo().groups();
        Groups groups = app.groups().all();
        app.goTo().homePage();
        app.contacts().to_group(contacts.iterator().next(), groups.iterator().next());
        //ContactData cDeleted = contacts.iterator().next();
        //ContactData cAdded = new ContactData().withFirstname("t1").withLastname("t2").withMidlename("t3");
        //cAdded.withId(cDeleted.getId());
        //app.contacts().edit(contacts, cAdded, cDeleted.getId(), false, false);
        //app.goTo().homePage();

        //проверяем размерность
        //assertThat(app.contacts().count(), equalTo(contacts.size()));

        //Contacts after = app.db().contacts();

        //assertThat(after, equalTo(contacts.without(cDeleted).withAdded(cAdded)));
        //verifyContactListInUI();
    }
}
