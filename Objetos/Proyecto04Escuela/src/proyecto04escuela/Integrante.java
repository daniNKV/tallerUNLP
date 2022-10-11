/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto04escuela;

/**
 *
 * @author dani
 */
public class Integrante {
    private String nombre;
    private int dni;
    private int edad;

    public Integrante(String nombre, int dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Integrante: " + "nombre=" + nombre + ", dni=" + dni + ", edad=" + edad + " ";
    }
    
    
}
