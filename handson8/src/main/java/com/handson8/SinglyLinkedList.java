package com.handson8;

import java.util.Random;

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class SinglyLinkedList {
    private final int length;
    private Node head;

    public SinglyLinkedList(int length) {
        this.length = length;
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int numberOfNodes() {
        if (isEmpty()) {
            return 0;
        } else {
            int count = 1;
            Node node = head;
            while (node.next != null) {
                count++;
                node = node.next;
            }
            return count;
        }
    }

    public void insert(Node node) {
        if (numberOfNodes() == length) {
            System.out.println("OVERFLOW!!!  Unable to insert the element . Linked List is full." );
        } else {
            if (isEmpty()) {
                head = node;
            } else {
                Node emptyNext = head;
                while (emptyNext.next != null) {
                    emptyNext = emptyNext.next;
                }
                emptyNext.next = node;
            }
        }
    }

    public void delete(Node node) {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
        } else {
            if (head == node) {
                head = null;
            } else {
                Node previousNode = head;
                while (previousNode.next != node) {
                    previousNode = previousNode.next;
                }
                previousNode.next = node.next;
            }
        }
    }

    public Node headNode() {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
            return null;
        } else {
            return head;
        }
    }

    public Node tailNode() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return null;
        } else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            return lastNode;
        }
    }

    public Node nodeAtIndex(int index) {
        if (isEmpty()) {
            System.out.println("UNDERFLOW!!!");
            return null;
        } else {
            Node node = head;
            for (int i = 0; i < index; i++) {
                if (node.next != null) {
                    node = node.next;
                } else {
                    System.out.println("Index out of bounds");
                    return null;
                }
            }
            return node;
        }
    }

    public void printLinkedList() {
        if (!isEmpty()) {
            Node node = head;
            System.out.println("Nodes: " + node);
            while (node.next != null) {
                node = node.next;
                System.out.print(" -> " + node.value + " [ " + node + " ] ");
            }
        }
        System.out.println();
        System.out.println("Size of LinkedList : " + numberOfNodes());
        System.out.println("Head Element: " + headNode().value + " | " + headNode());
        System.out.println("Tail Element: " + tailNode().value + " | " + tailNode());
    }

    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList(10);

        Random random = new Random();
        System.out.println("Add 5 items to the list");
        for (int i = 0; i < 5; i++) {
            Node node = new Node(random.nextInt(101));
            linkedList.insert(node);
        }
        linkedList.printLinkedList();

        System.out.println("Add 2 items to the List");
        for (int i = 0; i < 2; i++) {
            Node node = new Node(random.nextInt(101));
            linkedList.insert(node);
        }
        linkedList.printLinkedList();
        System.out.println("Item at index 5: " + linkedList.nodeAtIndex(5));

        System.out.println("Remove the tail Element 5 times from the list");
        for (int i = 0; i < 5; i++) {
            linkedList.delete(linkedList.tailNode());
        }
        linkedList.printLinkedList();
        System.out.println("Linked List's number of items after pop: " + linkedList.numberOfNodes());
    }
}

