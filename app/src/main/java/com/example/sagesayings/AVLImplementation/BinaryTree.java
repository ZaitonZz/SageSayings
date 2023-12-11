package com.example.sagesayings.AVLImplementation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Philip Candelario
 */
public class BinaryTree {
    
    ChapterNode rootNode;
    
    public BinaryTree() {
        rootNode = null;
    }

    public BinaryTree(ChapterNode rootNode) {
        this.rootNode = rootNode;
    }

    public ChapterNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(ChapterNode rootNode) {
        this.rootNode = rootNode;
    }
    
    public void postOrderTraverse(){
        postOrderTraverse(rootNode);
    }
    
    public void postOrderTraverse(ChapterNode root){
        if (root != null) {
            postOrderTraverse(root.getLeft()); //left
            postOrderTraverse(root.getRight()); //right
            System.out.print(root.getChapterNumber() + " "); //root
        }
    }
    
    public void inOrderTraverse(){
        inOrderTraverse(rootNode);
    }
    
    public void inOrderTraverse(ChapterNode root) {
        if (root != null) {
            inOrderTraverse(root.getLeft()); //left
            System.out.print(root.getChapterNumber() + " "); //root
            inOrderTraverse(root.getRight()); //right
        }
    }
    
    public void preOrderTraverse(){
        preOrderTraverse(rootNode);
    }
    
    public void preOrderTraverse(ChapterNode root){
        if (root != null) {
            System.out.print(root.getChapterNumber() + " "); //root
            preOrderTraverse(root.left); //left
            preOrderTraverse(root.getRight()); //right
        }
    }
    
    public boolean isFound(ChapterNode lookingFor, ChapterNode node){
        return searchNode(lookingFor, node) ==  null;
    }
    
    public ChapterNode searchNode(ChapterNode lookingFor, ChapterNode node){
        if (node ==null) {
            return null;
        } 
        if (lookingFor.getChapterNumber() == node.getChapterNumber()) {
            return node;
        } else if (lookingFor.getChapterNumber() != node.getChapterNumber() && node.getLeft() != null) {
            return searchNode(lookingFor, node.getLeft());
        } else if (lookingFor.getChapterNumber() != node.getChapterNumber() && node.getRight() != null) {
            return searchNode(lookingFor, node.getRight());
        } else {
            return null;
        }
    }
    
    public int getHeight(){
        return getHeight(rootNode);
    }
    
    public int getHeight(ChapterNode node){
        if (node == null) {
            return -1;
        } else{
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }
    }
    
    
    
    
    
    
    
    
}
