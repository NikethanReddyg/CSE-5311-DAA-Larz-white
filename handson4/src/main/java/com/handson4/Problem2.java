package com.handson4;

import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        int arr[] = new int[] {1, 2, 2, 3, 4, 4, 4, 5, 5};
        System.out.println(Arrays.toString(removeDuplicates(arr)));
    }
    
    public static int[] removeDuplicates(int[] arr){
        List<Integer> list = new LinkedList<Integer>();
        list.add(arr[0]);
        for(int i = 1 ; i< arr.length; i++){
            if(arr[i]!= arr[i-1]){
                list.add(arr[i]);
            }
        }
        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }

        return array;
    }
}
