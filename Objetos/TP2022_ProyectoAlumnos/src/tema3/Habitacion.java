/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;
import tema2.Persona;
import PaqueteLectura.GeneradorAleatorio;
/**
 *
 * @author dani
 */
public class Habitacion {
    private double costo;
    private boolean empty = true;
    private Persona cliente;
    
    public Habitacion(){
        GeneradorAleatorio.iniciar();
        this.setCosto(GeneradorAleatorio.generarDouble(6000) + 2000);
    }
    
    public void setCosto(double precio){
        this.costo = precio;
    }
    public void switchOcupacion(){
        empty = !(empty = true);
    }
    public void setCliente(Persona cliente){
        this.cliente = cliente;
    }
    public void aumentarCosto(double monto){
        this.costo += monto;
    }
    public boolean estaVacia(){
        return this.empty;
    }
    public double getCosto(){
        return this.costo;
    }
    public Persona getCliente(){
        return this.cliente;
    }
    
}
