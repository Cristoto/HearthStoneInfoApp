package com.hsi.hearthstoneinfo.Entidades;

import java.io.Serializable;

/**
 * Created by Carlos - xibhu on 20/02/2018.
 * Clase para mapear el objeto MAZO.
 */

public class Mazo implements Serializable{

    private Integer id;
    private String nombre;

    public Mazo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Mazo(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
