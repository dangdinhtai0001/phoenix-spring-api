package com.phoenix.others;

import org.junit.Test;

public class TestCoinChange {

    @Test
    public void testChange() {

        int amount = 12;
        int[] coins = {2, 4, 5};

        System.out.println(
                "Number of combinations of getting change for " + amount + " is: " + CoinChange.change(coins, amount));
        System.out.println(
                "Minimum number of coins required for amount :"
                        + amount
                        + " is: "
                        + CoinChange.minimumCoins(coins, amount));
    }

    // A basic print method which prints all the contents of the array
    private void printAmount(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
