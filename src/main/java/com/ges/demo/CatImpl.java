package com.ges.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatImpl implements Cat {
    public void cat(OutputStream output, OutputStream error, InputStream... inputs) {


    }

    public void list(String... inputs) {

        Arrays.asList(inputs).forEach( item -> System.out.println(item));

    }

    public static void main(String[] args) {

        boolean debug = true;
        if ( !debug && args.length ==0) {
            System.out.println("usage:  cat [file ...]");
            return;
        }

        String[] fileNames  = { "dummy.txt", "foo.txt", "bar.txt"};
        //String[] fileNames;  =  args;
        // get from CLI
        if(debug) {
            System.out.println("CLI Args: " + args.length);
            Arrays.stream(fileNames).forEach((fileName) -> {
                System.out.println(fileName);
            });
        }

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
        *  4. Java x.. 8 Features
        *   Lambda
        *   foreach
        *   Reference Args
        *   Try with Resources
        *   Java 8 file NIO.  Java Path.
        *
        *  5. Unit tests
        *   -Mockito
        *   -Junit 5 with paramaterized types, assertGroups, Lambdas
        *
        *
        *   TODO: APPROACH
        *    1- Basic with Streams and code in main
        *    2. Convert to Java Paths, 8 NIO
        *    3. Move to class
        *    4. Error handling
        *    5. Fun with Tests.
        *    6. Git deploy with readme.
        *       -test on windows follow instructions
        *       -Mac install script
        *       -alias jcat .. YO.  sudo maybe.
        *
        */

        OutputStream outputStream;
        outputStream = System.out;

        OutputStream errorStream;
        errorStream = System.err;


        //System.out.println("*** OK GREG");
        MyInputStreamReader misr = new MyInputStreamReader();  // CUT
        List<InputStream> inputStreams = new ArrayList<>();

        Arrays.stream(fileNames).forEach( (fileName) -> {
            //System.out.println("Building Stream for file: " +fileName);
            try {
                FileInputStream fis = new FileInputStream(new File(fileName));
                inputStreams.add(fis);
            } catch (FileNotFoundException e) {
                System.out.println("File not found:" +  e.getMessage());
                //e.printStackTrace();
            }
        });
        InputStream[] streams = inputStreams.toArray( new InputStream[inputStreams.size()]);
        misr.cat(outputStream, errorStream,streams);

        //System.out.println("*** OK GREG");


        // Working Individual File  -- Close here.
        /*
        try {
            try (FileInputStream fis = new FileInputStream(new File(fileNames[0]))){
                //fis = new FileInputStream(new File("foo.txt"));
                InputStream[] inputstreams = new InputStream[2];
                inputstreams[0] = fis;
                misr.cat(fis);
            } catch (FileNotFoundException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error Opening File: " +  e.getMessage() );
        }
        */


        /**
         * 1. Old School File, FileReader, BufferedReader
         */
        /*
        System.out.println("\n\nOld School");
        File file = new File("foo.txt");
        try (BufferedReader br = new BufferedReader( new FileReader(file))){
            //FileReader fr = new FileReader(file);
            String line;
            while( (line = br.readLine()) != null) {
                System.out.println(line);
            };

        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + file.toString());
        } catch(IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        }
        */


    }
}
