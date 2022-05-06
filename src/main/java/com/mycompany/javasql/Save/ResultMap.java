package com.mycompany.javasql.Save;

import java.util.ArrayList;
import java.util.Map;

public class ResultMap {
    private ArrayList<Map<String, Object>> dataJSON;
    private ArrayList<ArrayList<Object>> dataCSV;

    public ResultMap() {
        this.dataJSON = new ArrayList<>();
        this.dataCSV = new ArrayList<>();
    }

    public void addJSONItem(Map<String, Object> item) {
        this.dataJSON.add(item);
    }

    public void addCSVHeader(ArrayList<Object> item) {
        this.dataCSV.add(item);
    }

    public void addCSVItem(ArrayList<Object> item) {
        this.dataCSV.add(item);
    }

    public ArrayList<Map<String, Object>> getJSON() {
        return this.dataJSON;
    }

    public ArrayList<ArrayList<Object>> getCSV() {
        return this.dataCSV;
    }
}