package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;
import java.util.stream.Collectors;


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

        attach(By.name("photo"), contactData.getPhoto());

        // пока убираем, т.к. не знаем где в БД хранится группа, привязанныя к контакту
        if(creation && isElementPresent(By.name("new_group")))
        {
                if (contactData.getGroups().size() == 0)
                {
                    Assert.assertTrue(contactData.getGroups().size() == 1);
                    setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
                    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
                    setTimeout(ApllicationManager.STANDART_TIMEOUT);
                }
        }
        else
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void selectContactById(int id) {
        wd.findElement(By.id(Integer.toString(id))).click();
    }

    public void clickEditContact(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id="+id+"']")).click();
    }
    public void clickViewContact(int id) {
        wd.findElement(By.xpath("//a[@href='view.php?id="+id+"']")).click();
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

    public int count()
    {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contactData, boolean creation)
    {
        goToAddContactPage();
        fillContactData(contactData, creation);
        sendContact();
        contactsCache = null;
    }

    public void delete(int idToDelete) {
        selectContactById(idToDelete);
        deleteSelectedContact();
        acceptDeleteContact();
        contactsCache = null;
    }
    private Contacts contactsCache = null;
    public Contacts all()
    {
        if (contactsCache != null)
                return new Contacts(contactsCache);

        contactsCache = new Contacts();
        setTimeout(ApllicationManager.WAIT_ELEMENT_TIMEOUT);
        List<WebElement> elements = wd.findElements(By.name("entry"));
        setTimeout(ApllicationManager.STANDART_TIMEOUT);
        for(WebElement e : elements)
        {
            java.lang.String phones = e.findElements(By.tagName("td")).get(5).getText();//.split("\n");
            java.lang.String emails = e.findElements(By.tagName("td")).get(4).getText();
            contactsCache.add(new ContactData().withId(Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value")))
                    .withFirstname(e.findElements(By.tagName("td")).get(2).getText())
                    .withLastname(e.findElements(By.tagName("td")).get(1).getText())
                    .withAllPhones(phones)
                    .withAddress(e.findElements(By.tagName("td")).get(3).getText())
                    .withAllEmails(emails));
        }
        return new Contacts(contactsCache);
    }

    public void edit(Set<ContactData> contacts, ContactData contact, int idToEdit, boolean creation, boolean createGroup) {
        clickEditContact(idToEdit);

        for (Iterator<ContactData> it = contacts.iterator(); it.hasNext(); ) {
            ContactData c = it.next();
            if (c.equals(new GroupData().withId(idToEdit))) {
                c.setData(contact);
            }
        }

        fillContactData(contact, creation);
        clickUpdateContact();
        contactsCache = null;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        clickEditContact(contact.getId());

        return new ContactData().withId(contact.getId())
                .withFirstname(wd.findElement(By.name("firstname")).getAttribute("value"))
                .withMiddlename(wd.findElement(By.name("middlename")).getAttribute("value"))
                .withLastname(wd.findElement(By.name("lastname")).getAttribute("value"))
                .withHomePhone(wd.findElement(By.name("home")).getAttribute("value"))
                .withWorkPhone(wd.findElement(By.name("work")).getAttribute("value"))
                .withMobilePhone(wd.findElement(By.name("mobile")).getAttribute("value"))
                .withEmail1(wd.findElement(By.name("email")).getAttribute("value"))
                .withEmail2(wd.findElement(By.name("email2")).getAttribute("value"))
                .withEmail3(wd.findElement(By.name("email3")).getAttribute("value"))
                .withAddress(wd.findElement(By.name("address")).getAttribute("value"))
                .withAddress2(wd.findElement(By.name("address2")).getAttribute("value"));

    }

    public ContactData infoDetails(ContactData contact) {
        clickViewContact(contact.getId());
        java.lang.String home = "";
        java.lang.String work = "";
        java.lang.String mobile = "";

        java.lang.String allDetail = wd.findElement(By.xpath("//div[@id='content']")).getText();
        Pattern p = Pattern.compile("(H: ).*\\n");
        Matcher m = p.matcher(allDetail);

        if (m.find())
            home = allDetail.substring(m.start() + 3, m.end() - 1);

        p = Pattern.compile("(W: ).*\\n");
        m = p.matcher(allDetail);
        if (m.find())
            work = allDetail.substring(m.start() + 3, m.end() - 1);

        p = Pattern.compile("(M: ).*\\n");
        m = p.matcher(allDetail);
        if (m.find())
            mobile = allDetail.substring(m.start() + 3, m.end() - 1);

        // собираем все мэйлы
        List<WebElement> emails = wd.findElements(By.xpath("//div[@id='content']/a"));
        String allEmails = emails.stream().map(WebElement::getText).collect(Collectors.joining("\n"));

        return new ContactData().withId(contact.getId())
                .withFIO(wd.findElement(By.xpath("//div[@id='content']/b")).getText())
                .withAllPhones(wd.findElement(By.xpath("//div[@id='content']/br[3]")).getText().replaceAll("(W: )|(H: )|(M: )", ""))
                .withHomePhone(home)
                .withWorkPhone(work)
                .withMobilePhone(mobile)
                .withAllEmails(allEmails)
                .withAddress(wd.findElement(By.xpath("//div[@id='content']/br[1]")).getText());

    }
}
