/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema4;


public abstract class Figura {
    private String colorRelleno;
    private String colorLinea;
   
    public Figura(String unCR, String unCL){
        setColorRelleno(unCR);
        setColorLinea(unCL);
    }
    
    @Override
    public String toString(){
        String aux = "Area: " + this.calcularArea() + " " +
                     this.getColores() +
                     " Perimetro: " + calcularPerimetro();             
             return aux;
       }

    
    public String getColorRelleno(){
        return colorRelleno;       
    }
    public final void setColorRelleno(String unColor){
        colorRelleno = unColor;       
    }
    public String getColorLinea(){
        return colorLinea;       
    }
    public final void setColorLinea(String unColor){
        colorLinea = unColor;       
    }
    public void despintar(){
        this.setColorRelleno("Blanco");
        this.setColorLinea("Negro");
    }
    
        public String getColores(){
        return "Color Linea: " + this.getColorLinea() + ", Color Relleno: " + this.getColorRelleno();
    }
    
    
    public abstract double calcularArea();
    public abstract double calcularPerimetro();
    
     
}
