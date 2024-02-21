package com.handson5;

import java.util.*;

public class MinHeap<T extends Comparable<T>> {

    private T[] array;
    private int size;

    public MinHeap(int capacity) {
        array = (T[]) new Comparable[capacity];
        size = 0;
    }

    private int getParent(int i) {
        return (i - 1) >> 1;
    }

    private int getLeftChild(int i) {
        return (i << 1) + 1;
    }

    private int getRightChild(int i) {
        return (i << 1) + 2;
    }

    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return array[0];
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T root = array[0];
        array[0] = array[size - 1];
        size--;
        minHeapify(0);
        return root;
    }

    public void insert(T element) {
        if (size == array.length) {
            resizeArray(size * 2);
        }
        array[size++] = element;
        int i = size - 1;
        while (i > 0 && array[getParent(i)].compareTo(array[i]) > 0) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    public void buildMinHeap(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        array = arr;
        size = arr.length;
        for (int i = (size / 2) - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    private void minHeapify(int i) {
        int smallest = i;
        int leftChild = getLeftChild(i);
        int rightChild = getRightChild(i);

        if (leftChild < size && array[leftChild].compareTo(array[smallest]) < 0) {
            smallest = leftChild;
        }

        if (rightChild < size && array[rightChild].compareTo(array[smallest]) < 0) {
            smallest = rightChild;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public void printMinHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
            int level = (int) Math.floor(Math.log(i + 1) / Math.log(2));
            for (int j = 0; j < level * 2; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private void resizeArray(int newSize) {
        T[] temp = (T[]) new Comparable[newSize];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>(10);

        minHeap.insert(6);
        minHeap.insert(8);
        minHeap.insert(2);
        minHeap.insert(3);
        minHeap.insert(3);
        minHeap.insert(0);
        minHeap.insert(15);
        minHeap.insert(50);

        System.out.println("Built heap:");
        minHeap.printMinHeap();

        System.out.println("Minimum element: " + minHeap.peek());

        System.out.println("Popped element: " + minHeap.pop());

        System.out.println("Heap after pop:");

        minHeap.printMinHeap();
    }
}