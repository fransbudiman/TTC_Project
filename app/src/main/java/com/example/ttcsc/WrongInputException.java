package com.example.ttcsc;

@SuppressWarnings("serial")
public class WrongInputException extends Exception {

    public WrongInputException(String message) {
        super(message);
    }

}