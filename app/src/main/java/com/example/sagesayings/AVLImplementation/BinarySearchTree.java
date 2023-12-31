/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sagesayings.AVLImplementation;

/**
 *
 * @author Philip Candelario
 */
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(ChapterNode rootNode) {
        super(rootNode);
    }
    
    public ChapterNode insert(ChapterNode root, ChapterNode newNode){
        if (root == null) {
            root = newNode;
            return root;
        } else if (root.getChapterNumber() > newNode.getChapterNumber()){
            root.setLeft(insert(root.getLeft(), newNode));
        } else if (root.getChapterNumber() < newNode.getChapterNumber()){
            root.setRight(insert(root.getRight(), newNode));
        } 
        return root;
    }
    
    public ChapterNode findMin(){
        return findMin(rootNode);
    }
    
    public ChapterNode findMin(ChapterNode root){
        if(root==null) return null;
        else if(root.getLeft()==null) return root;
        return findMin(root.getLeft());
    
    }
    
    public ChapterNode findMax(){
        return findMax(rootNode);
    }
    
    public ChapterNode findMax(ChapterNode root){
        if(root==null) return null;
        else if(root.getRight()==null) return root;
        return findMax(root.getRight());
    
    }
    

    public boolean isFound(int chapterNumber, ChapterNode root){
        return ((searchNode(chapterNumber ,root) != null ));
    }

    public ChapterNode searchNode(int chapterNumber, ChapterNode root){
       
        if (root==null) return null;
   
        if(chapterNumber < root.getChapterNumber()) return searchNode(chapterNumber, root.getLeft());
        else if (chapterNumber > root.getChapterNumber()) return searchNode(chapterNumber, root.getRight());
        else return root;
        
    }
    public ChapterNode delete(ChapterNode t, int key) {
        if (t == null) {            
            return t;            
        }

        if (key < t.getChapterNumber()) {
            t.setLeft(delete(t.getLeft(), key));            
        } else if (key > t.getChapterNumber()) {
            t.setRight(delete(t.getRight(), key));            
        } else {            
            if (t.getLeft() == null) {
                return t.getRight();
            } else if (t.getRight() == null) {
                return t.getLeft();
            }
            t.setChapterNumber(findMin(t.getRight()).getChapterNumber());
            t.setRight(delete(t.getRight(), t.getChapterNumber()));
        }
        return t;
    }

    public void deleteNode(ChapterNode node) {
        setRootNode(delete(rootNode, node.getChapterNumber()));
    }
}
