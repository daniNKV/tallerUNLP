/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema4;

/**
 *
 * @author dani
 */
public class Ej02Equipo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Jugador jugador = new Jugador(5, 29, "John", 10000.0, 4);
        Entrenador entrenador = new Entrenador(6, "Harry", 5000, 2);
        
        System.out.print(jugador.toString() + " " + jugador.calcularSueldoACobrar());
        System.out.println();
        System.out.print(entrenador.toString() + " " + entrenador
                .calcularSueldoACobrar());
    
    }
    
}
