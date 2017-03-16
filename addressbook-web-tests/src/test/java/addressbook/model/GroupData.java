package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.testng.annotations.Test;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table (name = "group_list")
public class GroupData {
    @Expose
    @Column(name = "group_name")
    private String name;

    @Expose
    @Column(name = "group_header")
    @Type(type= "text")
    private String header;

    @Expose
    @Column(name = "group_footer")
    @Type(type= "text")
    private String footer;

    //@XStreamOmitField
    //@Expose
    //private int group_parent_id;

    @XStreamOmitField
    @Expose
    private int domain_id;

    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_parent_id")
    private int group_parent_id = 0;

    public Contacts getContacts() {
        return new Contacts(contacts);
    }

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    //@JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name =  "group_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public static int getIndexById(List<GroupData> lGD, int id)
    {
        for(int i = 0; i < lGD.size(); i++)
        {
            if(lGD.get(i).getId() == id)
            {
                return i;
            }
        }
        return -1;
    }

    public GroupData() {

    }

    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
        this.id = Integer.MAX_VALUE;
    }

    public GroupData(int id, String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
        this.id = id;
    }

    public void setData(GroupData data)
    {
        // считаем, что если null, то модифицировать не требуется
        if (data.footer != null)
            this.footer = data.footer;

        if (data.header != null)
            this.header = data.header;

        if (data.name != null)
            this.name = data.name;
    }

    public int getGroup_parent_id() {
        return group_parent_id;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }


    public GroupData withId(int id)
    {
        this.id = id;
        return this;
    }

    public GroupData withName(String name)
    {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header)
    {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer)
    {
        this.footer = footer;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                ", domain_id=" + domain_id +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (domain_id != groupData.domain_id) return false;
        if (id != groupData.id) return false;
        if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;
        if (header != null ? !header.equals(groupData.header) : groupData.header != null) return false;
        return footer != null ? footer.equals(groupData.footer) : groupData.footer == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        result = 31 * result + domain_id;
        result = 31 * result + id;
        return result;
    }
}
