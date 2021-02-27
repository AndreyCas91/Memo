package ru.geekbrains.memo;

import java.io.Serializable;

public class SimpleNote implements Serializable {
    private final String title;
    private final String desk;
    private final String data;

    public SimpleNote(String title, String desk, String data) {
        this.title = title;
        this.desk = desk;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getDesk() {
        return desk;
    }

    public String getData() {
        return data;
    }
}
