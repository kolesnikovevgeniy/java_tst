package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by MyK on 28.01.17.
 */
public class TestEditContact extends TestBase{
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereContact())
        {
            app.getContactHelper().createContact(new ContactData("Evgeniy2",
                    "Antolievich2",
                    "Kolesnikov2",
                    "Koles",
                    "Title",
                    "Company",
                    "Address",
                    "home",
                    "mobile num",
                    "good work",
                    "very best fax",
                    "mail",
                    "mail2",
                    "mail3",
                    "url home pahe",
                    "adress",
                    "home",
                    "note",
                    new String[]{"12", "1","2001"},
                    new String[]{"1", "2","2002"},
                    "test8"), true, true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getListContacts();
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        app.getContactHelper().clickEditContact(before.get(before.size() - 1).getId());
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Evgeniygg",
                "Antolievidsfsdfchhh",
                "Kolesnikosdfsdfvjjj",
                "----",
                "Title",
                "Company",
                "Address",
                "home",
                "+79583368827",
                "good work",
                "very best fax",
                "mail345",
                "mail552",
                "mail366",
                "http://home.google.com",
                "adress",
                "home",
                "note",
                new String[]{"15", "5","2001"},
                new String[]{"16", "6","2002"},
                null);
        app.getContactHelper().fillContactData(contact, false, false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getListContacts();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(before, after);
    }

    @Test
    public void testEditContactById() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereContact())
        {
            app.getContactHelper().createContact(new ContactData("Evgeniy2",
                    "Antolievich2",
                    "Kolesnikov2",
                    "Koles",
                    "Title",
                    "Company",
                    "Address",
                    "home",
                    "mobile num",
                    "good work",
                    "very best fax",
                    "mail",
                    "mail2",
                    "mail3",
                    "url home pahe",
                    "adress",
                    "home",
                    "note",
                    new String[]{"12", "1","2001"},
                    new String[]{"1", "2","2002"},
                    "test8"), true, true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> contacts = app.getContactHelper().getListContacts();
        int numContact = contacts.size() - 1;
        app.getContactHelper().clickEditContact(contacts.get(numContact).getId());
        contacts.get(numContact).setData(new ContactData("Evgeniygg",  null, "Kolesnikosdfsdfvjjj"));
        app.getContactHelper().fillContactData(contacts.get(numContact), false, false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getListContacts();

        //проверяем размерность
        Assert.assertEquals(after.size(), contacts.size());

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        contacts.sort(byId);
        after.sort(byId);

        // проверяем корректность заполненных данных
        Assert.assertEquals(contacts, after);
    }
}
