/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package proyecto02estacionamiento;
import PaqueteLectura.Lector;
/**
 *
 * @author dani
 */
public class ProyectoEstacionamiento02 {

    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento("Henry", "Alberga al 2000", "02:00", "04:00", 3, 3);
        
        for (int i=0; i<3; i++) {
            
            for(int j=0; j<3; j++){
                Auto auto = new Auto(Lector.leerString(), Lector.leerString());
                estacionamiento.registrarAuto(auto, i, j);
            }
        }
        
        System.out.print(estacionamiento.toString());
        System.out.println();
        
        System.out.print(estacionamiento.contarAutosEnPlaza(1));
        System.out.println();
        
        estacionamiento.buscarPatente(Lector.leerString());
        System.out.println();
        
        
        
        
    }
}
