package addressbook.generator;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyK on 10.03.17.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "File name")
    public String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }
        catch (ParameterException ex)
        {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupData> groups, File file) throws IOException
    {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for(GroupData g : groups)
        {
            writer.write(String.format("%s;%s;%s\n", g.getName(), g.getHeader(), g.getFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count)
    {
        List<GroupData> g = new ArrayList<GroupData>();
        for(int i =0; i < count; i++)
        {
            g.add(new GroupData().withName("Name" + i).withHeader("Header" + i).withFooter("Footer" + i));
        }
        return g;
    }
}
