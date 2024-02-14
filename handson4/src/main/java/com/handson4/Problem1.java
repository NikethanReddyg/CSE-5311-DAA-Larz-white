package com.handson4;

import java.util.Arrays;

public class Problem1 {
    public static void main(String[] args) {
        int[][] array1 = { { 1, 3, 5, 7 }, { 2, 4, 6, 8 }, { 0, 9, 10, 11 } };
        int[] mergedArray = mergeSortedArrays(array1);
        System.out.println(Arrays.toString(mergedArray));

        int[][] array2 = { { 1, 3, 7 }, { 2, 4, 8 }, { 9, 10, 11 } };
        mergedArray = mergeSortedArrays(array2);
        System.out.println(Arrays.toString(mergedArray));

    }

    public static int[] mergeSortedArrays(int[][] array) {
        int n = 0;

        for(int i = 0; i< array.length; i ++){
            n+= array[i].length;
        }

        int[] newArray = new int[n];
        int[] index = new int[]{ 0, 0, 0 };
        int currentIndex = 0;

        while (currentIndex < n) {
            int minVal = Integer.MAX_VALUE;
            int min = -1;

            for (int i = 0; i < 3; i++) {
                if (index[i] < array[i].length && array[i][index[i]] < minVal) {
                    minVal = array[i][index[i]];
                    min = i;
                }
            }

            if (min != -1) {
                newArray[currentIndex] = minVal;
                index[min]++;
                currentIndex++;
            }
        }

        return newArray;
    }
}
