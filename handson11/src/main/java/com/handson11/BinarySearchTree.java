package com.handson11;

class Node {
    int key;
    Node left, right;
 
    public Node(int item)
    {
        key = item;
        left = null;
        right = null;
    }
}

public class BinarySearchTree{

    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertKey(root, key);
    }

    Node insertKey(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertKey(root.left, key);
        else if (key > root.key)
            root.right = insertKey(root.right, key);

        return root;
    }

    void inorder() {
        inorderTraverse(root);
    }

    void inorderTraverse(Node root) {
        if (root != null) {
            inorderTraverse(root.left);
            System.out.print(root.key + " ");
            inorderTraverse(root.right);
        }
    }

    public Node searchBST(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key < key)
            return searchBST(root.right, key);

        return searchBST(root.left, key);
    }



    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        binarySearchTree.insert(10);
        binarySearchTree.insert(90);
        binarySearchTree.insert(20);
        binarySearchTree.insert(80);
        binarySearchTree.insert(30);
        binarySearchTree.insert(70);
        binarySearchTree.insert(40);

        System.out.println("Inorder traversal of the given tree");
        binarySearchTree.inorder();

        System.out.println("Searching for 40 in the tree");
        Node searchResult = binarySearchTree.searchBST(binarySearchTree.root, 40);
        if (searchResult != null) {
            System.out.println("Found node with key " + searchResult.key);
        } else {
            System.out.println("Key not found in the tree.");
        }
    }
}