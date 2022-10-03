/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4;

/**
 *
 * @author dani
 */
public class AlmacenamientoFiguras {
    private int guardadas;
    private int capacidadMaxima=5;
    private Figura [] vector;
    
    public AlmacenamientoFiguras(){
        //completar
        vector = new Figura[capacidadMaxima];
        this.guardadas = 0;
    }
    public void guardar(Figura f){
        //completar
        if (quedaEspacio()){
            this.vector[guardadas] = f;
            this.guardadas++;
        }else {
            System.out.print("No queda espacio");
            System.out.println();
        }
    }
    public boolean quedaEspacio(){
        return capacidadMaxima > this.getGuardadas();
    }
    public void mostrar(){
        //completar
        for (int i = 0; i < this.getGuardadas(); i++){
            System.out.print(vector[i].toString());
            System.out.println();
        }
    }
    public int getGuardadas() {
        return  guardadas;
    }
}
