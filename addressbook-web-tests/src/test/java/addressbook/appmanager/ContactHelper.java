package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Created by MyK on 28.01.17.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd)
    {
        super(wd);
    }
    public void sendContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData, boolean creation) {
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

        if(creation && isElementPresent(By.name("new_group"))) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
        else
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillAnniversary(ContactData contactData) {
        // проверяем на достаточный размер массив
        if (contactData.getAnniversary().length < 3)
            return;

        if (isElementPresent(By.name("aday")))
        {
            new Select(wd.findElement(By.name("aday"))).selectByValue(contactData.getAnniversary()[0]);
        }

        if (isElementPresent(By.name("amonth")))
        {
            int bMonth = Integer.parseInt(contactData.getAnniversary()[1]);
            if (bMonth >= 0 && bMonth < 13)
                new Select(wd.findElement(By.name("amonth"))).selectByIndex(bMonth);
        }

        type(By.name("ayear"), contactData.getAnniversary()[2]);
    }

    public void fillBirthday(ContactData contactData) {
        // проверяем на достаточный размер массив
        if (contactData.getBirthday().length < 3)
            return;

        if (isElementPresent(By.name("bday")))
        {
            new Select(wd.findElement(By.name("bday"))).selectByValue(contactData.getBirthday()[0]);
        }

        if (isElementPresent(By.name("bmonth")))
        {
            int bMonth = Integer.parseInt(contactData.getBirthday()[1]);
            if (bMonth >= 0 && bMonth < 13)
                new Select(wd.findElement(By.name("bmonth"))).selectByIndex(bMonth);
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

    public void deleteSelectedContact() {
        JavascriptExecutor js = (JavascriptExecutor)wd;
        js.executeScript("DeleteSel();");
    }

    public void acceptDeleteContact()
    {
        acceptAlert();
    }
}
