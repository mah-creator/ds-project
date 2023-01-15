import java.util.Collection;
import java.util.HashMap;
public class Library {
    private BookDatabase bookDatabase = new BookDatabase();
    private UserDatabase userDatabase = new UserDatabase();
    private HashMap<User, HashMap<String,Book>> userBookMap = new HashMap<>();

    // this defines the user that is currently logged into the library system
    // where only one user can use the system at a time
    private User activeUser;
    
    // returns a list of all database books sorted based on the desired sort criteria
    Book[] getSortedBooks(SortCriteria sortCriteria){
        return bookDatabase.getSortedBooksList(sortCriteria);
    }
    

    // adds a book to the logged-in user (activeUser) 
    void add(Book book) throws IllegalStateException{
        if(userBookMap.containsKey(activeUser) && userBookMap.get(activeUser) != null);

        else {
            HashMap<String,Book> bookList = new HashMap<>();
            userBookMap.put(activeUser, bookList);
        }
        
        if(!userBookMap.get(activeUser).containsKey(book.getTitle())) 
            userBookMap.get(activeUser).put(book.getTitle(), book);
        else throw new IllegalStateException("User already bought this book");
    }
    
    // returns the list of books corresponding to the logged-in user
    Book[] getActiveUserBooks(){
        if(activeUser == null) throw new IllegalStateException("No user is currently logged inthe user have no books into their account");
        else if(userBookMap.get(activeUser) == null) throw new IllegalStateException("The user have no books in their account");
        
        Collection<Book> userBooksList = userBookMap.get(activeUser).values();
        return (userBooksList).toArray(new Book[userBooksList.size()]);
    }

    // logs-out the user by removing the activeUser pointer
    void logUserOut(){
        activeUser = null;
    }

    void logUserIn(String email, String password){
        // get the user if exists and check password validity
            User user = userDatabase.getUser(email, password);


            if(!userBookMap.containsKey(user)){ 
                HashMap<String,Book> bookList = new HashMap<>();
                userBookMap.put(user, bookList);
            }
            logUserOut();
            activeUser = user;
    }

    public void removeBookFromeUser(Book book) {
        userBookMap.get(activeUser).remove(book.getTitle());
    }
}