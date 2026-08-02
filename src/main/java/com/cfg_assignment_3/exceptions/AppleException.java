package com.cfg_assignment_3.exceptions;

//set up exception for empty list
public class AppleException extends Exception {
    //throw error instead of returning empty list and save msg for later
    public AppleException(String message) {
        super(message);
    }
}