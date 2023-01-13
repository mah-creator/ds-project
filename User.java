public class User{
    private   int id;
    private String password;
    private int age;
    private String email;
    private String firstName;
    private String lastName; 
    public User(int id, String password,int age,String email,String firstName,String lastName){
        this.age=age;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.id=id;
    }
    public User() {
    }
    // set and get to Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    /*end */

    // set and get to Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*end */

    // set and get to Age
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    /*end */

    // set and get to Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /*end */

    // set and get to FirstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /*end */

    // set and get to LastName
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /*end */
}