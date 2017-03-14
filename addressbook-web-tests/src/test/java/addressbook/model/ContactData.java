package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.io.*;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private  String firstname = "";

    @Expose
    @Column(name = "middlename")
    private  String midlename = "";

    @Expose
    @Column(name = "lastname")
    private  String lastname = "";

    @Expose
    @Column(name = "nickname")
    private  String nick = "";

    @Expose
    @Column(name = "title")
    private  String title = "";

    @Expose
    @Column(name = "company")
    private  String company = "";

    @Expose
    @Column(name = "address")
    @Type(type= "text")
    private  String address = "";

    @Expose
    @Column(name = "addr_long")
    @Type(type= "text")
    private  String addr_long = "";

    @Expose
    @Column(name = "addr_lat")
    @Type(type= "text")
    private  String addr_lat = "";

    @Expose
    @Column(name = "addr_status")
    @Type(type= "text")
    private  String addr_status = "";

    @Expose
    @Column(name = "home")
    @Type(type= "text")
    private  String home = "";

    @Expose
    @Column(name = "mobile")
    @Type(type= "text")
    private  String mobile = "";

    @Expose
    @Column(name = "work")
    @Type(type= "text")
    private  String work = "";

    @Expose
    @Column(name = "fax")
    @Type(type= "text")
    private  String fax = "";

    @Expose
    @Column(name = "email")
    @Type(type= "text")
    private  String mail = "";

    @Expose
    @Column(name = "email2")
    @Type(type= "text")
    private  String mail2 = "";

    @Expose
    @Column(name = "email3")
    @Type(type= "text")
    private  String mail3 = "";

    @Expose
    @Column(name = "homepage")
    @Type(type= "text")
    private  String homepage = "";

    @Expose
    @Column(name = "address2")
    @Type(type= "text")
    private  String address2 = "";

    @Expose
    @Column(name = "phone2")
    @Type(type= "text")
    private  String phone2 = "";

    @Expose
    @Column(name = "notes")
    @Type(type= "text")

    private  String note = "";

    private transient String[] birthday;

    private transient String[] anniversary;

    @Expose
    private String role;

    @Expose
    @Type(type= "text")
    private String im;

    @Expose
    @Type(type= "text")
    private String im2;

    @Expose
    @Type(type= "text")
    private String im3;

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id  = Integer.MAX_VALUE;

    //@Column(name = "deprecated" )
    //private Date deprecated  = null;

    @ManyToMany(mappedBy = "contacts", fetch = FetchType.EAGER)
    private Set<GroupData> groups = new HashSet<GroupData>();

    private transient String allPhones;
    private transient String fio;
    private transient String allEmails;
    private transient File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public void setAllPhones(String allPhones) {
        this.allPhones = allPhones;
    }

    public ContactData()
    {}

    public ContactData(String firstname, String midlename, String lastname, String nick, String title, String company, String adress, String home, String mobile, String work, String fax, String mail, String mail2, String mail3, String homepage, String address2, String phone2, String note, String[] birthday, String[] anniversary, String group) {
        this.firstname = firstname;
        this.midlename = midlename;
        this.lastname = lastname;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = adress;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.mail = mail;
        this.mail2 = mail2;
        this.mail3 = mail3;
        this.homepage = homepage;
        this.address2 = address2;
        this.phone2 = phone2;
        this.note = note;
        this.birthday = birthday;
        this.anniversary = anniversary;
    }

    public ContactData(int id, String firstname, String midlename, String lastname, String nick, String title, String company, String adress, String home, String mobile, String work, String fax, String mail, String mail2, String mail3, String homepage, String address2, String phone2, String note, String[] birthday, String[] anniversary, String group) {
        this.firstname = firstname;
        this.midlename = midlename;
        this.lastname = lastname;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = adress;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.mail = mail;
        this.mail2 = mail2;
        this.mail3 = mail3;
        this.homepage = homepage;
        this.address2 = address2;
        this.phone2 = phone2;
        this.note = note;
        this.birthday = birthday;
        this.anniversary = anniversary;
        this.id = id;
    }

    public ContactData(int id, String firstname, String midlename, String lastname) {
        this.firstname = firstname;
        this.midlename = midlename;
        this.lastname = lastname;
        this.id = id;
    }

    public ContactData(String firstname, String midlename, String lastname) {
        this.firstname = firstname;
        this.midlename = midlename;
        this.lastname = lastname;
        this.id = Integer.MAX_VALUE;
    }


    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMidlename(String midlename) {
        this.midlename = midlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public ContactData withAllPhones(String allPhones)
    {
        this.allPhones = allPhones;
        return this;
    }

    public Groups getGroups()
    {
        return new Groups(groups);
    }
     public String[] getBirthday() {
        return birthday;
    }

    public String[] getAnniversary() {
        return anniversary;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMidlename() {
        return midlename;
    }

    public String getLastname() {
        return lastname;
    }
    
    public String getNick() {
        return nick;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAdress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getMail() {
        return mail;
    }

    public String getMail2() {
        return mail2;
    }

    public String getMail3() {
        return mail3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNote() {
        return note;
    }
    public int getId() {return id;}

    public static int getIndexById(List<ContactData> lCD, int id)
    {
        for(int i = 0; i < lCD.size(); i++)
        {
            if(lCD.get(i).getId() == id)
            {
                return i;
            }
        }
        return -1;
    }
    public void setData(ContactData data) {
        if(data.firstname != null)
            this.firstname = data.firstname;

        if (data.midlename != null)
            this.midlename = data.midlename;

        if (data.lastname != null)
            this.lastname = data.lastname;
    }

    public ContactData withId(int id)
    {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", midlename='" + midlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nick='" + nick + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", addr_long='" + addr_long + '\'' +
                ", addr_lat='" + addr_lat + '\'' +
                ", addr_status='" + addr_status + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", work='" + work + '\'' +
                ", fax='" + fax + '\'' +
                ", mail='" + mail + '\'' +
                ", mail2='" + mail2 + '\'' +
                ", mail3='" + mail3 + '\'' +
                ", homepage='" + homepage + '\'' +
                ", address2='" + address2 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", note='" + note + '\'' +
                ", birthday=" + Arrays.toString(birthday) +
                ", anniversary=" + Arrays.toString(anniversary) +
                ", role='" + role + '\'' +
                ", im='" + im + '\'' +
                ", im2='" + im2 + '\'' +
                ", im3='" + im3 + '\'' +
                ", id=" + id +
                ", allPhones='" + allPhones + '\'' +
                ", fio='" + fio + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", photo=" + photo +
                '}';
    }


    public ContactData withEmail1(String attribute) {
        this.mail = attribute;
        return this;
    }

    public ContactData withEmail2(String attribute) {
        this.mail2 = attribute;
        return this;
    }

    public ContactData withEmail3(String attribute) {
        this.mail3 = attribute;
        return this;
    }

    public ContactData withAddress(String attribute) {
        this.address = attribute;
        return this;
    }

    public ContactData withAddress2(String attribute) {
        this.address2 = attribute;
        return this;
    }

    public ContactData withAllEmails(String emails) {
        this.allEmails = emails;
        return this;
    }

    public ContactData withMiddlename(String attribute) {
        this.midlename = attribute;
        return this;
    }

    public String getFio() {
        return fio;
    }

    public ContactData withFIO(String attribute) {
        this.fio = attribute;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (midlename != null ? !midlename.equals(that.midlename) : that.midlename != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (mail2 != null ? !mail2.equals(that.mail2) : that.mail2 != null) return false;
        if (mail3 != null ? !mail3.equals(that.mail3) : that.mail3 != null) return false;
        if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
        return note != null ? note.equals(that.note) : that.note == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (midlename != null ? midlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (mail2 != null ? mail2.hashCode() : 0);
        result = 31 * result + (mail3 != null ? mail3.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactData inGroups(GroupData group) {
        groups.add(group);
        return this;
    }
}
