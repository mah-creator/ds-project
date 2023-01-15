import java.io.File;

import java.util.*;

public class BookDatabase {
    private TreeMap<String, Book> bookList = new TreeMap<>();
    private int booksNumber;
    private final String dataFilePath = ".\\data\\books_file.txt";

    private Scanner input;

    public BookDatabase(){
        try{
            input = new Scanner(new File(dataFilePath));
    
            booksNumber = input.nextInt();
    
            extractData();
        }catch(Exception e){System.out.println(e.getMessage());}
    }

    public int getBooksNumber(){
    	return booksNumber;
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

            bookList.put(tempBook.getTitle(), tempBook);
        }
    }

    public Book getBook(String bookName){
        if(!bookList.containsKey(bookName) || bookList.get(bookName) == null) throw new IllegalStateException("this Book isn't in the database");
        return bookList.get(bookName);
    }

    public Book[] getBookList(){
        return bookList.values().toArray(new Book[bookList.size()]);
    }
}