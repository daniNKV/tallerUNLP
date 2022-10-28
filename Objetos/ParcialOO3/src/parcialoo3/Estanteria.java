/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo3;

/**
 *
 * @author dani
 */
public class Estanteria {
    Estante[] estantes;
    
    public Estanteria(int cantidadEstantes, int capacidad){
        this.estantes = new Estante[cantidadEstantes];
        
        for(int i=0; i<cantidadEstantes; i++){
            this.estantes[i] = new Estante(capacidad);
        }
    }
    
    public void almacenarLibro(Libro libro, int numeroEstante){
        this.estantes[numeroEstante].agregarLibro(libro);
    }
    
    public Libro sacarLibro(int numeroEstante, int posicionEnEstante){
        return this.estantes[numeroEstante].sacarLibro(posicionEnEstante);
    }
    
    public double calcularEstanteMasPesado(){
        int estanteMasPesado = -1;
        double pesoMaximo = 0;
        
        for(int i = 0; i < this.estantes.length; i++){
            double pesoEstanteActual = estantes[i].calcularPesoEstante();
            if(pesoEstanteActual > pesoMaximo){
                pesoMaximo = pesoEstanteActual;
                estanteMasPesado = i;
            }
        }
        
        return estanteMasPesado;
    }
}
