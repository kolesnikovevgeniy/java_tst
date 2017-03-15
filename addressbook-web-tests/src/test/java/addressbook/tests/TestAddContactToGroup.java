package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by MyK on 14.03.17.
 */
public class TestAddContactToGroup extends TestBase{

    @BeforeMethod
    public void verifyGroups()
    {
        if (app.db().groups().size() > 0)
            return;

        app.goTo().groups();
        app.groups().create(new GroupData().withName("name1").withHeader("H1").withFooter("F1"));
    }

    @BeforeMethod
    public void verifyContacts()
    {
        if (app.db().contacts().size() == 0)
        {
            app.contacts().create(new ContactData().withFirstname("Test1").withMidlename("testmadle").withLastname("testlast"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();
        Groups groups_from_contact = app.db().groups_in_contact(contact);


        // предусловие, сравниваем кол-во групп в контакте с общим количетсвом групп
        if (groups_from_contact.size() == app.db().groups().size())
        {
            app.goTo().groups();
            app.groups().create(new GroupData().withName("name1").withHeader("H1").withFooter("F1"));
            app.goTo().homePage();
        }

        // проверяем в какой не состоит
        GroupData contact_not_in_group = app.db().contact_not_in_groups(contact).iterator().next();
        assertThat(app.db().contact_not_in_groups(contact).size(), not(0));
        app.contacts().to_group(contact, contact_not_in_group);

        groups_from_contact = app.db().groups_in_contact(contact);
        //проверяем, что контакт добавился
        assertThat(groups_from_contact.stream().filter((g) -> {return g.getId() == contact_not_in_group.getId();}).count(),
                equalTo(1L));
    }
}
