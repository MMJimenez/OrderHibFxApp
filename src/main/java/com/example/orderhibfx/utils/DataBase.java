package com.example.orderhibfx.utils;

public abstract class DataBase {
    public enum SelectedDB {
        OBJECTDB, HIBERNATE
    }

    private static SelectedDB selectedDB = SelectedDB.HIBERNATE;

    public static SelectedDB getSelectedDB() {
        return selectedDB;
    }

    public static void setSelectedDB(SelectedDB selectedDB) {
        DataBase.selectedDB = selectedDB;
    }
}