package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] lf = left.split("\\.");
        String[] rt = right.split("\\.");
        return Integer.compare(Integer.parseInt(lf[0]), Integer.parseInt(rt[0]));
    }
}
