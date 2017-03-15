package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        ContactData contact = app.contacts().all().iterator().next();
        GroupData group = app.groups().all_from_home().iterator().next();

        // предпроверка, сравниваем кол-во групп в контакте с общим количетсвом групп
        // проверяем в какой не состоит


        app.contacts().to_group(contact, group);

        //проверяем, что контакт добавился в выбранную группу через ВФ

        //проверяем, что контакт добавился
        assertThat(app.db().groups_in_contact(contact).stream().filter((g) -> g.getId() == group.getId()).count(),
                equalTo(1L));

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
