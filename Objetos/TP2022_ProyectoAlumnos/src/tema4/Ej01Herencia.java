/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package tema4;
import tema3.Circulo;
import tema3.Triangulo;

/**
 *
 * @author dani
 */
public class Ej01Herencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Circulo circulo = new Circulo(10, "Verde", "Rosa");
        Triangulo triangulo = new Triangulo(10, 10, 10, "Azul", "Verde");
        
        System.out.print(circulo.toString());
        System.out.println();
        System.out.print(triangulo.toString());
        
        System.out.println();
        circulo.despintar();
        triangulo.despintar();
   
        System.out.println();
        System.out.print(circulo.toString());
       
        System.out.println();
        System.out.print(triangulo.toString());
        
    }
    
}
