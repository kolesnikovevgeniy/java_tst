package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by MyK on 14.03.17.
 */
public class TestDeleteContactFromGroup extends  TestBase{

    @BeforeTest
    public void verifyGroup()
    {

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
