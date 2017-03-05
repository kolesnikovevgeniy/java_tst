package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by MyK on 29.01.17.
 */
public class TestDeleteContact extends TestBase{

    @Test
    public void testDeleteContact() {
        app.goTo().homePage();
        if (app.contacts().list().size() == 0)
        {
            app.contacts().create(new ContactData("Evgeniy2",
                    "Antolievich2",
                    "Kolesnikov2",
                    "Koles",
                    "Title",
                    "Company",
                    "Address",
                    "home",
                    "mobile num",
                    "good work",
                    "very best fax",
                    "mail",
                    "mail2",
                    "mail3",
                    "url home pahe",
                    "adress",
                    "home",
                    "note",
                    new String[]{"12", "1","2001"},
                    new String[]{"1", "2","2002"},
                    "test8"), true, false);
            app.goTo().homePage();
        }
        List<ContactData> before = app.contacts().list();
        int idToDelete = before.get(before.size()-1).getId();
        app.contacts().delete(idToDelete);

        app.goTo().homePage();
        List<ContactData> after = app.contacts().list();

        //проверяем размерность
        Assert.assertEquals(after.size(), before.size() - 1);

        try {
            before.remove(ContactData.getIndexById(before, idToDelete));
        }catch(IndexOutOfBoundsException ioobe) {
            throw new TestException("Идентификатор ("+ idToDelete +") в списке контактов не найден.");
        }
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }
}
