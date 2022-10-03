
package tema3;

/**
 *
 * @author dani
 */
import tema4.Figura;
public class Circulo extends Figura {
    private double radio;
    
    public Circulo(double radio, String colorRelleno, String colorLinea){
        super(colorRelleno, colorLinea);
        this.radio = radio;
    
    }
    public Circulo(double radio, String colorLinea) {
        this(radio, "Black", colorLinea);
    }
    public void setRadio(double radio) {
        this.radio = radio;
    }
    public double getRadio(){
        return this.radio;
    }

    public double getDiametro() {
        return this.radio * 2;
    }

    @Override
    public double calcularPerimetro(){
        return this.getDiametro() * Math.PI;
    }
    
    @Override
    public double calcularArea() {
        return Math.PI * Math.sqrt(this.getRadio());
    }
    
    @Override
    public String toString() {
        return super.toString() + " radio: " + this.getRadio();
    }
}

