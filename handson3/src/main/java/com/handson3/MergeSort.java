package com.handson3;
import java.util.*;

// Implement merge sort, upload your code to github and show/test it on the array [5,2,4,7,1,3,2,6].
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = new int[]{5,2,4,7,1,3,2,6};

        System.out.println("Arrays Before Sorting: " + Arrays.toString(arr));
        
        // merge Sort -> Divide and Merge
        mergeSort(arr, 0, arr.length-1);

        System.out.println("Arrays After Sorting: " + Arrays.toString(arr));

        /*
         * Output: 
         * Arrays Before Sorting: [5, 2, 4, 7, 1, 3, 2, 6]
         * Arrays After Sorting: [1, 2, 2, 3, 4, 5, 6, 7]
         */
         
    }

    public static void mergeSort(int[] arr, int left, int right){
        if(left==right) return;
        int mid = (left+right)/2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[]  arr, int low, int mid, int high){
        List<Integer> list = new LinkedList<>();

        int left = low;
        int right = mid+1;

        while(left<=mid && right<= high){
            if(arr[left]<arr[right]){
                list.add(arr[left]);
                left++;
            } else{
                list.add(arr[right]);
                right++;
            }
        }

        while(left<=mid){
            list.add(arr[left]); left++;
        }

        while(right<=high){
            list.add(arr[right]); right++;
        }

        for(int i = low; i<=high; i++){
            arr[i] = list.get(i-low);
        }
    }
}
