package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class TestCreateContact extends TestBase {

    public TestCreateContact()
    {

    }

    @Test
    public void testCreateContact() {
        app.getContactHelper().addContactPage();
        app.getContactHelper().fillContactData(new ContactData("Evgeniy2", "Antolievich2", "Kolesnikov2", "Koles", "Title", "Company", "Address", "home", "mobile num", "good work", "very best fax", "mail", "mail2", "mail3", "url home pahe", "adress", "home", "note", new String[]{"12", "4","2001"}, new String[]{"10", "3","2002"}));
        app.getContactHelper().sendContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
