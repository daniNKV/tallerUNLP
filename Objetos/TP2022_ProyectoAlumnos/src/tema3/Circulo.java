
package tema3;

/**
 *
 * @author dani
 */
public class Circulo {
    private double radio;
    private String relleno;
    private String linea;
    
    public Circulo() {
        
    }
    
    public Circulo(double radio, String colorRelleno, String colorLinea){
        this.radio = radio;
        this.relleno = colorRelleno;
        this.linea = colorLinea;
    }
    public Circulo(double radio, String colorLinea) {
        this(radio, "Black", colorLinea);
    }
    public void setRadio(double radio) {
        this.radio = radio;
    }
    public void setColorLinea(String color){
        this.linea = color;
    }
    public void setColorRelleno(String color){
        this.relleno = color;
    }
    public double getRadio(){
        return this.radio;
    }
    public String getColores(){
        return "Color Linea: " + this.linea + ", Color Relleno: " + this.relleno;
    }
    public double getDiametro() {
        return this.radio * 2;
    }

    public double calcularPerimetro(){
        return this.getDiametro() * Math.PI;
    }
    
    public double calcularArea() {
        return Math.PI * Math.sqrt(this.getRadio());
    }
}
