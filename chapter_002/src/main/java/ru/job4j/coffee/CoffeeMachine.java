package ru.job4j.coffee;

import java.util.ArrayList;
import java.util.Arrays;

public class CoffeeMachine {
    private final int[] values = {50, 100, 500, 1000};
    private final int[] coins = {10, 5, 2, 1};

    public int[] change(int value, int price) {
        ArrayList<Integer> changeCoins = new ArrayList<>();
        int[] result = null;

        if (Arrays.binarySearch(values, value) < 0) {
            System.out.println("Это не купюра");
        } else if (value < price) {
            System.out.println("Недостаточно денег");
        } else {
            System.out.println(String.format("Купюра %d", value));
            int change = value - price;
            for (int i = 0; i != coins.length; i++) {
                if (change - coins[i] >= 0) {
                    change -= coins[i];
                    changeCoins.add(coins[i--]);
                }
            }
            result = changeCoins.stream().mapToInt(i -> i).toArray();
        }
        return result;
    }
}
