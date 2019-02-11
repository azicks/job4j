
package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int)Math.ceil(list.size() / (float)rows);
        int listIndex = 0;
        int[][] array = new int[rows][cells];
        for (int row = 0; row != rows; row++){
            for (int ceil = 0; ceil != cells; ceil++) {
                if(listIndex < list.size()) {
                    array[row][ceil] = list.get(listIndex++);
                }
                else {
                    array[row][ceil] = 0;
                }
            }
        }
        return array;
    }
}