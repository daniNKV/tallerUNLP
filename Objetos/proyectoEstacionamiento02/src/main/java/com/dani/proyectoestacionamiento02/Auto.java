/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.proyectoestacionamiento02;

/**
 *
 * @author dani
 */
public class Auto {
    private String nombrePropietario;
    private String patente;

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombre_dueño(String nombre_dueño) {
        this.nombrePropietario = nombre_dueño;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "Auto{" + "nombrePropietario=" + nombrePropietario + ", patente=" + patente + '}';
    }
    
    
    
}
