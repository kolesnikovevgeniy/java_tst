package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteContact extends TestBase{

    @BeforeMethod
    public void before() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0)
        {
            app.contacts().create(new ContactData().withFirstname("Test1").withMidlename("testmadle").withLastname("testlast"), true, false);
            app.goTo().homePage();
        }
    }
    @Test
    public void testDeleteContact() {

        app.goTo().homePage();
        Contacts before = app.contacts().all();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact.getId());
        app.goTo().homePage();


        //проверяем размерность
        assertThat(app.contacts().count(), equalTo(before.size() - 1));

        Contacts after = app.contacts().all();

        //проверяем идентификаторы
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
