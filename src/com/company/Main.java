package com.company;

import com.db4o.ObjectContainer;

public class Main {

    private static ObjectContainer db = DataConnection.getInstance();

    public static void main(String[] args) {

        /*Persona admin = new Persona("admin","admin",true);
        db.store(admin);*/

        /*Anime a = new Anime("Naruto","Kishimoto","Pierrot","Shoonen",720);
        db.store(a);*/

        mainView pr = new mainView();
        pr.setVisible(true);

    }
}
