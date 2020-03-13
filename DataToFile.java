package com.example.taskmanager;
import android.content.Context;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class DataToFile {
    public void put(Context context,LinkedList<String> hi) {
        System.out.println("OnDestroy");
        System.out.println("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        ListIterator<String> hello = hi.listIterator(0);
        System.out.println(context.getFilesDir().toString());
        try {
            System.out.println("hi2");
            File file;
            String name = "File1.txt";
            file = new File(context.getFilesDir(), name);
            PrintWriter x = new PrintWriter(file);
            x.print("");
            x.close();
            System.out.println(context.getFilesDir().toString());
            ListIterator<String> adi = hi.listIterator(0);
            FileWriter fw = new FileWriter(file, true);
            while (adi.hasNext()) {
                System.out.println("hi");

                fw.append(adi.next() + "\n");
            }
            //fw.append(adi.toString() + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public int index(LinkedList<String> ll, String addDate) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        ListIterator<String> hi = ll.listIterator(0);
        int index = 0;
        Date date2 = new Date();

        Date date = new Date();
        try {
            date2 = format.parse(addDate.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        while(hi.hasNext()) {
            try {
                String[] arr = hi.next().split(" ");
                date = format.parse(arr[arr.length-1].trim());
                if (date.compareTo(date2) <= 0) {
                    index++;
                } else {
                    return index;
                }
            } catch (ParseException e) {
                e.getStackTrace();
            }
        }
        return index;
    }
}
