package com.ges.demo;

import java.io.*;
import java.util.Arrays;

public class MyInputStreamReader implements Cat {
    public void cat(OutputStream os, OutputStream error, InputStream... inputStream) {


        /**
         * TODO Note: Would like ot use Java 8 NIO Files.list(Path) Files.lines(Path) <- Streams
         * but cant as were given Streams in the exercise  to make better challenge
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
