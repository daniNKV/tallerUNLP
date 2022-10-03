/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4;
/**
 *
 * @author dani
 */
public class Jugador extends Empleado{
    private int cantidadPartidos;
    private int cantidadGoles;

    public Jugador(int cantidadPartidos, int cantidadGoles, String nombre, double sueldo, int antiguedad) {
        super(nombre, sueldo, antiguedad);
        this.cantidadPartidos = cantidadPartidos;
        this.cantidadGoles = cantidadGoles;
    }

    public int getCantidadPartidos() {
        return cantidadPartidos;
    }

    public void setCantidadPartidos(int cantidadPartidos) {
        this.cantidadPartidos = cantidadPartidos;
    }

    public int getCantidadGoles() {
        return cantidadGoles;
    }

    public void setCantidadGoles(int cantidadGoles) {
        this.cantidadGoles = cantidadGoles;
    }
    
    @Override
    public double calcularEfectividad(){
        return (double)this.getCantidadGoles() / (double)this.getCantidadPartidos();
    }
    @Override
    public double calcularSueldoACobrar(){
        double base = this.getSueldo();
        return this.calcularEfectividad() < 0.5 ? base + base * 0.1 : base * 2 + base + base * 0.1;
    }    
  
    
}
