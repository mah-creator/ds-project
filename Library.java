import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private BookDatabase bookDatabase = new BookDatabase();
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
}
