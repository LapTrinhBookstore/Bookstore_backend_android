package com.example.appbookstore;

public class SearchObj {
    private int icon;
    private String keyWord;

    public SearchObj(int icon, String keyWord) {
        this.icon = icon;
        this.keyWord = keyWord;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}