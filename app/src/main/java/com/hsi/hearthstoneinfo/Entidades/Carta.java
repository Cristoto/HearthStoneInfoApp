package com.hsi.hearthstoneinfo.Entidades;

/**
 * Created by Carlos - xibhu on 20/02/2018.
 */

public class Carta {

    private String nombre;
    private Integer id_mazo;
    private Integer vida;
    private Integer ataque;

    public Carta(Integer id_mazo, String nombre, Integer vida, Integer ataque) {
        this.id_mazo = id_mazo;
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
    }

    public Carta(){

    }

    public Integer getId_mazo() {
        return id_mazo;
    }

    public void setId_mazo(Integer id_mazo) {
        this.id_mazo = id_mazo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }
}
