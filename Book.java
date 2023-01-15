public class Book {
    private int bookID, totalPages;
    private String title, auther, publisher, publishDate;
    private float rating;
    private long isbn;
    public static final int ATTRIBUTES_NUMBER = 8;
    public static String[] informationBook=new String[ATTRIBUTES_NUMBER];

    public int getID() {
        return bookID;
    }
    public String getTitle() {
        return title;
    }
    public String getAuther() {
        return auther;
    }
    public long getIsbn() {
        return isbn;
    }
    public String getPublisher() {
        return publisher;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public float getRating() {
        return rating;
    }
    public String getPublishDate() {
        return publishDate;
    }

    
    public void setID(String bookID) {
        this.bookID = Integer.parseInt(bookID == null ? "0":bookID);
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuther(String auther) {
        this.auther = auther;
    }
    public void setIsbn(String isbn) {
        this.isbn = Long.parseLong(isbn == null ? "0":isbn);
    }
    public void setPublisher(String publisher) {
        this.publisher   = publisher;
    }
    public void setTotalPages(String totalPages) {
        this.totalPages  = Integer.parseInt(totalPages == null ? "0":totalPages);
    }
    public void setRating(String rating) {
        this.rating = Float.parseFloat(rating == null ? "0":rating);
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setAttributes(String[] attributes){
        setID(attributes[0]);
        setTitle(attributes[1]);
        setAuther(attributes[2]);
        setIsbn(attributes[3]);
        setPublisher(attributes[4]);
        setTotalPages(attributes[5]);
        setRating(attributes[6]);
        setPublishDate(attributes[7]);
        // informationBook[0]=getTitle();
        // informationBook[1]=getAuther();
        // informationBook[2]=Integer.toString(getID());
        // informationBook[3]=Long.toString( getIsbn());
        // informationBook[4]=getPublisher();
        // informationBook[5]=getPublisher();
        // informationBook[6]=Float.toString( getRating());
        // informationBook[7]=Integer.toString(getTotalPages());
    }
    public String[] getAttributes(){
        informationBook[0]=getTitle();
        informationBook[1]=getAuther();
        informationBook[2]=Integer.toString(getID());
        informationBook[3]=Long.toString( getIsbn());
        informationBook[4]=getPublisher();
        informationBook[5]=getPublisher();
        informationBook[6]=Float.toString( getRating());
        informationBook[7]=Integer.toString(getTotalPages());
        return informationBook;
    }
}
