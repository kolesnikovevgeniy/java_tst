package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by MyK on 28.01.17.
 */
public class BaseHelper {
    protected WebDriver wd;



    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        click(locator);
        if(text != null)
        {
            if (wd.findElement(locator).getAttribute("value").equals(text))
                return;

            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected boolean isSelected(By locator) {
        return wd.findElement(locator).isSelected();
    }

    protected boolean find(By locator) {
        return wd.findElement(locator).isSelected();
    }

    protected void acceptAlert()
    {
        wd.switchTo().alert().accept();
    }

    protected boolean isElementPresent(By locator)
    {
        try
        {
            wd.manage().timeouts().implicitlyWait(ApllicationManager.waitElement, TimeUnit.SECONDS);
            wd.findElement(locator);
            wd.manage().timeouts().implicitlyWait(ApllicationManager.standartTimeout, TimeUnit.SECONDS);
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
        return true;
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
