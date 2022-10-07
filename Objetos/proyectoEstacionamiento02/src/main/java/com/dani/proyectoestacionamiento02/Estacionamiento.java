package com.dani.proyectoestacionamiento02;

import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dani
 */
public class Estacionamiento {
    private String nombre;
    private String direccion;
    private String hora_cierre;
    private String hora_apertura;

    private int cantidadPisos;
    private int cantidadPlazasPorPiso;
    private Auto pisos[][];

    public Estacionamiento(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.hora_cierre = "21:00";
        this.hora_apertura = "08:00";
        
    }

    public Estacionamiento(String nombre, String direccion, String hora_cierre, String hora_apertura, int cantidadPisos, int cantidadPlazasPorPiso) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.hora_cierre = hora_cierre;
        this.hora_apertura = hora_apertura;
        this.cantidadPisos = cantidadPisos;
        this.cantidadPlazasPorPiso = cantidadPlazasPorPiso;
        this.pisos = new Auto[cantidadPisos][cantidadPlazasPorPiso];
    }
    
    public void registrarAuto(Auto auto, int piso, int plaza){
        this.pisos[piso][plaza] = auto;
    }
    
    public String buscarPatente(String patente){
        int i = 0;
        int j = 0;
        String str = "El auto con patente: " + patente;
        boolean fueEncontrado = false;
  
        while (!fueEncontrado && i < this.cantidadPisos){
            while(!fueEncontrado && j < this.cantidadPlazasPorPiso){
                if(patente.equals(pisos[i][j].getPatente())){
                    fueEncontrado = true;
                    i--;
                }else {
                    j++;           
                }
            }
            i++;
        }
        if (fueEncontrado == true) {
            return str + " se encuentra en el piso: " + i + " y plaza: " + j;           
        }else
            return str + " no existe";
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(String hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    public String getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(String hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    public void setCantidadPisos(int cantidadPisos) {
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPlazasPorPiso() {
        return cantidadPlazasPorPiso;
    }

    public void setCantidadPlazasPorPiso(int cantidadPlazasPorPiso) {
        this.cantidadPlazasPorPiso = cantidadPlazasPorPiso;
    }
    
    
 
    @Override
    public String toString() {
        return "Estacionamiento{" + "nombre=" + nombre + ", direccion=" + direccion + ", hora_cierre=" + hora_cierre + ", hora_apertura=" + hora_apertura + ", cantidadPisos=" + cantidadPisos + ", cantidadPlazasPorPiso=" + cantidadPlazasPorPiso ;
    }

    
    
    
}
