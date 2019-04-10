package com.willsmithte.DBSystemBE.util;

/**
 * Created by Will Smith on 15/3/19.
 */
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
