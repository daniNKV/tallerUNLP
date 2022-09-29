/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/*
A- Definir una clase para representar triángulos. Un triángulo se caracteriza por el
tamaño de sus 3 lados (double), el color de relleno (String) y el color de línea (String).
Provea un constructor que reciba todos los datos necesarios para iniciar el objeto.
Provea métodos para:
- Devolver/modificar el valor de cada uno de sus atributos (métodos get y set)
- Calcular el perímetro y devolverlo (método calcularPerimetro)
- Calcular el área y devolverla (método calcularArea)
B- Realizar un programa que instancie un triángulo, le cargue información leída desde
teclado e informe en consola el perímetro y el área.
NOTA: Calcular el área con la fórmula Área = √s(s − a)(s − b)(s − c) , donde a, b y c son
los lados y s = a+b+c
2 . La función raíz cuadrada es Math.sqrt(#)*/

/*
package tema3;

/**
 *
 * @author dani
 */

import PaqueteLectura.Lector;

public class Ej01Triangulos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        System.out.print("Ingrese los 3 lados del triangulo: ");
        System.out.println();
        Triangulo nuevoTriangulo = new Triangulo(Lector.leerDouble(), Lector.leerDouble(), Lector.leerDouble());
    
        System.out.print("Area: " + nuevoTriangulo.calcularArea());
        System.out.println();
        System.out.print("Perimetro: " + nuevoTriangulo.calcularPerimetro());
    }
}
