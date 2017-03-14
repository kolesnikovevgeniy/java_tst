package addressbook.tests;

import addressbook.appmanager.ApllicationManager;
import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by MyK on 28.01.17.
 */
public class TestBase {

    public static ApllicationManager app = new ApllicationManager(System.getProperty("browser", BrowserType.CHROME));

    public TestBase()
    {

    }

    @BeforeSuite
    public void setUp() throws Exception
    {
        app.init();
    }


    @AfterSuite
    public void tearDown()
    {
        app.stop();
    }


    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.groups().all();
            assertThat(dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet()), equalTo(uiGroups));
        }
    }

    public void verifyContactListInUI() {
        if (!Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contacts().all();
            assertThat(dbContacts.stream().map((c) -> new ContactData().withId(c.getId()).withFirstname(c.getFirstname()).withLastname(c.getLastname())).collect(Collectors.toSet()), equalTo(uiContacts));
        }
    }

}
