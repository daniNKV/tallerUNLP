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
public class Triangulo {
    private double a = 10; 
    private double b = 10;
    private double c = 10;
    private String relleno = "Blanco";
    private String linea = "Negro";
    
    
    public Triangulo() {
        
    }
    
    public Triangulo(double ladoA, double ladoB, double ladoC){
        a = ladoA;
        b = ladoB;
        c = ladoC;
    }
    
    public void setLadoA(double size) {
        a = size;
    }
    public double getLadoA() {
        return a;
    }
    public void setLadoB(double size) {
        b = size;
    }
    public double getLadoB() {
        return b;
    }
    public void setLadoC(double size) {
        c = size;
    }
    public double getLadoC() {
        return c;
    }
    
    public void setLinea(String color) {
        linea = color;
    }
    public void setFondo(String color){ 
        relleno = color;
    }
    public double calcularPerimetro() {
        return this.getLadoA() + this.getLadoB() + this.getLadoC();
    }
    public double calcularArea(){
        double s = this.calcularPerimetro() / 2;
        double cuerpo = (s - this.getLadoA()) * (s - this.getLadoB()) * (s - this.getLadoC());
       
        return Math.sqrt(s * cuerpo);
        
    }
}
