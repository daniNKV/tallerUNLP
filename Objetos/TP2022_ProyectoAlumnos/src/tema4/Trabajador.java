/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4;
import tema2.Persona;
/**
 *
 * @author dani
 */
public class Trabajador extends Persona {
    String ocupacion;

    public Trabajador(String unNombre, int unDNI, int unaEdad, String trabajo) {
        super(unNombre, unDNI, unaEdad);
        this.ocupacion = trabajo;
    }

    public Trabajador() {
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    
    public String toString(){
        return super.toString() + " Ocupacion: Soy " + this.getOcupacion();
    }
    
    
}
