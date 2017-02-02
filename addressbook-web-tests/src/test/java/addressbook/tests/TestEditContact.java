package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by MyK on 28.01.17.
 */
public class TestEditContact extends TestBase{
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().clickEditContact();
        app.getContactHelper().fillContactData(new ContactData("Evgeniy",
                "Antolievich",
                "Kolesnikov",
                "----",
                "Title",
                "Company",
                "Address",
                "home",
                "+79583368827",
                "good work",
                "very best fax",
                "mail",
                "mail2",
                "mail3",
                "http://home.google.com",
                "adress",
                "home",
                "note",
                null),
                false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
