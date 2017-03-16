package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyK on 12.03.17.
 */
public class DBHelper {
    private SessionFactory sessionFactory;
    public DBHelper()
    {

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }

    public Groups groups()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from GroupData where deprecated = null" ).list();

        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //выбираем только актуальные записи
        List result = session.createQuery( "from ContactData where deprecated = null" ).list();

        session.getTransaction().commit();
        session.close();
        return new Contacts(result);

    }

    public Contacts contacts_in_group(GroupData group)
    {
        return new Contacts(groups().stream().filter((g) -> g.getId() == group.getId()).iterator().next().getContacts());
    }

    public Groups groups_in_contact(ContactData contact)
    {
        return new Groups(contacts().stream().filter((c) -> c.getId() == contact.getId()).iterator().next().getGroups());
    }

    public Groups contact_not_in_groups(ContactData contact)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from GroupData where deprecated = null " ).list();

        List<GroupData> ret = new ArrayList<GroupData>();

        for ( GroupData g : (List<GroupData>) result ) {
            Contacts contacts = g.getContacts();
            boolean contactfound = false;
            for(ContactData cg: (Contacts)contacts) {
                if (cg.getId() == contact.getId()) {
                    contactfound = true;
                    break;
                }
            }
            if (!contactfound)
                ret.add(g);
        }

        // исключаем родительские групппв най
        session.getTransaction().commit();
        session.close();
        return new Groups(ret);
    }
}
