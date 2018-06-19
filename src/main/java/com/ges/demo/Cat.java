package com.ges.demo;


import java.io.InputStream;
import java.io.OutputStream;

/*
 * Create JCat class that implements Cat.
 *
 * JCat operates similarly to unix cat command.
 *
 * It will take zero or more file names from the command-line and print the contents to System.out.
 * Errors should not stop the processing of files and can be printed to System.err.
 *
 * Unit tests should test JCat via Cat interface - using actual files is discouraged.
 *
 * Bonus - create separate functional tests that use files to increase test code coverage.
 *
 */

public interface Cat {
    public void cat(OutputStream output, OutputStream error, InputStream... inputs);
//    public void list(String... inputs);
}