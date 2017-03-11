package addressbook.generator;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyK on 10.03.17.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contacts count")
    public int count;

    @Parameter(names = "-f", description = "File name")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
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
        List<ContactData> contacts = generateContacts(count);
        switch(format)
        {
            case "xml":
                saveInXML(contacts, new File(file));
                break;

            case "json":
                saveInJSON(contacts, new File(file));
                break;
        }
    }

    private void saveInJSON(List<ContactData> groups, File file) {
    }

    private void saveInXML(List<ContactData> contacts, File file) throws IOException {
        XStream stream= new XStream();
        stream.processAnnotations(ContactData.class);
        stream.alias("contact", ContactData.class);
        String xml = stream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacts(int count)
    {
        List<ContactData> c = new ArrayList<ContactData>();
        for(int i =0; i < count; i++)
        {
            c.add(new ContactData().withFirstname("Name" + i).withMiddlename("Midlename" +i).withLastname("Lastname" + i));
        }
        return c;
    }
}
