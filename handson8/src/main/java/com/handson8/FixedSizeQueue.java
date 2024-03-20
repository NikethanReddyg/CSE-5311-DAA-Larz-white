package com.handson8;

import java.util.Random;

public class FixedSizeQueue {
    private final int maxSize;
    private Integer[] array;
    private int head;
    private int tail;

    public FixedSizeQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new Integer[maxSize];
        this.head = 0;
        this.tail = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return head == (tail + 1) % maxSize || (head == 0 && tail == maxSize - 1);
    }

    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("OVERFLOW!!!");
        } else {
            array[tail] = item;
            tail = (tail + 1) % maxSize;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
            return -1; 
        } else {
            int x = array[head];
            array[head] = null;
            head = (head + 1) % maxSize;
            return x;
        }
    }

    public Integer headElement() {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
            return null;
        } else {
            return array[head];
        }
    }

    public Integer tailElement() {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
            return null;
        } else {
            return array[(tail - 1 + maxSize) % maxSize];
        }
    }

    public Integer elementAtIndexBehindHead(int index) {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
            return null;
        } else if ((head + index) % maxSize >= tail) {
            System.out.println("Index out of bounds");
            return null;
        } else {
            return array[(head + index) % maxSize];
        }
    }

    public int numberOfElements() {
        if (head <= tail) {
            return tail - head;
        } else {
            return maxSize - head + tail;
        }
    }

    public void printQueue() {
        System.out.print("Queue: [");
        for (int i = head; i != tail; i = (i + 1) % maxSize) {
            System.out.print(array[i] + (i != tail - 1 ? ", " : ""));
        }
        System.out.println("]");
        System.out.println("Number of items: " + numberOfElements());
        System.out.println("Head: " + head + ", Element: " + headElement());
        System.out.println("Tail: " + tail + ", Element: " + tailElement());
    }

    public static void main(String[] args) {
        FixedSizeQueue queue = new FixedSizeQueue(10);

        queue.printQueue();

        Random random = new Random();
        System.out.println("Add 5 numbers into the queue");
        for (int i = 0; i < 5; i++) {
            queue.enqueue(random.nextInt(101));
        }
        
        queue.printQueue();

        System.out.println("Add 2 numbers into the queue");
        for (int i = 0; i < 2; i++) {
            queue.enqueue(random.nextInt(101));
        }
        queue.printQueue();
        System.out.println("Item at index 5: " + queue.elementAtIndexBehindHead(5));

        System.out.println("Remove 5 items from the queue");
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
        }
        queue.printQueue();
        System.out.println("Queue number of items after dequeue: " + queue.numberOfElements());
    }
}
