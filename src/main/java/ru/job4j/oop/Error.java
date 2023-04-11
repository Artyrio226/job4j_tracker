package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Active : " + active);
        System.out.println("Status : " + status);
        System.out.println("Message : " + message);
    }

    public static void main(String[] args) {
        Error voidError = new Error();
        Error firstError = new Error(true, 12, "Danger!");
        Error secondError = new Error(true, 99, "Crush!");
        voidError.printInfo();
        firstError.printInfo();
        secondError.printInfo();
    }
}
