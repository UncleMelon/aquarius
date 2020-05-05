package basic.date;

public class TestDateSort {

    public static void main(String[] args) {
        Date[] dates = new Date[5];
        dates[0] = new Date(2018, 1, 2);
        dates[1] = new Date(2018, 2, 4);
        dates[2] = new Date(2014, 3, 6);
        dates[3] = new Date(2020, 1, 2);
        dates[4] = new Date(2019, 1, 2);

        print(dates);

        bubbleSort(dates);

        System.out.println("=========after bubble sort=========");
        print(dates);

        Date target = new Date(2019, 1, 2);
        System.out.println(binarySearch(dates, target));
    }

    public static void print(Date[] dates) {
        for (Date date: dates) {
            System.out.println(date);
        }
    }

    public static void bubbleSort(Date[] d) {
        for (int o = d.length - 1; o > 0; o--) {
            for (int i = 0; i < o; i++) {
                if (d[i].compareTo(d[i+1]) > 0) {
                    Date tmp = d[i];
                    d[i] = d[i+1];
                    d[i+1] = tmp;
                }
            }
        }
    }


    public static int binarySearch(Date[] d, Date target) {
        if (d.length == 0) return -1;

        int startPos = 0;
        int endPos = d.length - 1;
        int middle = (startPos + endPos) / 2;

        while (startPos <= endPos) {
            if (d[middle].compareTo(target) == 0) {
                return middle;
            }

            if (d[middle].compareTo(target) > 0) {
                endPos = middle - 1;

            }
            if (d[middle].compareTo(target) < 0) {
                startPos = middle + 1;
            }

            middle = (startPos + endPos) / 2;
        }

        return -1;
    }


}
