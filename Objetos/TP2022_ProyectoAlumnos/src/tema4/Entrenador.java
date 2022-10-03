/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4;
/**
 *
 * @author dani
 */
public class Entrenador extends Empleado {
    private int cantidadTitulos;
  
    public Entrenador(int cantidadTitulos, String nombre, double sueldo, int antiguedad) {
        super(nombre, sueldo, antiguedad);
        this.cantidadTitulos = cantidadTitulos;
    }

    public int getCantidadTitulos() {
        return cantidadTitulos;
    }

    public void setCantidadTitulos(int cantidadTitulos) {
        this.cantidadTitulos = cantidadTitulos;
    }
    
    @Override
    public double calcularEfectividad(){
        return (double)this.getCantidadTitulos() / (double)this.getAntiguedad();
    }
    @Override
    public double calcularSueldoACobrar(){
        double base = this.getSueldo();
        return base + base * 0.1 + this.calcularPlusSalarial() ;
    }
    
    private double calcularPlusSalarial() {
        int campeonatos = this.getCantidadTitulos();
        
        if (campeonatos > 1 && campeonatos < 4) {
            return 5000;
        }else if (campeonatos > 5 && campeonatos < 10) {
            return 30000;
        }else if (campeonatos > 10){
            return 50000;
        }
        
        return 0;
            
    }
    
    
}
