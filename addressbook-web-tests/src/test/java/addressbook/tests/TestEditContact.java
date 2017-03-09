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
        app.goTo().homePage();
        if (app.contacts().all().size() == 0)
        {
            app.contacts().create(new ContactData().withFirstname("Test1").withMidlename("testmidle").withLastname("testlast"), true, true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testEditContactById() {
        app.goTo().homePage();
        Contacts contacts = app.contacts().all();
        app.contacts().edit(contacts, new ContactData().withFirstname("t1").withLastname("t2").withMidlename("t3"), contacts.iterator().next().getId(), false, false);
        app.goTo().homePage();
        Contacts after = app.contacts().all();

        //проверяем размерность
        assertThat(after.size(), equalTo(contacts.size()));

        //проверяем идентификаторы
        assertThat(after, equalTo(contacts));
    }
}
