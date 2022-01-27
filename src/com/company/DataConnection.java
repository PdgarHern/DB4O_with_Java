package com.company;

import com.db4o.Db4oEmbedded;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ObjectContainer;

public class DataConnection {

    private static DataConnection INSTANCE = null;

    private final String PATH = "exercise.db4o";
    private static ObjectContainer db;

    private DataConnection() {}

    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataConnection();
            INSTANCE.performConnection();

        }
    }

    public void performConnection() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        db = Db4oEmbedded.openFile(config, PATH);

    }

    public static ObjectContainer getInstance() {
        if (INSTANCE == null) createInstance();
        return db;

    }

    public static void closeConnection() {
        try {
            db.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
