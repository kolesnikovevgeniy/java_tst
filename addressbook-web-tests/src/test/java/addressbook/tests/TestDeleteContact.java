package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

        Set<ContactData> before = app.contacts().all();
        ContactData deletedContact = before.iterator().next();
        //int idToDelete = before.get(before.size()-1).getId();
        app.contacts().delete(deletedContact.getId());

        app.goTo().homePage();
        Set<ContactData> after = app.contacts().all();


        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);


        Assert.assertEquals(after, before);
    }
}
