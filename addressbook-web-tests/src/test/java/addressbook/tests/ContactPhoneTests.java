package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MyK on 09.03.17.
 */
public class ContactPhoneTests extends TestBase {
    @Test
    public void testContactPhones()
    {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

        assertThat(cleaned(contact.getAllPhones()), equalTo(mergePhones(contactInfoFromEditForm)));
        //assertThat(cleaned(contact.getHome()), equalTo(contactInfoFromEditForm.getHome()));
        //assertThat(cleaned(contact.getWork()), equalTo(contactInfoFromEditForm.getWork()));
        //assertThat(cleaned(contact.getMobile()), equalTo(contactInfoFromEditForm.getMobile()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(),contact.getMobile(), contact.getWork())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String str)
    {
        return  str.replaceAll("[-\\s()]", "");
    }
}
