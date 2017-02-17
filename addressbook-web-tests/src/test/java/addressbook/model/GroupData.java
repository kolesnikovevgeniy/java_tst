package addressbook.model;

import java.util.List;

public class GroupData {
    private String name;
    private String header;
    private String footer;
    private int id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return name != null ? name.equals(groupData.name) : groupData.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
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
        this.footer = data.footer;
        this.header = data.header;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
