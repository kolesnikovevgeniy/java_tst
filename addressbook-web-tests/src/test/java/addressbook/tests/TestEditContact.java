package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

        Set<ContactData> contacts = app.contacts().all();
        //ContactData cd = contacts.get(contacts.size()-1);
        app.contacts().edit(contacts,new ContactData().withFirstname("t1").withLastname("t2").withMidlename("t3"), contacts.iterator().next().getId(), false, false);
        app.goTo().homePage();
        Set<ContactData> after = app.contacts().all();

        //проверяем размерность
        Assert.assertEquals(after.size(), contacts.size());

        // проверяем корректность заполненных данных
        Assert.assertEquals(contacts, after);
    }
}
