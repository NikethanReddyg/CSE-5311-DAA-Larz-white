package com.handson3;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * function x = f(n)
   x = 1;
   for i = 1:n
        for j = 1:n
             x = x + 1;
   Find the runtime of the algorithm mathematically (I should see summations).
 */


public class Main {
    public static void main(String[] args) {

        int n = 1000;
        
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Time Complexity Analysis");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("n");
            row.createCell(1).setCellValue("Time Taken for function1");
            row.createCell(2).setCellValue("Time Taken for function2");

            for(int i = 1; i<=n ; i++){
                long timeTakenForFunction1 = function1(i*10);
                long timeTakenForFunction2 = function2(i*10);
                row = sheet.createRow(i);
                
                row.createCell(0).setCellValue(i);
                row.createCell(1).setCellValue(timeTakenForFunction1);
                row.createCell(2).setCellValue(timeTakenForFunction2);


                System.out.println("Progress for Function1: " +  (100-(double)(n-i)/n *100));
            }


            try (FileOutputStream fileOut = new FileOutputStream("handson3/TimeComplexityAnalysis.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Successully writen data to excel sheet");
            }
        } catch (Exception e) {
            System.out.println("Error while performing the operation: " + e);
        }
    }
    
    public static long function1(int n){
        long startTime = System.nanoTime();
        int x = 1;
        for (int i = 1; i<=n; i++){
            for(int j = 1; j <=n ; j++){
                x=x+1;
            }
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    public static long function2(int n ){
        long startTime = System.nanoTime();
        int x = 1;
        int y = 1;
        for (int i = 1; i<=n; i++){
            for(int j = 1; j <=n ; j++){
                x=x+1;
                y=i+j;
            }
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }
}