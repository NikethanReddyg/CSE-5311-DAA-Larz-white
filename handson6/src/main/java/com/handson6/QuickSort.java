package com.handson6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QuickSort {
    public static void main(String[] args) {
       
        String fileName = "handson6/quick_sort_times.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Quick Sort Times");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("n");
            headerRow.createCell(1).setCellValue("Best Time");
            headerRow.createCell(2).setCellValue("Average Time");
            headerRow.createCell(3).setCellValue("Worst Time");

            for (int i = 2; i < 100; i++) {
                int  n = i * 10;
                int[] arr = new int[n];
                Random random = new Random();
                for (int j = 0; j < n; j++) {
                    arr[j] = random.nextInt();
                }

                int[] bestCaseInput = generateBestCaseInput(arr.clone());
                int[] averageCaseInput = arr;
                int[] worstCaseInput = generateWorstCaseInput(arr.clone());

                long bestTime = measureTime(bestCaseInput);
                long averageTime = measureTime(averageCaseInput);
                long worstTime = measureTime(worstCaseInput);

                Row row = sheet.createRow(i - 1);
                row.createCell(0).setCellValue(n);
                row.createCell(1).setCellValue(bestTime);
                row.createCell(2).setCellValue(averageTime);
                row.createCell(3).setCellValue(worstTime);
            }

            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("Excel file has been created successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long measureTime(int[] arr) {
        long startTime = System.nanoTime();
        quickSortWithNonRandomPivot(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }


    public static void quickSortWithNonRandomPivot(int[] arr, int lowIndex, int highIndex){
        if(lowIndex>=highIndex) return;
        
        int pivot = arr[highIndex];
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while(leftPointer<rightPointer){

            while(arr[leftPointer] <= pivot && leftPointer< rightPointer){
                leftPointer++;
            }

            while(arr[rightPointer]>= pivot && leftPointer< rightPointer){
                rightPointer--;
            }

            swap(arr, leftPointer, rightPointer);
        }
        swap(arr, leftPointer, highIndex);
        quickSortWithNonRandomPivot(arr, lowIndex, leftPointer-1);
        quickSortWithNonRandomPivot(arr, leftPointer+1, highIndex);
        return;
    }

    public static void quickSortWithRandomPivot(int[] arr, int lowIndex, int highIndex){
        if(lowIndex>=highIndex) return;

        int pivotIndex = new Random().nextInt(highIndex-lowIndex)+lowIndex;
        
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, highIndex);
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while(leftPointer<rightPointer){

            while(arr[leftPointer] <= pivot && leftPointer< rightPointer){
                leftPointer++;
            }

            while(arr[rightPointer]>= pivot && leftPointer< rightPointer){
                rightPointer--;
            }

            swap(arr, leftPointer, rightPointer);
        }
        swap(arr, leftPointer, highIndex);
        quickSortWithRandomPivot(arr, lowIndex, leftPointer-1);
        quickSortWithRandomPivot(arr, leftPointer+1, highIndex);
        return;
    }
    
    public static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left]= arr[right];
        arr[right] = temp; 
    }

    public static int[] generateBestCaseInput(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public static int[] generateWorstCaseInput(int[] arr) {
        Arrays.sort(arr);
        reverse(arr);
        return arr;
    }

    public static void reverse(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
