/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.proyectounlp01;

/**
 *
 * @author dani
 */
public class Investigador {
    private int cantidadMaximaSubsidios = 5;
    private String nombre;
    private int categoria;
    private String especialidad;
    private Subsidio subsidios[] = new Subsidio[cantidadMaximaSubsidios];
    private int cantidadSubsidios = 0;

    public Investigador(String nombre, int categoria, String especialidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.especialidad = especialidad;
    }
    
    public void agregarSubsidio(Subsidio unSubsidio){
        this.subsidios[this.getCantidadSubsidios()] = unSubsidio;
        cantidadSubsidios++;
    }
    private int getCantidadSubsidios() {
        return this.cantidadSubsidios;
    }
    public String getNombre(){
        return this.nombre;
    }
    public double getMontoSubsidiado(){
        double total = 0;
        for(int i = 0; i < getCantidadSubsidios(); i++){
            if (subsidios[i].getEstado()){
                total += subsidios[i].getMonto();
            }
        }
        return total;
    }
    public void otorgarTodos(){
        for(int i = 0; i < this.getCantidadSubsidios(); i++){
            if(!subsidios[i].getEstado()) {
                subsidios[i].otorgarSubsidio();
            }
            
        }
    }
    public String toString(){
        String nombre = "Investigador - Nombre: " + this.nombre + " ";
        String cat = "Categoria: " + this.categoria + " ";
        String esp = "Especialidad: " + this.especialidad + " ";
        String total = "Total Subsidiado: " + this.getMontoSubsidiado() + " ";
        
        return nombre + cat + esp + total;
        
    }
}
