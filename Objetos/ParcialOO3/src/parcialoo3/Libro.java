/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo3;

/**
 *
 * @author dani
 */
public class Libro {
    private String titulo;
    private String nombreAutor;
    private double peso;

    public Libro(String titulo, String nombreAutor, double peso) {
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", nombreAutor=" + nombreAutor + ", peso=" + peso + '}';
    }

    
    
}