import java.util.HashMap;
public class UserDatabase {
    private HashMap<String,User> listOfUser=new HashMap<>();
    // final User admin= new User();
    UserDatabase(){
        listOfUser.put("123", new User(0, "123", 0, "123", "abc", "abc"));
    }
    /**
     * 
     * @param email the email whose enterd by the someone to signin or create account 
     * @return return object of user if exist else null
     */
    private User exist( String email){
        return listOfUser.get(email);
    }

    /**
     * 
     * @param email the email whose enterd by the someone to signin 
     * @param password  the email whose enterd by the someone to signin
     * @return if the email  is exist and have the same password whose enterd return true else false
     */
    public boolean CheckValidate(String email,String password){
        User user=exist(email);
        return (user!=null&&user.getPassword().equals(password))? true:false;
    }
    /**
     * if the person not have account in library  promt frome you add some information
     * @param id
     * @param password
     * @param age
     * @param email
     * @param firstName
     * @param lastName
     * @return if the email hase enterd id exist return null else return the User object whose created
     */
    public User addUser(int id,int age, String password ,String email,String firstName,String lastName){
        User user=exist(email);
        if(user!=null) return null;
        user=new User(id, password, age, email, firstName, lastName);
        listOfUser.put(email, user);
        return user;
    }
}
