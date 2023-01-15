// this class defines the order of columns in my book database file
// by setting a numerical value for eavh column to represent it's index
public enum DatabaseColumn{
    ID(0),
    TITLE(1),
    AUTHOR(2),
    ISBN(3),
    PUBLISHER(4),
    TOTAL_PAGES(5),
    RATING(6),
    PUBLISH_DATA(7);

    final int value;
    DatabaseColumn(int value){
        this.value = value;
    }
}