package com.ges.demo;

import java.io.*;
import java.util.Arrays;

public class MyInputStreamReader implements Cat {
    public void cat(OutputStream os, OutputStream error, InputStream... inputStream) {


        /**
         *  TODO: Summarize  NIO vs. IO
         *   -This uses Java I/O which is stream based, good for Few Threads large data sets :Traditional servers
         *   -NIO is buffer based, good for handling multiple connections in single thread with small data sets.
         *    -but can get more complex.
         *    -Java 8 has good NIO support Files returning Streams
         *      -Files.list() returns steam of files.
         *      -Files.lines() returns stream with lines of files.
         *      e.g. https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#lines-java.nio.file.Path-
         */

        OutputStreamWriter osw = new OutputStreamWriter(os);
        OutputStreamWriter esw = new OutputStreamWriter(error);

        Arrays.stream(inputStream).forEach((is) -> {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                try {
                    while ((line = br.readLine()) != null) {
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
                } finally {

                    try {
                        br.close();
                        is.close();
                    } catch (IOException e) {
                        try {
                            esw.write("Error Closing Streams:" + e.getMessage() + "\n");
                            esw.flush();
                        } catch (IOException e1) {
                            //
                        }
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
            //
        }


    }

}
