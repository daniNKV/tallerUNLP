/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto03productor;

/**
 *
 * @author dani
 */
public class Gira extends Recital{
    String nombre;
    int cantidadFechas;
    double costoFecha = 3000;
    int fechasAlocadas = 0;
    Fecha fechas[];
    int fechaActual;

    public Gira(String nombre, int cantidadFechas, String nombreBanda, int cantidadCanciones) {
        super(nombreBanda, cantidadCanciones);
        this.nombre = nombre;
        this.cantidadFechas = cantidadFechas;
        this.fechas = new Fecha[cantidadFechas];
        this.fechaActual = 0;
    }
    
    public void agregarFecha(Fecha fecha) {
        this.fechas[fechasAlocadas] = fecha;
        this.fechasAlocadas++;
    }
    
    public void actuar(){
        System.out.print("Buenas Noches..." + this.fechas[fechaActual].getCiudad());
        System.out.println();
        super.actuar();
        this.fechaActual++;
    }
    
    @Override
    public double calcularCosto(){
        return this.fechasAlocadas * this.costoFecha;
    }
    
    
    
}
