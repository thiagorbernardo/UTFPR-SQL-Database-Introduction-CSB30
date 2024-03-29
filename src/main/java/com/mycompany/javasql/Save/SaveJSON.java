package com.mycompany.javasql.Save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SaveJSON {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String filePath = "src/main/resources/save/save.json";

    private final FileManager fileManager;

    public SaveJSON(String path) {
        this.filePath = path;
        this.fileManager = new FileManager(path, "");
    }

    /**
     * Reading save file
     *
     * @return a Save object
     */
    public Save read() {
        this.fileManager.createFile();

        Save save = null;
        try {
            FileReader fileReader = new FileReader(this.filePath, StandardCharsets.UTF_8);

            save = this.gson.fromJson(fileReader, new TypeToken<Save>() {
            }.getType());

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(save == null || save.isSaveEmpty()){
            return new Save();
        }
        return save;
    }

    /**
     * Saving object of save
     *
     * @param save object
     */
    public void save(Save save) {
        save.setUpdatedAt();
        this.fileManager.saveString(this.gson.toJson(save), false);
    }
}
