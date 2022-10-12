/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto04escuela;

/**
 *
 * @author dani
 */
public class CoroMatriz extends Coro {
    private final int filas;
    private final int capacidadFila;
    private int filasLlenas;
    private int coristasEnFila;
    
    public CoroMatriz(String nombre, Director director, int cantidadFilas, int coristasPorFila) {
        super(nombre, director, cantidadFilas, coristasPorFila);
        this.filas = cantidadFilas;
        this.capacidadFila = coristasPorFila;
        this.filasLlenas = 0;
        this.coristasEnFila = 0;
    }
    
    public void agregarCorista(Corista corista) {
        if (coristasEnFila <= capacidadFila) {
            super.agregar(filasLlenas, coristasEnFila, corista);
            coristasEnFila++;
            
            if(coristasEnFila >= capacidadFila) {
                coristasEnFila = 0;
                
                if(filasLlenas < filas) {
                    filasLlenas++;
                }
            }   
        }
    }
    
    @Override
    public boolean ordenadoCorrectamente(){
        boolean orden = true;
        int i = 0;
        int j = 0;
        int filasPosibles = super.getFilasPosibles();
        int capacidad = super.getCapacidadFilas();
        
        while(orden && i < filasPosibles && j < capacidad - 1){
            if (!(super.getCorista(i,j).getTonoFundamental() == super.getCorista(i,j+1).getTonoFundamental())){
                orden = false;
            }
            j++;

            if (j >= capacidad - 1) {
                i++;
                j = 0;
               
                if (!(super.getCorista(i,j).getTonoFundamental() <= super.getCorista(i-1,j).getTonoFundamental())){
                    orden = false;
                }
            }
        }
        
        return orden;
    }
    
    
    
    
}
