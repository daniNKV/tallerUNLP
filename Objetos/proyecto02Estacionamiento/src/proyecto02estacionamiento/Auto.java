/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto02estacionamiento;

/**
 *
 * @author dani
 */
public class Auto {
    private String nombrePropietario;
    private String patente;

    public Auto(String nombrePropietario, String patente) {
        this.nombrePropietario = nombrePropietario;
        this.patente = patente;
    }

    
    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombre_dueño(String nombre_dueño) {
        this.nombrePropietario = nombre_dueño;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "Auto{" + "nombrePropietario=" + nombrePropietario + ", patente=" + patente + '}';
    }
    
    
    
}
