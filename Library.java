public class Library {
    private BookDatabase bookDatabase = new BookDatabase();

    public Book[] getBookList() {
        return bookDatabase.getBookList();
    }
    
}
