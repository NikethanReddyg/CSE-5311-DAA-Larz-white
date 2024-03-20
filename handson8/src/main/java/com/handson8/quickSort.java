package com.handson8;

import java.util.Arrays;

public class quickSort {

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static int quickSelect(int[] arr, int low, int high, int i) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            if (pivotIndex == i)
                return arr[pivotIndex];
            else if (pivotIndex < i)
                return quickSelect(arr, pivotIndex + 1, high, i);
            else
                return quickSelect(arr, low, pivotIndex - 1, i);
        }
        return arr[low]; 
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9,8,7,6};
        System.out.println("Array before sort" + Arrays.toString(arr));
        int n = arr.length;
        int i = 3; // Find the 3rd order statistic

        int ithOrderStatistic = quickSelect(arr, 0, n - 1, i - 1);
        System.out.println("Array after sort" + Arrays.toString(arr));
        System.out.println("The " + i + " th order statistic is: " + ithOrderStatistic);
    }
}


/*
 * OUTPUT: 
 * Array before sort[9, 8, 7, 6]
 * Array after sort[6, 7, 8, 9]
 * The 3 th order statistic is: 8
 */
