package com.handson10;

public class HashTableImplementation {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.putValue(1, 90);
        hashTable.putValue(2, 70);
        hashTable.putValue(3, 40);
        hashTable.putValue(4, 90);
        hashTable.putValue(5, 30);

        System.out.println("Hash Table Size: " + hashTable.size());
        hashTable.printHashTable();

        System.out.println("Value at Key 1 " + hashTable.getValue(1));
        System.out.println("Value at Key 2 " + hashTable.getValue(2));
        System.out.println("Value at Key 3 " + hashTable.getValue(3));
        System.out.println("Value at Key 4 " + hashTable.getValue(4));

        hashTable.removeValue(2);
        System.out.println("Hash Table Size after removal: " + hashTable.size());
        hashTable.printHashTable();
    }
}

/* Output
 * Hash Table Size: 5
Index 0: 
Index 1: (1, 90) 
Index 2: (2, 70) 
Index 3: (3, 40) 
Index 4: (4, 90) 
Index 5: (5, 30) 
Index 6: 
Index 7: 
Index 8: 
Index 9: 
Value at Key 1 90
Value at Key 2 70
Value at Key 3 40
Value at Key 4 90
Hash Table Size after removal: 4
Index 0: 
Index 1: (1, 90) 
Index 2: 
Index 3: (3, 40) 
Index 4: (4, 90) 
Index 5: (5, 30) 
Index 6: 
Index 7: 
Index 8: 
Index 9: 
 */
