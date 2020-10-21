package uni.tbd.openday.Class;

public class User {

    public String name;
    public String email;
    public String pass;
    // Variable to store data corresponding
    // to firstname keyword in database
    private String firstname;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String lastname;

    // Variable to store data corresponding
    // to age keyword in database
    private String age;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public User() {
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
    // Getter and setter method
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public String getAge()
    {
        return age;
    }
    public void setAge(String age)
    {
        this.age = age;
    }
}