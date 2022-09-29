/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

import PaqueteLectura.GeneradorAleatorio;
import tema2.Persona;

/**
 *
 * @author dani
 */
public class Hotel {

    private int cantidadHabitaciones;
    private Habitacion habitaciones[];

    private int getCantidadHabitaciones() {
        return this.cantidadHabitaciones;
    }

    public Hotel(int n) {
        habitaciones = new Habitacion[n];
    }

    public void ingresarCliente(int nro, Persona cliente) {
        Habitacion habitacion = this.getHabitacion(nro);
        if (habitacion.estaVacia()) {
            habitacion.setCliente(cliente);
            habitacion.switchOcupacion();
        } else {
            System.out.print("La habitación está ocupada");
        }
    }

    public void aumentarMontoFijo(double monto) {
        for (int i = 0; i < this.getCantidadHabitaciones(); i++) {
            this.getHabitacion(i).aumentarCosto(monto);
        }
    }

    private Habitacion getHabitacion(int numeroHabitacion) {
        return habitaciones[numeroHabitacion];
    }

    public String toString() {
        String fullString = "";

        for (int i = 0; i < this.getCantidadHabitaciones(); i++) {
            Habitacion habitacion = this.getHabitacion(i);
            String ocupacion = habitacion.estaVacia() ? "Vacia" : "Ocupada";
            fullString += "{Habitacion: " + i + " costo: " + habitacion.getCosto() + " ocupacion: " + ocupacion;
        }
        
        return fullString;
    }

}
