package addressbook.generator;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;

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

    @Parameter(names = "-d", description = "Data format")
    public String format;

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
        switch(format)
        {
            case "csv":
                saveInCSV(groups, new File(file));
                break;

            case "xml":
                saveInXML(groups, new File(file));
                break;

            case "json":
                saveInJSON(groups, new File(file));
                break;
        }
    }

    private void saveInJSON(List<GroupData> groups, File file) {
    }

    private void saveInXML(List<GroupData> groups, File file) throws IOException {
        XStream stream= new XStream();

        stream.processAnnotations(GroupData.class);
        stream.alias("group", GroupData.class);
        String xml = stream.toXML(groups);
        try (Writer writer = new FileWriter(file))
        {
            writer.write(xml);
        }
    }

    private void saveInCSV(List<GroupData> groups, File file) throws IOException
    {
        try (Writer writer = new FileWriter(file)) {
            for (GroupData g : groups) {
                writer.write(String.format("%s;%s;%s\n", g.getName(), g.getHeader(), g.getFooter()));
            }
        }
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
