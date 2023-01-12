import java.util.HashMap;
import java.util.TreeMap;

public class Library {
    private BookDatabase bookDatabase = new BookDatabase();
    private HashMap<User, TreeMap<String, Book>> hashMap = new HashMap();
    private User activeUser = new User();
    
    public Book[] getBookList() {
        return bookDatabase.getBookList();
    }

    void add(Book book){
        hashMap.get(activeUser).put(book.getTitle(), book);
    }
}
