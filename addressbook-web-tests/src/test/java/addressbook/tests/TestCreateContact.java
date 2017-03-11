package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateContact extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsXML()
    {
        XStream stream = new XStream();
        stream.processAnnotations(ContactData.class);
        List<ContactData> contactsXML = (List<ContactData>)stream.fromXML(new File("src/test/resources/contacts.xml"));
        return contactsXML.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsXML")
    public void testCreateContact(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        File photo = new File("./src/test/resources/1.png");
        app.contacts().create(contact, true, false);
        app.goTo().homePage();
        Contacts after = app.contacts().all();

        //проверяем размерность
        assertThat(before.size() + 1, equalTo(after.size()));

        //проверяем идентификаторы
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCreateBadContact() {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        ContactData contact = new ContactData().withFirstname("Test1'").withMidlename("testmidle").withLastname("testlast");
        app.contacts().create(contact, true, false);
        app.goTo().homePage();


        //проверяем размерность
        assertThat(before.size(), equalTo(app.contacts().count()));

        Contacts after = app.contacts().all();
        //проверяем идентификаторы
        assertThat(after, equalTo(before));
    }
}
