package com.mycompany.javasql.Save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ExportJSON {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String filePath = "src/main/resources/export/";

    private final FileManager fileManager;

    public ExportJSON(String path) {
        this.filePath += path;
        this.fileManager = new FileManager(this.filePath, "");
    }

    public Object read() {
        this.fileManager.createFile();

        Object save = null;
        try {
            FileReader fileReader = new FileReader(this.filePath, StandardCharsets.UTF_8);

            save = this.gson.fromJson(fileReader, new TypeToken<Object>() {
            }.getType());

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(save == null){
            return new Object();
        }
        return save;
    }

    public void save(ResultMap result) {
        this.fileManager.saveString(this.gson.toJson(result.getData()), false);
    }
}
