package com.mycompany.javasql.Save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Export {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final FileManager fileManagerJSON;
    private final FileManager fileManagerCSV;

    public Export(String path) {
        String filePath = "src/main/resources/export/" + path;
        this.fileManagerJSON = new FileManager(filePath + ".json", "");
        this.fileManagerCSV = new FileManager(filePath+ ".csv", "");
    }

    public void saveJSON(ResultMap result) {
        this.fileManagerJSON.saveString(this.gson.toJson(result.getJSON()), false);
    }

    public void saveCSV(ResultMap result) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<ArrayList<Object>> list = result.getCSV();
         for (ArrayList<Object> row : list) {
             for (Object column : row) {
                 stringBuilder.append(column);

                 if(row.indexOf(column) != row.size()-1){
                     stringBuilder.append(",");
                 }
             }
             stringBuilder.append("\n");
         }
        this.fileManagerCSV.saveString(stringBuilder.toString(), false);
    }
}
