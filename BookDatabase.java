import java.io.File;

import java.util.*;

public class BookDatabase {
    // the predefiened ways to sort the books in the database
    static final int NUMBER_OF_SORTING_CRITERIA = SortCriteria.values().length;

    // here we have multiple TreeMaps that will store the same books
    // but for each tree the book will be mapped to a different book attribute
    private TreeMap<String, Book>[] bookTrees = new TreeMap[NUMBER_OF_SORTING_CRITERIA];
    private int booksNumber;

    //the file storing all books and their information
    private final String dataFilePath = ".\\data\\books_file.txt";

    private Scanner input;

    public BookDatabase(){
        constructBookTrees();
        try{
            input = new Scanner(new File(dataFilePath));
    
            booksNumber = input.nextInt();
    
            extractData();
        }catch(Exception e){System.out.println(e.getMessage());}
    }

    // create a tree for each sort criteria defined in the SortCriteria class
    private void constructBookTrees(){
        for (SortCriteria sortCriteria : SortCriteria.values()) {
            contructTree(sortCriteria);
        }
    }

    // lists all books in a tree that is sorted 
    // base on the passed parameter sortCriteria
    private void  contructTree(SortCriteria sortCriteria){
        TreeMap<String, Book> bookTree = new TreeMap();
        bookTrees[sortCriteria.value] = bookTree;
    }

    // reads database data from the data file
    private void extractData(){
        Book tempBook;
        String[] tempBookAttributes = new String[Book.ATTRIBUTES_NUMBER];
        
        String[] line;
        String attributeValue;

        for (int i = 0; i < booksNumber; i++) {
            input.nextLine();

            tempBook = new Book();

            tempBookAttributes[0] = input.nextLine();

            for (int j = 1; j < Book.ATTRIBUTES_NUMBER; j++) {
                line = input.nextLine().split(" -> ");
                attributeValue = line.length == 1 ? null : line[1]; 
                tempBookAttributes[j] = attributeValue;
            }
            tempBook.setAttributes(tempBookAttributes);

            // for each sort criteria defined in the SortCriteria class
            // this will put the same book ectracted from the data file
            // in muliple trees where it's mapped to a different criteria at each tree 
            for (SortCriteria sortCriteria : SortCriteria.values()) {
                if(sortCriteria == SortCriteria.BOOK_NAME)
                    // the book name by convention is unique and must be
                    // for sorting to work properly
                    bookTrees[sortCriteria.value].put(tempBookAttributes[sortCriteria.corrospondingDatabaseColumn.value], tempBook);
                else
                    // other book attributes aren't guaranteed to be unique, but the book id is
                    // so to ensure uniqueness the book id is concatenated to the book attribute
                    // before mapping a book to it
                    bookTrees[sortCriteria.value].put(tempBookAttributes[sortCriteria.corrospondingDatabaseColumn.value] + tempBook.getID(), tempBook);

                
            }
        }
    }

    // returns a list of all books sorted based on the desired sort criteria
    public Book[] getSortedBooksList(SortCriteria sortCriteria){
        TreeMap<String, Book> sortedBookTree = bookTrees[sortCriteria.value];
        return sortedBookTree.values().toArray(new Book[sortedBookTree.size()]);
    }

    public int getBooksNumber(){
        return booksNumber;
    }
}
