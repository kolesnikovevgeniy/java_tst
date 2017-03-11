package addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.testng.annotations.Test;

import java.util.List;

@XStreamAlias("group")
public class GroupData {
    private String name;
    private String header;
    private String footer;

    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return id == groupData.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

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
                ", id=" + id +
                '}';
    }
}
