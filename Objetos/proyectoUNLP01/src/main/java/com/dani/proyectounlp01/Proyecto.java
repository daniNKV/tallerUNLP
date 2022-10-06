/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.proyectounlp01;

/**
 *
 * @author dani
 */
public class Proyecto {
    
    private String nombre;
    private int codigo;
    private String nombreDirector;
    
    private static int maximoInvestigadores = 50;
    private int cantidadInvestigadores = 0;
    private Investigador[] investigadores = new Investigador[maximoInvestigadores];

    public Proyecto(String nombre, int codigo, String nombreDirector) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreDirector = nombreDirector;
    }
    public void agregarInvestigador(Investigador unInvestigador){
        this.investigadores[getCantidadInvestigadores()] = unInvestigador;
        this.cantidadInvestigadores++;
    }
    
    private int getCantidadInvestigadores(){
        return this.cantidadInvestigadores;
    }
    
    public double dineroTotalOtorgado(){
        double total = 0;
        for(int i = 0; i < this.getCantidadInvestigadores(); i++){
            total += this.investigadores[i].getMontoSubsidiado();
        }
        return total;
    }
    
    public void otorgarTodos(String nombre_completo){
        boolean found = false;
        int i = 0;
        while(!found && i < this.cantidadInvestigadores){
            if(nombre_completo.equals(investigadores[i].getNombre())) {
                investigadores[i].otorgarTodos();
                found = !found;
            }
            i++;
        }
    }
    public String toString(){
        String br = System.getProperty("line.separator");
        String nombreStr = "Nombre Proyecto: " + this.nombre + " ";
        String directorStr = "Nombre Director: " + this.nombreDirector + " ";
        String codigoStr = "Codigo: " + this.codigo + " ";
        String subsidioStr = "Monto total Subsidiado: " + this.dineroTotalOtorgado() + " ";
        
        return nombreStr + directorStr + codigoStr + subsidioStr + br + investigadoresToString();
    }
 
    private String investigadoresToString(){
        String br = System.getProperty("line.separator");
        String str = "";
        
        for(int i = 0; i < this.getCantidadInvestigadores(); i++){
            str += investigadores[i].toString() + br;
        }
        return str;
    }
    

}
