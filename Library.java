import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private BookDatabase bookDatabase = new BookDatabase();
    private UserDatabase userDatabase = new UserDatabase();
    private HashMap<User, ArrayList<Book>> userBookMap = new HashMap();

    // this defines the user that is currently logged into the library system
    // where only one user can use the system at a time
    private User activeUser;
    
    // returns all books from the BookDatabase
    Book[] getBookList() {
        return bookDatabase.getBookList();
    }

    // adds a book to the logged-in user (activeUser) 
    void add(Book book){
        if(userBookMap.containsKey(activeUser))
            userBookMap.get(activeUser).add(book);

        else if(!userBookMap.containsKey(activeUser)){
            ArrayList<Book> bookList = new ArrayList();
            bookList.add(book);
        }
    }
    
    // returns the list of books corresponding to the logged-in user
    Book[] getUserBooksList(){
        ArrayList<Book> userBooksList = userBookMap.get(activeUser);
        return userBooksList.toArray(new Book[userBooksList.size()]);
    }

    // logs-out the user by removing the activeUser pointer
    void logUserOut(){
        activeUser = null;
    }

    boolean logUserIn(String email, String password){
        // get the user if exists and check password validity
        User user = userDatabase.getUser(email, password);

        if(user == null) return false;
        
        if(!userBookMap.containsKey(user)){ 
            ArrayList<Book> bookList = new ArrayList();
            userBookMap.put(user, bookList);
        }

        activeUser = user;
        return true;
    }
}
