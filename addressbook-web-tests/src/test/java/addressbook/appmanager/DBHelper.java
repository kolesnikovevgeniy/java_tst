package addressbook.appmanager;

import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
        List result = session.createQuery( "from GroupData where deprecated = null order by group_id" ).list();

        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //выбираем только актуальные записи
        List result = session.createQuery( "from ContactData where deprecated = null order by id" ).list();

        session.getTransaction().commit();
        session.close();
        return new Contacts(result);

    }
}
