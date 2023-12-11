/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sagesayings.AVLImplementation;

/**
 *
 * @author Philip Candelario
 */
public class AVLChapterTree extends BinarySearchTree{

    public AVLChapterTree() {
    }

    public AVLChapterTree(ChapterNode rootNode) {
        super(rootNode);
    }
   
    
    @Override
    public ChapterNode insert(ChapterNode root, ChapterNode newNode){
        root = super.insert(root, newNode);
        // check balance
        int balanceFactor = getBalanceFactor(root);
        // do rotation if unbalanced
        // Left-Left Case
        if (balanceFactor > 1 && newNode.getChapterNumber() < root.getLeft().getChapterNumber()) {
            return rotateRight(root);
        }

        // Right-Right Case
        if (balanceFactor < -1 && newNode.getChapterNumber() > root.getRight().getChapterNumber()) {
            return rotateLeft(root);
        }

        // Left-Right Case
        if (balanceFactor > 1 && newNode.getChapterNumber() > root.getLeft().getChapterNumber()) {
            root.setLeft(rotateLeft(root.getLeft()));
            return rotateRight(root);
        }

        // Right-Left Case
        if (balanceFactor < -1 && newNode.getChapterNumber() < root.getRight().getChapterNumber()) {
            root.setRight(rotateRight(root.getRight()));
            return rotateLeft(root);
        }
        return root;
    }

    private ChapterNode rotateRight(ChapterNode y) {
        ChapterNode x = y.getLeft();
        ChapterNode T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        return x;
    }

    // Helper method to perform a left rotation at the given node
    private ChapterNode rotateLeft(ChapterNode x) {
        ChapterNode y = x.getRight();
        ChapterNode T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        return y;
    }
    
    private int getBalanceFactor(ChapterNode node) {
        if (node == null) {
            return 0; // Balance factor of null node is 0
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    
}
