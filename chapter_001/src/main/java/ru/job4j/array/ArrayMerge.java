package ru.job4j.array;

public class ArrayMerge {

    public int[] merge(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int idx1 = 0, idx2 = 0;

        for (int i = 0; i < result.length; i++) {
            if ((idx1 < array1.length) && (array1[idx1] <= array2[idx2])) {
                result[i] = array1[idx1];
                idx1++;
            } else if (idx2 < array2.length) {
                result[i] = array2[idx2];
                idx2++;
            }
        }
        return result;
    }
}
