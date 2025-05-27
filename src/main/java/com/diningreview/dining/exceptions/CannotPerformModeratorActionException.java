package com.diningreview.dining.exceptions;


// can inherit exception or runnable exception i think
// exception means that its on compile so will need to handle it with try catch
public class CannotPerformModeratorActionException extends Exception {
    public CannotPerformModeratorActionException(String message) {
        super(message);
    }
}
