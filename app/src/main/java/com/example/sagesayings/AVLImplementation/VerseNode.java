package com.example.sagesayings.AVLImplementation;

public class VerseNode {

    private int verseNumber;
    private String formattedText;
    private String metaWords;
    private VerseNode left;
    private VerseNode right;

    public VerseNode() {
    }

    public VerseNode(int verseNumber, String formattedText, String metaWords) {
        this.verseNumber = verseNumber;
        this.formattedText = formattedText;
        this.metaWords = metaWords;
    }

    public int getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(int verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getFormattedText() {
        return formattedText;
    }

    public void setFormattedText(String formattedText) {
        this.formattedText = formattedText;
    }

    public String getMetaWords() {
        return metaWords;
    }

    public void setMetaWords(String metaWords) {
        this.metaWords = metaWords;
    }

    public VerseNode getLeft() {
        return left;
    }

    public void setLeft(VerseNode left) {
        this.left = left;
    }

    public VerseNode getRight() {
        return right;
    }

    public void setRight(VerseNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return verseNumber + ": " + formattedText;
    }
    public VerseNode contains(String query){
        if (formattedText.toLowerCase().contains(query.toLowerCase()) || metaWords.toLowerCase().contains(query.toLowerCase())){
            return this;
        }
        return null;
    }
}
