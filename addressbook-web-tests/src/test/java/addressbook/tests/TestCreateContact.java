package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateContact extends TestBase {
    @Test
    public void testCreateContact() {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        ContactData contact = new ContactData().withFirstname("Test1").withMidlename("testmidle").withLastname("testlast");
        app.contacts().create(contact, true, false);
        app.goTo().homePage();
        Contacts after = app.contacts().all();

        //проверяем размерность
        assertThat(before.size() + 1, equalTo(after.size()));

        //проверяем идентификаторы
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
