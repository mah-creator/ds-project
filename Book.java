public class Book {
    private int bookID, totalPages;
    private String title, auther, publisher, publishDate;
    private float rating;
    private long isbn;

    public static final int ATTRIBUTES_NUMBER = DatabaseColumn.values().length;

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
    
    // returns all attributes a String value
    public String[] getStringAttributes(){
        String[] stringAttributes = new String[ATTRIBUTES_NUMBER];
        stringAttributes[DatabaseColumn.ID.value] = getID() + "";
        stringAttributes[DatabaseColumn.TITLE.value] = getTitle();
        stringAttributes[DatabaseColumn.AUTHOR.value] = getAuther();
        stringAttributes[DatabaseColumn.ISBN.value] = getIsbn() + "";
        stringAttributes[DatabaseColumn.PUBLISHER.value] = getPublisher();
        stringAttributes[DatabaseColumn.TOTAL_PAGES.value] = getTotalPages() + "";
        stringAttributes[DatabaseColumn.RATING.value] = getRating() + "";
        stringAttributes[DatabaseColumn.PUBLISH_DATA.value] = getPublishDate();
        return stringAttributes;
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
    }
}
