package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] os1 = o1.split("/", 2);
        String[] os2 = o2.split("/", 2);
        int rsl = os2[0].compareTo(os1[0]);
        if (rsl == 0) {
            rsl = Integer.compare(os1.length, os2.length);
        }
        return rsl == 0 ? os1[1].compareTo(os2[1]) : rsl;
    }
}
