package addressbook.control;

import addressbook.TestBase;
import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class TestCreateGroup extends TestBase {
    public TestCreateGroup()
    {

    }

    @Test
    public void testCreateGroup() {
        gotoGroupCreate();
        clickCreateNewGroup();
        fillGroupParams(new GroupData("test8", "test1", "test2"));
        clickAddGroup();
        returnGroupsPage();
    }

}
