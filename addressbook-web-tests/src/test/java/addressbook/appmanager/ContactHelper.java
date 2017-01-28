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
        fillBirthday(19, 2, "1983");
        fillAnniversary(18, 4, "1800");
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNote());
    }

    public void fillAnniversary(final int day, final int month, String year) {
        if (!isSelected(By.xpath("//div[@id='content']/form/select[3]//option[" + day + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[3]//option[" + day + "]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[4]//option[" + month + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[4]//option[" + month + "]"));
        }
        type(By.name("ayear"), year);
    }

    public void fillBirthday(final int day, final int month, String year) {
        if (!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[" + day + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[" + day + "]"));
        }
        if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[" + month + "]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[" + month + "]"));
        }
        type(By.name("byear"), year);
    }

    public void addContactPage() {
        click(By.linkText("add new"));
    }

}
