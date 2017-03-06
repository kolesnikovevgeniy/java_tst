package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TestCreateContact extends TestBase {
    @Test
    public void testCreateContact() {
        app.goTo().homePage();
        Set<ContactData> before = app.contacts().all();
        ContactData contact = new ContactData().withFirstname("Test1").withMidlename("testmidle").withLastname("testlast");

        app.contacts().create(contact, true, false);

        app.goTo().homePage();
        Set<ContactData> after = app.contacts().all();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        //Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        //after.sort(byId);
        //before.sort(byId);

        Assert.assertEquals(after, before);
    }
}
