package com.handson8;

import java.util.Random;

class FixedSizeStack {
    private final int maxSize;
    private Integer[] array;
    private int top;

    public FixedSizeStack(int maxSize) {
        this.maxSize = maxSize;
        this.array = new Integer[maxSize];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int item) {
        if (isFull()) {
            System.out.println("Overflow");
        } else {
            array[++top] = item;
        }
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("Underflow");
        } else {
            array[top--] = null;
        }
    }

    public Integer firstElement() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return null;
        } else {
            return array[0];
        }
    }

    public Integer lastElement() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return null;
        } else {
            return array[top];
        }
    }

    public Integer elementAt(int index) {
        if (isEmpty() || index < 0 || index > top) {
            System.out.println("Underflow or Index out of bounds");
            return null;
        } else {
            return array[index];
        }
    }

    public int numberOfElements() {
        return top + 1;
    }

    public void printStack() {
        System.out.print("Stack: [");
        for (int i = 0; i <= top; i++) {
            System.out.print(array[i] + (i != top ? ", " : ""));
        }
        System.out.println("]");
        System.out.println("Number of items: " + numberOfElements());
        System.out.println("First item: " + firstElement());
        System.out.println("Last item: " + lastElement());
    }

    public static void main(String[] args) {
        FixedSizeStack stack = new FixedSizeStack(10);

        stack.printStack();

        Random random = new Random();
        System.out.println("Add 5 numbers into the stack");
        for (int i = 0; i < 5; i++) {
            stack.push(random.nextInt(101));
        }
    
        stack.printStack();

        System.out.println("Add 2 numbers into the stack");
        for (int i = 0; i < 2; i++) {
            stack.push(random.nextInt(101));
        }

        stack.printStack();
        System.out.println("Item at index 5: " + stack.elementAt(5));

        System.out.println("Remove 5 items from the stack");
        for (int i = 0; i < 5; i++) {
            stack.pop();
        }
        stack.printStack();
        System.out.println("Stack number of items after pop: " + stack.numberOfElements());
    }
}
