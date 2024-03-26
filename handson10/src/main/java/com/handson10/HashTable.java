package com.handson10;

class Node {
    int key;
    int value;
    Node next;
    Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class HashTable {
    private static int capacity = 10;
    private static double LOAD_FACTOR = 0.75;

    private Node[] table;
    private int size;

    public HashTable() {
        table = new Node[capacity];
        size = 0;
    }

    private int hash(int key) {
        int hash = key;
        hash ^= (hash >>> 20) ^ (hash >>> 12);
        return hash ^ (hash >>> 7) ^ (hash >>> 4);
    }

    private int getIndex(int key) {
        int hash = hash(key);
        return hash % table.length;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Node[] newTable = new Node[newCapacity];
        for (Node node : table) {
            while (node != null) {
                Node next = node.next;
                int index = getIndex(node.key);
                node.next = newTable[index];
                if (newTable[index] != null) {
                    newTable[index].prev = node;
                }
                newTable[index] = node;
                node.prev = null;
                node = next;
            }
        }
        table = newTable;
    }


    private void checkLoadFactor() {
        double loadFactor = (double) size / table.length;
        if (loadFactor >= LOAD_FACTOR) {
            resizeTable();
        } else if (loadFactor <= 0.25 && table.length > capacity) {
            resizeTable();
        }
    }

    public void putValue(int key, int value) {
        int index = getIndex(key);
        Node newNode = new Node(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node current = table[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
        checkLoadFactor();
    }

    public int getValue(int key) {
        int index = getIndex(key);
        Node current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return -1; // Key not found
    }

    public void removeValue(int key) {
        int index = getIndex(key);
        Node current = table[index];
        while (current != null) {
            if (current.key == key) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    table[index] = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printHashTable() {
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            System.out.print("Index " + i + ": ");
            while (current != null) {
                System.out.print("(" + current.key + ", " + current.value + ") ");
                current = current.next;
            }
            System.out.println();
        }
    }
}
