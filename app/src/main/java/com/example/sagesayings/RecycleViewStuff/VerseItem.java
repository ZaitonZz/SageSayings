package com.example.sagesayings.RecycleViewStuff;

public class VerseItem {
    private String chapter;
    private String verse;

    public VerseItem(String chapter, String verse) {
        this.chapter = chapter;
        this.verse = verse;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }
}
