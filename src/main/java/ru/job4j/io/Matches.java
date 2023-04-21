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
            int tmp = Math.min(count, 3);
                System.out.print(player + " На столе " + count
                        + " шт спичек, возьмите от 1 до " + tmp + " шт: ");

            int matches = Integer.parseInt(input.nextLine());
            if (matches < 1 || matches > tmp) {
                System.out.println("Вы взяли неправильное количество спичек.");
            } else {
                turn = !turn;
                count -= matches;
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
