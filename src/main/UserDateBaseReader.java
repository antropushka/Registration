package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserDateBaseReader {
    private final File usersDateBase = new File("usersBase");

    private static final UserDateBaseReader instance = new UserDateBaseReader();

    public UserDateBaseReader() {
    }

    public static UserDateBaseReader getInstance() {
        return instance;
    }

//    public boolean isFind (String string) {
//
//
//    }

    public static boolean readFileByFilter(String fileName, String filter) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            String tmp = reader.readLine();
            if (tmp.matches(".*"+ filter +".*")) {
                return true;
            }
        }
        reader.close();
        return false;
    }
}
