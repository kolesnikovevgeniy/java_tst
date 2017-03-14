package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.Assert;
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
public class TestEditContact extends TestBase{

    @BeforeMethod
    public void before() {
        if (app.db().contacts().size() == 0)
        {
            app.contacts().create(new ContactData().withFirstname("Test1").withMidlename("testmidle").withLastname("testlast"), true, true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testEditContactById() {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        ContactData cDeleted = contacts.iterator().next();
        ContactData cAdded = new ContactData().withFirstname("t1").withLastname("t2").withMidlename("t3");
        cAdded.withId(cDeleted.getId());
        app.contacts().edit(contacts, cAdded, cDeleted.getId(), false, false);
        app.goTo().homePage();

        //проверяем размерность
        assertThat(app.contacts().count(), equalTo(contacts.size()));

        Contacts after = app.db().contacts();

        assertThat(after, equalTo(contacts.without(cDeleted).withAdded(cAdded)));
        verifyContactListInUI();
    }


}
