import java.util.Scanner;
import java.io.File;

public class BookDatabase {
    private Book[] books;
    private int attributesNumber = Book.ATTRIBUTES_NUMBER;
    private int booksNumber;

    private Scanner input;

    public BookDatabase(String dataFilePath) throws Exception{
        input = new Scanner(new File(dataFilePath));

        booksNumber = input.nextInt();
        books = new Book[booksNumber];

        extractData();
    }

    public Book[] getBooks(){
        return books;
    }
    public int getBooksNumber(){
    	return booksNumber;
    }

    private void extractData(){
        Book tempBook;
        String[] tempBookAttributes = new String[attributesNumber];
        
        String[] line;
        String attributeValue;

        for (int i = 0; i < booksNumber; i++) {
            input.nextLine();

            tempBook = new Book();

            tempBookAttributes[0] = input.nextLine();
            for (int j = 1; j < attributesNumber; j++) {
                line = input.nextLine().split(" -> ");
                attributeValue = line.length == 1 ? null : line[1]; 
                tempBookAttributes[j] = attributeValue;
            }
            tempBook.setAttributes(tempBookAttributes);

            books[i] = tempBook;
        }
    }
}
