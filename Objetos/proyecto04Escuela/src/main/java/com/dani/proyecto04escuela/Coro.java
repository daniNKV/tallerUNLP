/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.proyecto04escuela;

/**
 *
 * @author dani
 */
public abstract class Coro {
    private String nombre;
    private String director;
    private Integrante coristas[][];

    public Coro(String nombre, String director,int cantidadFilas, int coristasPorFila) {
        this.nombre = nombre;
        this.director = director;
        this.coristas = new Integrante[cantidadFilas][coristasPorFila];
    }
    
    
}
