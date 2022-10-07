/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.proyecto03productor;

/**
 *
 * @author dani
 */
public class Fecha {
    private String ciudad;
    private String dia;

    public Fecha(String ciudad, String dia) {
        this.ciudad = ciudad;
        this.dia = dia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    
}
