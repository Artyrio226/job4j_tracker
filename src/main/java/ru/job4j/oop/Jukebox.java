package ru.job4j.oop;

public class Jukebox {

    public void music(int position) {
        if  (position == 1) {
            System.out.println("Пусть бегут неуклюже");
        } else if  (position == 2) {
            System.out.println("Спокойной ночи");
        } else {
            System.out.println("Песня не найдена");
        }
    }

    public static void main(String[] args) {
        Jukebox jukebox = new Jukebox();
        int pos = 1;
        jukebox.music(pos);
        pos = 2;
        jukebox.music(pos);
        pos = 3;
        jukebox.music(pos);
    }
}
