package addressbook;

import addressbook.control.TestCreateGroup;
import addressbook.control.TestCreateContact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

/**
 * Created by MyK on 27.01.17.
 */
public class mainTest {

    FirefoxDriver wd;

    protected TestCreateGroup cg;
    protected TestCreateContact cc;
    @BeforeMethod
    public void setUp() throws Exception {

        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        cg = new TestCreateGroup(wd);
        cc = new TestCreateContact(wd);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @Test
    public void testCreateGroup()
    {
        cg.testCreateGroup();
    }

    @Test
    public void testCreateContact()
    {
        cc.testCreateContact();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
