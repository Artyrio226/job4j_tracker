package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] os1 = o1.split("/", 2);
        String[] os2 = o2.split("/", 2);
        int rsl = os2[0].compareTo(os1[0]);
        return rsl != 0 ? rsl : o1.compareTo(o2);
    }
}
