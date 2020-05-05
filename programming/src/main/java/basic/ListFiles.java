package basic;

import java.io.File;

public class ListFiles {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\wy198\\Documents\\TODO\\TODO\\zhuo");
        System.out.println(file.getName());
        tree(file, 1);
    }

    private static void tree(File file, int level) {

        String preStr = "";
        for (int i = 0; i < level; i++) {
            preStr += "    ";
        }

        for (File f: file.listFiles()) {
            System.out.println(preStr + f.getName());
            if (f.isDirectory()) {
                tree(f, level + 1);
            }
        }
    }
}
