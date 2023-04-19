package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            if (count > 3) {
                System.out.print(player + " На столе " + count
                        + " шт спичек, возьмите от 1 до 3 шт: ");
            } else {
                System.out.print(player + " На столе " + count
                        + " шт спичек, возьмите от 1 до " + count + " шт: ");
            }
            int matches = Integer.parseInt(input.nextLine());
            if (matches > 3 || matches > count) {
                System.out.println("Вы взяли слишком много спичек.");
            } else {
                turn = !turn;
                switch (matches) {
                    case 1 -> {
                        System.out.println("Отлично!! Вы выбрали 1 спичку!");
                        count -= 1;
                    }
                    case 2 -> {
                        System.out.println("Отлично!! Вы выбрали 2 спички!");
                        count -= 2;
                    }
                    default -> {
                        System.out.println("Отлично!! Вы выбрали 3 спички!");
                        count -= 3;
                    }
                }
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
