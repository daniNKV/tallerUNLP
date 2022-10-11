/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto03productor;

/**
 *
 * @author dani
 */
public abstract class Recital {
    private String nombreBanda;
    private int cantidadCanciones;
    private int cancionesGuardadas = 0;
    private String listaCanciones[];
    

    public Recital(String nombre, int cantidadCanciones) {
        this.nombreBanda = nombre;
        this.cantidadCanciones = cantidadCanciones;
        this.listaCanciones = new String[cantidadCanciones];
        
    }
    
    public void agregarTema(String nombre){
        this.listaCanciones[cancionesGuardadas] = nombre;
        this.cancionesGuardadas++;
    }
    
    public void actuar(){
        for(int i = 0; i < this.cancionesGuardadas; i++) {
            System.out.print("Y ahora tocaremos... ");
            System.out.println(listaCanciones[i]);
              
        }
    }
    
    public abstract double calcularCosto();
    
    
    
    
    
}
