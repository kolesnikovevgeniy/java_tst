package addressbook.generator;
import addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyK on 10.03.17.
 */
public class GroupDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File f = new File(args[1]);

        List<GroupData> groups = generateGroups(count);
        save(groups, f);
    }

    private static void save(List<GroupData> groups, File file) throws IOException
    {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for(GroupData g : groups)
        {
            writer.write(String.format("%s;%s;%s\n", g.getName(), g.getHeader(), g.getFooter()));
        }
        writer.close();
    }

    private static List<GroupData> generateGroups(int count)
    {
        List<GroupData> g = new ArrayList<GroupData>();
        for(int i =0; i < count; i++)
        {
            g.add(new GroupData().withName("Name" + i).withHeader("Header" + i).withFooter("Footer" + i));
        }
        return g;
    }
}
