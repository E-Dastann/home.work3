package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arrays = new int [] {10, 9,3,2, 1, 5, 6, 8};

        Arrays.sort(arrays);
        System.out.println(Arrays.deepToString(new int[][]{arrays}));
    }
}
