package com.handson12;

public class ExpandableArray {
    private int currentSize;
    private int[] elementsArray;

    public ExpandableArray() {
        this.currentSize = 0;
        this.elementsArray = new int[1];
    }

    public int getSize() {
        return this.currentSize;
    }

    public void append(int element) {
        if (this.currentSize == this.elementsArray.length) {
            increaseCapacity(2 * this.elementsArray.length);
        }
        this.elementsArray[this.currentSize] = element;
        this.currentSize++;
    }

    public int getElementAt(int index) {
        if (index < 0 || index >= this.currentSize) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return this.elementsArray[index];
    }

    public void removeElement(int element) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.elementsArray[i] == element) {
                for (int j = i; j < this.currentSize - 1; j++) {
                    this.elementsArray[j] = this.elementsArray[j + 1];
                }
                this.currentSize--;
                return;
            }
        }
        throw new IllegalArgumentException("Element not found");
    }

    private void increaseCapacity(int newCapacity) {
        int[] newArray = new int[newCapacity];
        System.arraycopy(this.elementsArray, 0, newArray, 0, this.currentSize);
        this.elementsArray = newArray;
    }

    public static void main(String[] args) {
        ExpandableArray myArray = new ExpandableArray();
        myArray.append(15);
        myArray.append(25);
        myArray.append(35);
        myArray.append(45);
        myArray.append(55);

        System.out.println("Array size: " + myArray.getSize());
        System.out.print("Values in the Array: ");
        for (int i = 0; i < myArray.getSize(); i++) {
            System.out.print(myArray.getElementAt(i) + " ");
        }

        myArray.append(65);
        System.out.print("\nValues after adding: ");
        for (int i = 0; i < myArray.getSize(); i++) {
            System.out.print(myArray.getElementAt(i) + " ");
        }
        System.out.println("\nArray size after adding: " + myArray.getSize());
        System.out.println("Value at index 2: " + myArray.getElementAt(2));

        myArray.removeElement(25);
        System.out.print("Values after removing 25: ");
        for (int i = 0; i < myArray.getSize(); i++) {
            System.out.print(myArray.getElementAt(i) + " ");
        }
        System.out.println("\nArray size after removing: " + myArray.getSize());
    }
}

