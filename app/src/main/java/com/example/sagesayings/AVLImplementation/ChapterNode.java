/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sagesayings.AVLImplementation;

/**
 *
 * @author Philip Candelario
 */
public class ChapterNode {
    ChapterNode left;
    ChapterNode right;
    int chapterNumber;
    AVLVerseTree avlVerseTree;


    public ChapterNode() {
        left = null;
        right = null;
        chapterNumber = 0;
        avlVerseTree = null;
    }

    public ChapterNode(int chapterNumber, AVLVerseTree avlVerseTree) {
        left = null;
        right = null;
        this.chapterNumber = chapterNumber;
        this.avlVerseTree = avlVerseTree;
    }


    public ChapterNode getLeft() {
        return left;
    }

    public void setLeft(ChapterNode left) {
        this.left = left;
    }

    public ChapterNode getRight() {
        return right;
    }

    public void setRight(ChapterNode right) {
        this.right = right;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" + " data = " + chapterNumber + " }";
    }


}
