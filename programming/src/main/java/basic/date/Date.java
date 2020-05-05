package basic.date;

public class Date {
    private int year, month, day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int compareTo(Date date) {
        return year > date.year ? 1
                : year < date.year ? -1
                : month > date.month ? 1
                : month < date.month ? -1
                : day > date.day ? 1
                : day < date.day ? -1 : 0;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}

