package java8.charpter1;

import java.io.File;

public class Examples {

    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    }
}
