package com.ges.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatMain {


    /**
     * Main for running on local system
     * @param args
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("usage:  cat [file ...]");
            return;
        }

        String[] fileNames = args;

        /*
         * TODO  --Mimic Cat command
         *  1. print usage notes if no CLA's
         *  2. print error message if not files.
         *  3. Cases
         *   3.0 Multiple files just like linux cat command.
         *   3.1 local path
         *   3.2 Relative path
         *   3.4 Mac vs Windows file paths.
         *
         *  4. Java 7 - 8 Features where possible
         *   Files.lines(Path) -> Stream
         *   Lambda
         *   foreach
         *   Reference Args
         *   Try with Resources
         *
         *  5. Unit tests
         *   -Mockito if needed.
         *   -Junit 5 with paramaterized tests , assertGroups, Lambdas
         *
         *
         *   TODO: APPROACH
         *    1- Basic with Streams and code in main
         *    2. Convert to Java Paths, 8 NIO if possible
         *    3. Move to class
         *    4. Error handling
         *    5. Fun with Tests.
         *    6. Git deploy with readme.
         *       -test on windows follow instructions
         *       -Mac install script
         *
         */

        OutputStream outputStream;
        outputStream = System.out;

        OutputStream errorStream;
        errorStream = System.err;

        MyInputStreamReader misr = new MyInputStreamReader();
        List<InputStream> inputStreams = new ArrayList<>();

        /**
         * Build List of InputStreams to pass to Impl.
         */
        Arrays.stream(fileNames).forEach((fileName) -> {
            try {
                FileInputStream fis = new FileInputStream(new File(fileName));
                inputStreams.add(fis);
            } catch (FileNotFoundException e) {
                System.out.println("File not found:" + e.getMessage());
            }
        });

        /**
         * Convert List back to Array and call cat.
         *  -Converted to List, then back to Array.  Can do either way. --Probably faster to keep as Array.
         */
        InputStream[] streams = inputStreams.toArray(new InputStream[inputStreams.size()]);
        misr.cat(outputStream, errorStream, streams);


    }
}
