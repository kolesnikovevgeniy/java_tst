package addressbook.model;

public class ContactData {
    private  String firstname;
    private  String midlename;
    private  String lastname;
    private  String nick;
    private  String title;
    private  String company;
    private  String adress;
    private  String home;
    private  String mobile;
    private  String work;
    private  String fax;
    private  String mail;
    private  String mail2;
    private  String mail3;
    private  String homepage;
    private  String address2;
    private  String phone2;
    private  String note;
    private String[] birthday;
    private String[] anniversary;
    private String group;

    public ContactData(String firstname, String midlename, String lastname, String nick, String title, String company, String adress, String home, String mobile, String work, String fax, String mail, String mail2, String mail3, String homepage, String address2, String phone2, String note, String[] birthday, String[] anniversary, String group) {
        this.firstname = firstname;
        this.midlename = midlename;
        this.lastname = lastname;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.adress = adress;
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
        this.group = group;
    }

    public String getGroup()
    {
        return group;
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
        return adress;
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
}
