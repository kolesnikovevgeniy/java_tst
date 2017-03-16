package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by MyK on 12.03.17.
 */
public class HBConectionTest {
    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testHDConnection()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from address_in_groups where deprecated = null" ).list();
        for ( ContactData c : (List<ContactData>) result ) {
            System.out.println("===");
            System.out.println(c.getGroups());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test(enabled = false)
    public void testDBConnection()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addresbook?user=root&password=");
            Statement st = conn.createStatement();
            st.execute("");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
