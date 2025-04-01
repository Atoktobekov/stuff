package org.example.view;

public class ErrorView {
    public static void printErrorMessage(String message){
        System.out.println("---------------------- ERROR MESSSAGE START ------------------");
        System.out.println(String.format("Error! %s", message));
        System.out.println("---------------------- ERROR MESSSAGE END ------------------");
    }
}
