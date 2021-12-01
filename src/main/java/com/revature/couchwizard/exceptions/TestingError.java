package com.revature.couchwizard.exceptions;

public class TestingError extends RuntimeException{
    public TestingError(){ super("Something went wrong. Cannot complete test!");}
}
