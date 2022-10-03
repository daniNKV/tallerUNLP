/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author dani
 */
package tema3;

import tema4.Figura;

public class Triangulo extends Figura {
    private double a; 
    private double b;
    private double c;
    
    public Triangulo(double ladoA, double ladoB, double ladoC){
        this(ladoA, ladoB, ladoC, "Black", "White");
    }
    public Triangulo(double ladoA, double ladoB, double ladoC, String linea, String relleno){
        super(relleno, linea);
        this.a = ladoA;
        this.b = ladoB;
        this.c = ladoC;
    }
    
    public void setLadoA(double size) {
        this.a = size;
    }
    public double getLadoA() {
        return a;
    }
    public void setLadoB(double size) {
        this.b = size;
    }
    public double getLadoB() {
        return b;
    }
    public void setLadoC(double size) {
        this.c = size;
    }
    public double getLadoC() {
        return c;
    }
    
    @Override
    public double calcularPerimetro() {
        return this.getLadoA() + this.getLadoB() + this.getLadoC();
    }
    private double calcularSuperficie(){
        return this.calcularPerimetro() / 2;
    }
    @Override
    public double calcularArea(){
        double superficie = this.calcularSuperficie();
        double cuerpo = (superficie - this.getLadoA()) * (superficie - this.getLadoB()) * (superficie - this.getLadoC());
       
        return Math.sqrt(superficie * cuerpo);
    }
    
    @Override
    public String toString(){
        return super.toString() + " lado A: " + getLadoA() + " lado B: " + getLadoB() + " Lado C:  " + getLadoC();
    }
    
}
