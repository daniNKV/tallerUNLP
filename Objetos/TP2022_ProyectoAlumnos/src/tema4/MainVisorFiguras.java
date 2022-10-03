/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema4;

/**
 *
 * @author dani
 */
public class MainVisorFiguras {
    public static void main(String[] args) {
        VisorFiguras visor = new VisorFiguras();
        Cuadrado c1 = new Cuadrado(10,"Violeta","Rosa");
        Rectangulo r= new Rectangulo(20,10,"Azul","Celeste");
        Cuadrado c2= new Cuadrado(30,"Rojo","Naranja");
        
        visor.mostrar(c1);
        visor.mostrar(r);
        visor.mostrar(c2);
        System.out.println(visor.getMostradas());
    }
}