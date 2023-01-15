// this class defines the columns that can be sorted according to the BookDatabase class
// by setting a numerical value for each sortable column
// and connecting it to the corrosponding column in the database
// also it defines how this column is sorted by defining a sort label
public enum SortCriteria{
    BOOK_NAME(0, "A - Z", DatabaseColumn.TITLE),
    BOOK_AUTHOR(1, "author", DatabaseColumn.AUTHOR),
    BOOK_PUBLISHER(2, "publisher", DatabaseColumn.PUBLISHER),
    BOOK_PUBLISH_DATE(3, "publish date", DatabaseColumn.PUBLISH_DATA),
    BOOK_RATING(4, "rating", DatabaseColumn.RATING);

    final int value;
    final String sortLabel;
    final DatabaseColumn corrospondingDatabaseColumn;

    SortCriteria(int value, String sortLabel, DatabaseColumn corrospondingDatabaseColumn){
        this.value = value;
        this.sortLabel = sortLabel;
        this.corrospondingDatabaseColumn =corrospondingDatabaseColumn;
    }
}