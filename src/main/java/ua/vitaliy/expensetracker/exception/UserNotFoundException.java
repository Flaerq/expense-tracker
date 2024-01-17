package ua.vitaliy.expensetracker.exception;

public class UserNotFoundException extends RuntimeException{

    private String message = "User not found in database";

    public String getMessage(){
        return message;
    }
}
