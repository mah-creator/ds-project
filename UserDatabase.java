import java.util.HashMap;
public class UserDatabase {
    private static HashMap<String,User> listOfUser=new HashMap<>();
    // final User admin= new User();

    /**
     * 
     * @param email the email whose enterd by the someone to signin or create account 
     * @return return object of user if exist else null
     */
    private static boolean exist(String email){
        if(listOfUser.containsKey(email)){
            return listOfUser.get(email) != null;
        }
        return false;
    }

    /**
     * 
     * @param email the email whose enterd by the someone to signin 
     * @param password  the email whose enterd by the someone to signin
     * @return if the email  is exist and have the same password whose enterd return true else false
     */
    public boolean checkValidity(String email,String password){
        return (exist(email) && listOfUser.get(email).getPassword().equals(password))? true:false;
        // this catch is necessary if there's no mapped user to this email, then listOfUser.get(email) = null
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

        if(exist(email)) throw new IllegalArgumentException("This email is already registered");

        User user=new User(id, password, age, email, firstName, lastName);
        listOfUser.put(user.getEmail(), user);
        return user;
    }

    /**
     * @param email the email which defines the user
     * @param password the password that verifies the user
     * @return the User object iff the password checks 
     */
    public User getUser(String email, String password) {
       if(!checkValidity(email, password))throw new WrongUserCredentialsException();

        return listOfUser.get(email);
    }
}