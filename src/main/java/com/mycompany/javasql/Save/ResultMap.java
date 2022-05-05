package com.mycompany.javasql.Save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultMap {
    private ArrayList<Map<String, Object>> data;

    public ResultMap() {
        this.data = new ArrayList<>();
    }

    public void addItem(Map<String, Object> item) {
        this.data.add(item);
    }

    public ArrayList<Map<String, Object>> getData() {
        return this.data;
    }
}