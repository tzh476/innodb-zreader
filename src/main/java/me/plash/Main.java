package me.plash;

import me.plash.reader.TableSpaceReader;

public class Main {
    static String ibdPath;
    public static void main(String[] args) {
        ibdPath = "C:\\Users\\ThinkPad\\IdeaProjects\\innodb-zreader\\data\\springdb\\test.ibd";
        TableSpaceReader.readTableSpace(ibdPath);
    }
}
