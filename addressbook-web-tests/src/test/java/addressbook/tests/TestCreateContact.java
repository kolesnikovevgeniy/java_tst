package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class TestCreateContact extends TestBase {
    @Test
    public void testCreateContact() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getListContacts();
        ContactData contact = new ContactData("Evgeniy2",
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
                "test8");

        app.getContactHelper().createContact(contact, true, false);

        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getListContacts();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(after, before);
    }
}
