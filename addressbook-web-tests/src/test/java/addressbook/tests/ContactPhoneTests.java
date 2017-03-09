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
        assertThat(contact.getAdress(), equalTo(contactInfoFromEditForm.getAdress()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    @Test
    public void testContactDetailPhones()
    {
        app.goTo().homePage();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(new ContactData().withId(app.contacts().all().iterator().next().getId()));

        app.goTo().homePage();
        ContactData contactDetail = app.contacts().infoDetails(contactInfoFromEditForm);

        //проверяем имя фамилию
        assertThat(contactDetail.getFio(), equalTo(mergeFIO(contactInfoFromEditForm)));

        //phones
        assertThat(cleaned(contactDetail.getHome()), equalTo(cleaned(contactInfoFromEditForm.getHome())));
        assertThat(cleaned(contactDetail.getWork()), equalTo(cleaned(contactInfoFromEditForm.getWork())));
        assertThat(cleaned(contactDetail.getMobile()), equalTo(cleaned(contactInfoFromEditForm.getMobile())));

        //mails
        assertThat(contactDetail.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getMail(),contact.getMail2(), contact.getMail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(),contact.getMobile(), contact.getWork())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeFIO(ContactData contact) {
        return Arrays.asList(contact.getFirstname(),contact.getMidlename(), contact.getLastname())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining(" "));
    }

    public static String cleaned(String str)
    {
        return  str.replaceAll("[-\\x20\\t()]", "");
    }
}
