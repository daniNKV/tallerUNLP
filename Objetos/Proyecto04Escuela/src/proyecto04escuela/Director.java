/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto04escuela;

/**
 *
 * @author dani
 */
public class Director extends Integrante {
    private int antiguedad;

    public Director(int antiguedad, String nombre, int dni, int edad) {
        super(nombre, dni, edad);
        this.antiguedad = antiguedad;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return super.toString() + " antiguedad=" + antiguedad + " ";
    }
    
    
    
    
    
    
}
