package com.example.sagesayings.AVLImplementation;

import java.util.Stack;

public class AVLVerseTree {

    private VerseNode root;

    public AVLVerseTree() {
    }

    public AVLVerseTree(VerseNode root) {
        this.root = root;
    }

    public VerseNode insert(VerseNode root, VerseNode newNode){
        if (root == null) {
            root = newNode;
            return root;
        } else if (root.getVerseNumber() > newNode.getVerseNumber()){
            root.setLeft(insert(root.getLeft(), newNode));
        } else if (root.getVerseNumber() < newNode.getVerseNumber()){
            root.setRight(insert(root.getRight(), newNode));
        }
        // check balance
        int balanceFactor = getBalanceFactor(root);
        // do rotation if unbalanced
        // Left-Left Case
        if (balanceFactor > 1 && newNode.getVerseNumber() < root.getLeft().getVerseNumber()) {
            return rotateRight(root);
        }

        // Right-Right Case
        if (balanceFactor < -1 && newNode.getVerseNumber() > root.getRight().getVerseNumber()) {
            return rotateLeft(root);
        }

        // Left-Right Case
        if (balanceFactor > 1 && newNode.getVerseNumber() > root.getLeft().getVerseNumber()) {
            root.setLeft(rotateLeft(root.getLeft()));
            return rotateRight(root);
        }

        // Right-Left Case
        if (balanceFactor < -1 && newNode.getVerseNumber() < root.getRight().getVerseNumber()) {
            root.setRight(rotateRight(root.getRight()));
            return rotateLeft(root);
        }
        return root;
    }

    private VerseNode rotateRight(VerseNode y) {
        VerseNode x = y.getLeft();
        VerseNode T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        return x;
    }

    // Helper method to perform a left rotation at the given node
    private VerseNode rotateLeft(VerseNode x) {
        VerseNode y = x.getRight();
        VerseNode T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        return y;
    }

    private int getBalanceFactor(VerseNode node) {
        if (node == null) {
            return 0; // Balance factor of null node is 0
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    public int getHeight(){
        return getHeight(root);
    }

    public int getHeight(VerseNode node){
        if (node == null) {
            return -1;
        } else{
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }
    }

    public boolean isFound(VerseNode lookingFor, VerseNode node){
        return searchNode(lookingFor, node) ==  null;
    }

    public VerseNode searchNode(VerseNode lookingFor, VerseNode node) {
        if (node == null) {
            return null;
        }
        if (lookingFor.getVerseNumber() == node.getVerseNumber()) {
            return node;
        } else if (lookingFor.getVerseNumber() != node.getVerseNumber() && node.getLeft() != null) {
            return searchNode(lookingFor, node.getLeft());
        } else if (lookingFor.getVerseNumber() != node.getVerseNumber() && node.getRight() != null) {
            return searchNode(lookingFor, node.getRight());
        } else {
            return null;
        }
    }

    public void insert(VerseNode newNode) {
        root = insert(root, newNode);
    }
    public Stack<VerseNode> inOrder(){
        Stack<VerseNode> verseStack = new Stack<>();
        inOrderTraverse(root, verseStack);
        return verseStack;
    }
    public void inOrderTraverse(VerseNode root, Stack<VerseNode> stack) {
        if (root != null) {
            inOrderTraverse(root.getLeft(), stack); //left
            stack.add(root); //root
            inOrderTraverse(root.getRight(), stack); //right
        }
    }
}
