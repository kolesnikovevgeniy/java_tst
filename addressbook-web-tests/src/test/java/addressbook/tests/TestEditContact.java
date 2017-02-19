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
        ContactData cd = contacts.get(contacts.size()-1);
        app.getContactHelper().editContact(contacts, cd.getId(), cd, false, false);
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
