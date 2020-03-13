package com.example.taskmanager;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadFromFile {
    public LinkedList<String> read(Context hi) {
        LinkedList<String> retval = new LinkedList<String>();
        try {
            System.out.println("hi");
            String line;
            File file = new File(hi.getFilesDir().toString() + "/File1.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                retval.add(line);
            }
            System.out.println(retval);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retval;
    }
}
