package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.List;

public class TestCreateContact extends TestBase {

    public TestCreateContact()
    {

    }

    @Test
    public void testCreateContact() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getListContacts();
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
                "test8"), true, false);
        List<ContactData> after = app.getContactHelper().getListContacts();
        //проверяем добавился ли контакт
        app.getNavigationHelper().gotoHomePage();
    }
}
