package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by MyK on 28.01.17.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(FirefoxDriver wd)
    {
        super(wd);
    }
    public void sendContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMidlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAdress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getMail());
        type(By.name("email2"), contactData.getMail2());
        type(By.name("email3"), contactData.getMail3());
        type(By.name("homepage"), contactData.getHomepage());

        fillBirthday(contactData);
        fillAnniversary(contactData);
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNote());
    }

    public void fillAnniversary(ContactData contactData) {
        if (contactData.getAnniversary().length < 3)
            return;

        if (!isSelected(By.xpath("//div[@id='content']/form/select[3]//option[" + contactData.getAnniversary()[0] + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[3]//option[" + contactData.getAnniversary()[0] + "]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[4]//option[" + contactData.getAnniversary()[1] + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[4]//option[" + contactData.getAnniversary()[1] + "]"));
        }
        type(By.name("ayear"), contactData.getAnniversary()[2]);
    }

    public void fillBirthday(ContactData contactData) {
        if (contactData.getBirthday().length < 3)
            return;

        if (!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.getBirthday()[0] + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.getBirthday()[0] + "]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.getBirthday()[1] + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.getBirthday()[1] + "]"));
        }
        type(By.name("byear"), contactData.getBirthday()[2]);
    }

    public void addContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void clickEditContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void clickUpdateContact() {
        click(By.name("update"));
    }
}
