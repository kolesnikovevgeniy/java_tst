package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.client.methods.RequestBuilder.trace;

/**
 * Created by MyK on 28.01.17.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd)
    {
        super(wd);
    }
    public void sendContact() {
        click(By.name("submit")/*xpath("//div[@id='content']/form/input[21]")*/);
    }

    public void fillContactData(ContactData contactData, boolean creation, boolean createGroup) {
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

        if(creation && isElementPresent(By.name("new_group")))
        {
            try
            {
                setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }catch (NoSuchElementException e) {
                // группа не найдена, создаем
                if (createGroup)
                {
                    new GroupHelper(wd).createGroup(new GroupData(contactData.getGroup(), "t", "t"));
                    System.out.print("Group \"" + contactData.getGroup() + "\" is created.");
                } else // выбираем первую попавшуюся
                {
                    new Select(wd.findElement(By.name("new_group"))).selectByIndex(0);
                    System.err.print("Warnining! Group \"" + contactData.getGroup() + "\" not found! Please create group.");
                }
            }
            setTimeout(ApllicationManager.STANDART_TIMEOUT);
        }
        else
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int num)
    {
        wd.findElements(By.name("selected[]")).get(num).click();
    }

    public void fillAnniversary(ContactData contactData) {
        // проверяем на достаточный размер массив
        if (contactData.getAnniversary() == null || contactData.getAnniversary().length < 3)
            return;

        if (isElementPresent(By.name("aday")))
        {
            try
            {
                setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
                new Select(wd.findElement(By.name("aday"))).selectByValue(contactData.getAnniversary()[0]);
            }
            catch(NoSuchElementException e)
            {
                System.out.print("Element not found.");
            }
            setTimeout(ApllicationManager.STANDART_TIMEOUT);
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
        if (contactData.getBirthday() == null || contactData.getBirthday().length < 3)
            return;

        if (isElementPresent(By.name("bday")))
        {
            try
            {
                setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
                new Select(wd.findElement(By.name("bday"))).selectByValue(contactData.getBirthday()[0]);
            }
            catch(NoSuchElementException e)
            {
                System.out.print("Element not found.");
            }
            setTimeout(ApllicationManager.STANDART_TIMEOUT);
        }

        if (isElementPresent(By.name("bmonth")))
        {
            int bMonth = Integer.parseInt(contactData.getBirthday()[1]);
            if (bMonth >= 0 && bMonth < 13)
                new Select(wd.findElement(By.name("bmonth"))).selectByIndex(bMonth);
        }

        type(By.name("byear"), contactData.getBirthday()[2]);
    }

    public void goToAddContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.id(Integer.toString(id))).click();
    }

    public void clickEditContact(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id="+id+"']")).click();
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

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contactData, boolean creation, boolean createGroup) {

        if (createGroup)
        {
            try
            {
                goToAddContactPage();
                setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }catch (NoSuchElementException e) {
                new NavigationHelper(wd).gotoGroups();
                new GroupHelper(wd).createGroup(new GroupData(contactData.getGroup(), "t", "t"));
                System.out.print("Group \"" + contactData.getGroup() + "\" is created.");

            }
            setTimeout(ApllicationManager.STANDART_TIMEOUT);
        }
        goToAddContactPage();
        fillContactData(contactData, creation, createGroup);
        sendContact();
    }

    public List<ContactData> getListContacts()
    {
        List<ContactData> contactsData = new ArrayList<ContactData>();
        setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
        List<WebElement> elements = wd.findElements(By.name("entry"));
        setTimeout(ApllicationManager.STANDART_TIMEOUT);
        for(WebElement e : elements)
        {

            contactsData.add(new ContactData(Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value")),
                    e.findElements(By.tagName("td")).get(2).getText(),
                    null,
                    e.findElements(By.tagName("td")).get(1).getText()));
        }
        return contactsData;
    }
}
