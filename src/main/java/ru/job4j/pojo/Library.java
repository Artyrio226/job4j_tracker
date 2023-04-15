package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book potter = new Book("Harry Potter", 223);
        Book code = new Book("Clean code", 413);
        Book lord = new Book("The Lord of the Rings", 1211);
        Book farm = new Book("Animal Farm", 52);
        Book[] books = new Book[4];
        books[0] = potter;
        books[1] = code;
        books[2] = lord;
        books[3] = farm;
        for (int index = 0; index < books.length; index++) {
            System.out.println(books[index].getName() + " - " + books[index].getPages() + " pages");
        }
        Book ex = books[0];
        books[0] = books[3];
        books[3] = ex;
        System.out.println("\nReplace index 0 to 3.");
        for (int index = 0; index < books.length; index++) {
            System.out.println(books[index].getName() + " - " + books[index].getPages() + " pages");
        }
        System.out.println("\nShown only \"Clean code\"");
        for (int index = 0; index < books.length; index++) {
            String tmp = books[index].getName();
            if (tmp.equals("Clean code")) {
                System.out.println(books[index].getName() + " - " + books[index].getPages() + " pages");
            }
        }
    }
}
