
package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int)Math.ceil(list.size() / (float)rows);
        int[][] array = new int[rows][cells];
        for (Integer i : list) {
            int row = list.indexOf(i) / rows;
            int cell = list.indexOf(i) - row * rows;
            array[row][cell] = i;
        }
        return array;
    }
}