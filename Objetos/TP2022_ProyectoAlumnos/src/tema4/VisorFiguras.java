/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4;

/**
 *
 * @author dani
 */
public class VisorFiguras {
    private int mostradas;
    
    public VisorFiguras(){
        mostradas=0;
    }
    public void mostrar(Figura f){
        System.out.println(f.toString());
        mostradas++;
    }
    public int getMostradas() {
        return mostradas;
    }   
}
