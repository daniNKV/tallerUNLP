/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto04escuela;

/**
 *
 * @author dani
 */
public class CoroSemicircular extends Coro {
    private int filas = 1;
    private int cantidadCoristas;
   
    public CoroSemicircular(String nombre, Director director, int coristasPorFila) {
        super(nombre, director, 1, coristasPorFila);
        this.cantidadCoristas = coristasPorFila;
        
    }
    
    public void agregarCorista(Corista corista){
        super.agregar(filas, cantidadCoristas, corista);
        this.cantidadCoristas++;
    }
    
    @Override
    public boolean ordenadoCorrectamente(){
        int i = 0;
        boolean orden = true;
        while (i < this.getCapacidadFilas() - 1 && orden ){
            if (!(this.getCorista(0, i).getTonoFundamental() >= this.getCorista(0,i+1).getTonoFundamental())){
                orden = false;
            }else
                i++;
        }
        return orden;
    }
    
    
    
}
