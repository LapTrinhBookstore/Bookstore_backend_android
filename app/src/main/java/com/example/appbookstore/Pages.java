package com.example.appbookstore;

import java.io.Serializable;

public class Pages implements Serializable {
    String content;





    public Pages(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}