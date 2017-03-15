package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Created by MyK on 14.03.17.
 */
public class TestDeleteContactFromGroup extends  TestBase{

    @BeforeMethod
    public void verifyGroups()
    {
        if (app.db().groups().size() > 0)
            return;

        app.goTo().groups();
        app.groups().create(new GroupData().withName("name1").withHeader("H1").withFooter("F1"));
        Contacts contacts = app.db().contacts();

        if (contacts.size() == 0)
        {
            app.contacts().create(new ContactData().withFirstname("Test1").withMidlename("testmadle").withLastname("testlast"), true);
            app.goTo().homePage();
        }
        contacts = app.db().contacts();

        for(ContactData c: contacts)
        {
            Groups groups_from_contact =  app.db().groups_in_contact(c);
            if (groups_from_contact.size() != app.db().groups().size())
            {
                // проверяем в какой не состоит
                Groups contact_not_in_groups = app.db().contact_not_in_groups(c);
                if (contact_not_in_groups.size() > 0 )
                    app.contacts().to_group(c, contact_not_in_groups.iterator().next());
            }
        }

    }

    @Test
    public void testDeleteContactFromGroup() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();
        GroupData group = app.groups().all_from_home().iterator().next();
        app.contacts().without_group(contact, group);

        assertThat(app.db().groups_in_contact(contact).stream().filter((g) -> g.getId() == group.getId()).count(),
                equalTo(0L));
    }
}
