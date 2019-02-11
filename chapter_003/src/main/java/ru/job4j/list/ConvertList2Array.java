
package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {
    private List<int[]> li = new ArrayList<>();

    public void add(int[] data) {
        this.li.add(data);
    }

    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil(list.size() / (float) rows);
        int[][] array = new int[rows][cells];
        for (Integer i : list) {
            int row = list.indexOf(i) / rows;
            int cell = list.indexOf(i) - row * rows;
            array[row][cell] = i;
        }
        return array;
    }

    public List<Integer> convert() {
        List<Integer> result = new ArrayList<>();
        for (int[] ai : this.li) {
            for (int i : ai) {
                result.add(i);
            }
        }
        return result;
    }
}