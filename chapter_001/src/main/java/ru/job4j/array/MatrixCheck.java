package ru.job4j.array;

public class MatrixCheck {

    public boolean mono(boolean[][] data) {

        boolean result = true;
        int maxindex = data.length - 1;

        for (int idx = 1; idx < data.length; idx++) {
            if (data[idx][idx] != data[idx - 1][idx - 1]) {
                result = false;
                break;
            }
        }
        if (result) {
            for (int idx = 1; idx < data.length; idx++) {
                if (data[maxindex - idx][idx] != data[maxindex - (idx - 1)][idx - 1]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
