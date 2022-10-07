package com.dani.proyecto03productor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dani
 */
public class EventoOcasional extends Recital {
    private static String opcionesMotivos[] = {"Beneficiencia", "TV", "Privado"};
    private static double opcionesCotizacion[] = {0, 5000, 150000};
    private int motivo;
    private String nombreEmpleador;
    private String fecha;

    public EventoOcasional(String motivo, String nombreEmpleador, String fecha, String nombre, int cantidadCanciones) {
        super(nombre, cantidadCanciones);
        this.setMotivo(motivo);
        this.nombreEmpleador = nombreEmpleador;
        this.fecha = fecha;
    }
    
    private void setMotivo(String motivo){
        int i = 0;
        boolean encontrado = false;
        while (i < opcionesMotivos.length && !encontrado) {
            if (motivo.equals(opcionesMotivos[i])){
                encontrado = true;
                this.motivo = i;
            }
            i++;
        }
    }
    
    public void actuar(){
              
        super.toString();
        System.out.println("Recuerden colaborar con... " + this.getNombreEmpleador()); 
    }
    
    public String getNombreEmpleador(){
        return this.nombreEmpleador;
    }
    
    public double calcularCosto(){
        return this.opcionesCotizacion[this.motivo];
    }
}
