package com.handson4;

public class Problem0 {
    public static void main(String[] args) {
        int res = fibonacci(5);
        System.out.println("\n"+res);
    }

    public static int fibonacci(int n){
        System.out.print(" fib(" + n + ") ->");
        if(n == 0 || n == 1){
            return n;
        }
         return fibonacci(n-1) + fibonacci(n-2);
    }
}
