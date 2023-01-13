import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private BookDatabase bookDatabase = new BookDatabase();
    private HashMap<User, ArrayList<Book>> userBookMap = new HashMap();
    private User activeUser = new User();
    
    public Book[] getBookList() {
        return bookDatabase.getBookList();
    }

    void add(Book book){
        if(userBookMap.containsKey(activeUser))
            userBookMap.get(activeUser).add(book);

        else if(!userBookMap.containsKey(activeUser)){
            ArrayList<Book> bookList = new ArrayList();
            bookList.add(book);
        }
    }

    Book[] getUserBooksList(){
        ArrayList<Book> userBooksList = userBookMap.get(activeUser);
        return userBooksList.toArray(new Book[userBooksList.size()]);
    }

    void logUserOut(){
        activeUser = null;
    }
}
