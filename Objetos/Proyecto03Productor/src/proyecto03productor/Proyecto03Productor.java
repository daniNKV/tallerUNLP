/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package proyecto03productor;

/**
 *
 * @author dani
 */
public class Proyecto03Productor {

    public static void main(String[] args) {
        Gira gira = new Gira("Stars", 3, "Claptone", 3);
        EventoOcasional evento = new EventoOcasional("TV", "Telefe", "22/07", "Bandalos", 2);
        for (int i=0; i<3; i++){
            Fecha fecha = new Fecha("Manhattan", "23");
            gira.agregarFecha(fecha);
        }
        gira.agregarTema("Slingshot");
        gira.agregarTema("Tottem");
        gira.agregarTema("Sling");

        evento.agregarTema("Hola1");
        evento.agregarTema("Hola2");
       
        System.out.print(gira.calcularCosto());
        System.out.println();
        System.out.print(evento.calcularCosto());
        System.out.println();
        
        gira.actuar();
        System.out.println();
        evento.actuar();
        
        
    }
}
