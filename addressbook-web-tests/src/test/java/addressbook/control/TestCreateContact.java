package addressbook.control;

import addressbook.model.ContactData;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class TestCreateContact {
    FirefoxDriver wd;

    public TestCreateContact(FirefoxDriver wd)
    {
        this.wd = wd;
    }

    public void testCreateContact(ContactData cd) {
        addContactPage();
        fillContactData(cd);
        sendContact();
    }

    private void sendContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void fillContactData(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMidlename());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNick());
        // вот это пока не знаю, как реализовать upload
        //wd.findElement(By.name("photo")).click();
        wd.findElement(By.name("title")).click();
        wd.findElement(By.name("title")).clear();
        wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAdress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getHome());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        wd.findElement(By.name("work")).click();
        wd.findElement(By.name("work")).clear();
        wd.findElement(By.name("work")).sendKeys(contactData.getWork());
        wd.findElement(By.name("fax")).click();
        wd.findElement(By.name("fax")).clear();
        wd.findElement(By.name("fax")).sendKeys(contactData.getFax());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getMail());
        wd.findElement(By.name("email2")).click();
        wd.findElement(By.name("email2")).clear();
        wd.findElement(By.name("email2")).sendKeys(contactData.getMail2());
        wd.findElement(By.name("email3")).click();
        wd.findElement(By.name("email3")).clear();
        wd.findElement(By.name("email3")).sendKeys(contactData.getMail3());
        wd.findElement(By.name("homepage")).click();
        wd.findElement(By.name("homepage")).clear();
        wd.findElement(By.name("homepage")).sendKeys(contactData.getHomepage());
        fillBirthday(19, 2, "1983");
        fillAnniversary(18, 4, "1800");
        wd.findElement(By.name("address2")).click();
        wd.findElement(By.name("address2")).clear();
        wd.findElement(By.name("address2")).sendKeys(contactData.getAddress2());
        wd.findElement(By.name("phone2")).click();
        wd.findElement(By.name("phone2")).clear();
        wd.findElement(By.name("phone2")).sendKeys(contactData.getPhone2());
        wd.findElement(By.name("notes")).click();
        wd.findElement(By.name("notes")).clear();
        wd.findElement(By.name("notes")).sendKeys(contactData.getNote());
    }

    private void fillAnniversary(final int day, final int month, String year) {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[3]//option[" + day + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[3]//option[" + day + "]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[4]//option[" + month + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[4]//option[" + month + "]")).click();
        }
        wd.findElement(By.name("ayear")).click();
        wd.findElement(By.name("ayear")).clear();
        wd.findElement(By.name("ayear")).sendKeys(year);
        /*if (!wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[2]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[2]")).click();
        }*/
    }

    private void fillBirthday(final int day, final int month, String year) {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + day + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + day + "]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + month + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + month + "]")).click();
        }
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(year);
    }

    private void addContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
