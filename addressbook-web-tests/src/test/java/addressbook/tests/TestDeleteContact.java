package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteContact extends TestBase{

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact())
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
                    "test8"), true, false);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getListContacts();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptDeleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getListContacts();
    }
}
