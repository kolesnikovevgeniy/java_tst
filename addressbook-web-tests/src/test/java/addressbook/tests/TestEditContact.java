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
                    "test8"), true);
            app.getNavigationHelper().gotoHomePage();
        }
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
                new String[]{"15", "5","2001"},
                new String[]{"16", "6","2002"},
                null),
                false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
