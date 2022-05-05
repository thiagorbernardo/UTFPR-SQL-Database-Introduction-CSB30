package com.mycompany.javasql.Save;

public interface SaveDAO<T> {
    /**
     * Reading save file
     * @return a Save object
     */
    T read();

    /**
     * Saving a object of save
     * @param save object
     */
    void save(T save);
}
