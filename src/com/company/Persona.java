package com.company;

public class Persona {

    private String nombre;
    private String password;
    private boolean admin;

    public Persona(String nombre, String password, boolean admin) {
        super();
        this.nombre = nombre;
        this.password = password;
        this.admin = admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
