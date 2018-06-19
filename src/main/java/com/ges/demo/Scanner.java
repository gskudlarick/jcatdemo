package com.ges.demo;

import java.io.File;
import java.io.FileNotFoundException;

public class Scanner {
    public void cat(String fileName) {

        File textFile = new File(fileName);

        java.util.Scanner in = null;
        try {
            in = new java.util.Scanner(textFile);
            while(in.hasNextLine()) {
                StringBuilder sb = new StringBuilder(in.nextLine());
                System.out.println(sb.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading File: " + textFile);
        }

        in.close();

    }
}
