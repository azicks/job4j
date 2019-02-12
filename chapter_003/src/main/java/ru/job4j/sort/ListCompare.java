package ru.job4j.sort;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        for (int i = 0; i != left.length(); i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }
        return (result == 0) && right.length() > left.length() ? -1 : result;
    }
}