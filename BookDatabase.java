import java.io.File;

import java.util.*;

public class BookDatabase {
    static final int NUMBER_OF_SORTING_CRITERIA = SortCriteria.values().length;

    private TreeMap<String, Book>[] allBookLists = new TreeMap[NUMBER_OF_SORTING_CRITERIA];
    private int booksNumber;
    private final String dataFilePath = ".\\data\\books_file.txt";

    private Scanner input;

    public BookDatabase(){
        constructListOfBooks();
        try{
            input = new Scanner(new File(dataFilePath));
    
            booksNumber = input.nextInt();
    
            extractData();
        }catch(Exception e){System.out.println(e.getMessage());}
    }

    public int getBooksNumber(){
    	return booksNumber;
    }

    private void constructListOfBooks(){
        for (SortCriteria sortCriteria : SortCriteria.values()) {
            addList(sortCriteria);
        }
    }
    private void addList(SortCriteria sortCriteria){
        TreeMap<String, Book> bookList = new TreeMap();
        allBookLists[sortCriteria.value] = bookList;
    }

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

            for (SortCriteria sortCriteria : SortCriteria.values()) {
                if(sortCriteria == SortCriteria.BOOK_NAME)
                    allBookLists[sortCriteria.value].put(tempBookAttributes[sortCriteria.corrospondingDatabaseColumn.value], tempBook);
                else
                    allBookLists[sortCriteria.value].put(tempBookAttributes[sortCriteria.corrospondingDatabaseColumn.value] + tempBook.getID(), tempBook);

                
            }
        }
    }

    public Book getBook(String bookName){
        if(!allBookLists[SortCriteria.BOOK_NAME.value].containsKey(bookName) || allBookLists[SortCriteria.BOOK_NAME.value].get(bookName) == null) throw new IllegalStateException("this Book isn't in the database");
        return allBookLists[SortCriteria.BOOK_NAME.value].get(bookName);
    }

    public Book[][] getDifferentlySotedBookLists(){
        Book[][] allBooksSortedDifferently = new Book[NUMBER_OF_SORTING_CRITERIA][];
        int i = 0;
        for (TreeMap<String,Book> treeMap : allBookLists) {
            allBooksSortedDifferently[i++] = treeMap.values().toArray(new Book[treeMap.size()]);
        }
        return allBooksSortedDifferently;
    }
}
