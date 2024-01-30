package com.daaAssignments;

import java.io.FileOutputStream;
import java.util.Random;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Sorting {
    public static void main(String[] args) {
        int n = 1000000;
        Random rand = new Random();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sorting Performance Results");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("arraySize");
            row.createCell(1).setCellValue("selectionSort");
            row.createCell(2).setCellValue("insertionSort");
            row.createCell(3).setCellValue("bubbleSort");

            for (int i = 1; i <= n; i++) {

                int size = i * 5;
                
                int[] arr = new int[size];
                for (int j = 0; j < size; j++) {
                    arr[j] = rand.nextInt();
                }
                long selectionSortTimeTaken = selectionSort(arr.clone());
                long insertionSortTimeTaken = insertionSort(arr.clone());
                long bubbleSortTimeTaken = bubbleSort(arr.clone());

                row = sheet.createRow(i);
                row.createCell(0).setCellValue(size);
                row.createCell(1).setCellValue(selectionSortTimeTaken);
                row.createCell(2).setCellValue(insertionSortTimeTaken);
                row.createCell(3).setCellValue(bubbleSortTimeTaken);

                System.out.println("Array Size: " + size + " | Progress: " +  (100-(double)(n-i)/n *100));
            }

            try (FileOutputStream fileOut = new FileOutputStream("SortingPerformanceResults.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Successully writen data to excel sheet");
            }
        } catch (Exception e) {
            System.out.println("Error while performing the operation: " + e);
        }
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
        return endTime - startTime;
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
        return endTime - startTime;
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
        return endTime - startTime;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
