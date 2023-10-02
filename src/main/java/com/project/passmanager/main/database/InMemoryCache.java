package com.project.passmanager.main.database;

import java.util.ArrayList;

public class InMemoryCache {
    private final ArrayList<Integer> database = new ArrayList<>();

    public void saveIntValue(int value) {
        database.add(value);
    }

    public int getFirstIntValue() {
        return database.get(0);
    }
}
