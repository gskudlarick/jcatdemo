package com.ges.demo;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MyInputStreamReader implements Cat {
    public void cat(OutputStream os, OutputStream error, InputStream ... inputStream) {


        /**
         * TODO Note: Would like ot use Java 8 NIO Files.readAllLines(Path)
         * but cant as were given Streams in the exercize to make better challenge
         */

        OutputStreamWriter osw = new OutputStreamWriter(os);
        OutputStreamWriter esw = new OutputStreamWriter(error);


        Arrays.stream(inputStream).forEach( (x) -> {
            try {
                InputStreamReader isr = new InputStreamReader(x);
                BufferedReader br = new BufferedReader(isr);
                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        //System.out.println("\t" +line);
                        osw.write(line + "\n");
                        osw.flush();
                    }
                } catch (IOException e) {
                    try {
                        esw.write("Error reading stream:" + e.getMessage() + "\n");
                        esw.flush();
                    } catch (IOException e1) {
                        //
                    }
                }

                try {
                    br.close();
                    x.close();
                } catch (IOException e) {
                    try {
                        esw.write("Error Closing Streams:" + e.getMessage() + "\n");
                        esw.flush();
                    } catch (IOException e1) {
                        //
                    }
                }

            } catch (NullPointerException npe) {
                try {
                    esw.write("Exception: Null Input Stream passed in.  Continue with next file");
                    esw.flush();
                } catch (IOException e) {
                    //
                }
            }

        });

        // Cleanup
        try {
            esw.close();
            osw.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }




    }

}
