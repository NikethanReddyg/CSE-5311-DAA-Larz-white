import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SelectionSort {

    // Select Minimum and Swap
    public static void main(String[] args) {
        int n = 1000000; // Number of arrays
        int[][] arr = new int[n][];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1]; 
            for (int j = 0; j < i + 1; j++) {
                arr[i][j] = rand.nextInt(); 
            }
            callSortingAlgorithms(arr[i]);
        }
    }

    public static void callSortingAlgorithms(int[] arr) {
        System.out.println("Array Size: "+ arr.length + "\tselectionSort: "+ selectionSort(arr.clone())+ "\t| insertionSort: "+ selectionSort(arr.clone())+ "\t| bubbleSort: "+ bubbleSort(arr.clone()));
    }

    
    public static long insertionSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    public static long selectionSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    public static long bubbleSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                int first = j;
                int second = j + 1;
                if (arr[first] > arr[second]) {
                    int temp = arr[first];
                    arr[first] = arr[second];
                    arr[second] = temp;
                }
            }
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
