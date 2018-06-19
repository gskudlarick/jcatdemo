package com.ges.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class CatImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test Design:
     *  -No need to use Mockito as we can use ByteArrayStreams.
     */
    @Test
    public void verify_2_valid_input_files() {

        /**
         * Define Test Data
         */
        String file1Text = "File one hello world";
        String file2Text = "File two whats up";
        String fileDelim = "\n";
        InputStream file1 = new ByteArrayInputStream(file1Text.getBytes());
        InputStream file2 = new ByteArrayInputStream(file2Text.getBytes());
        InputStream[] testStreams = {file1, file2};

        /**
         * Define Test Streams
         */
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        ByteArrayOutputStream error  = new ByteArrayOutputStream();

        /**
         * Run Test
         */
        MyInputStreamReader misr = new MyInputStreamReader();
        misr.cat(outputStream, error,testStreams);


        /**
         * Verify Results
         *  -Add \n to end of each file as thats the structure defined.
         */
        byte[] result = outputStream.toByteArray();
        String results =  new String(result);
        String expectedResults = file1Text + fileDelim + file2Text + fileDelim ;
        assertEquals(expectedResults, results);

        /**
         * Verify No Error sent to Error Stream.
         */
        byte[] errorResults = error.toByteArray();
        String errorString = new String(errorResults);
        assertTrue(errorString.length() == 0);

    }

    @Test
    public void verify_2_valid_one_bad_input_files() {

        /**
         * Define Test Data
         */
        String file1Text = "File one hello world";
        String file2Text = "File two whats up";
        String fileDelim = "\n";
        InputStream file1 = new ByteArrayInputStream(file1Text.getBytes());
        InputStream file2 = new ByteArrayInputStream(file2Text.getBytes());
        InputStream[] testStreams = {file1, null, file2};

        /**
         * Define Test Streams
         */
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        ByteArrayOutputStream error  = new ByteArrayOutputStream();

        /**
         * Run Test
         */
        MyInputStreamReader misr = new MyInputStreamReader();
        misr.cat(outputStream, error,testStreams);


        /**
         * Verify Results, 2 Streams Cat, and error Stream ignored.
         *  -Add \n to end of each file as thats the structure defined.
         */
        byte[] result = outputStream.toByteArray();
        String results =  new String(result);
        String expectedResults = file1Text + fileDelim + file2Text + fileDelim ;
        assertEquals(expectedResults, results);

        /**
         * Verify Error sent to Error Stream.
         */
        byte[] errorResults = error.toByteArray();
        String errorString = new String(errorResults);
        assertTrue(errorString.length() > 0);
        assertTrue(errorString.contains("Exception") );

    }

}