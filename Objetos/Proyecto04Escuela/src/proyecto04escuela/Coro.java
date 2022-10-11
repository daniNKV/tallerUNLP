/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto04escuela;

/**
 *
 * @author dani
 */
public abstract class Coro {
    private String nombre;
    private Director director;
    private Corista coristas[][];
    private int filasCompletas = 0;
    private int coristasEnFila = 0;

    public Coro(String nombre, Director director,int cantidadFilas, int coristasPorFila) {
        this.nombre = nombre;
        this.director = director;
        this.coristas = new Corista[cantidadFilas][coristasPorFila];
    }
    
    public void agregar(int fila, int columna, Corista corista) {
        this.coristas[fila][columna] = corista;
    }
    
    public boolean coroLleno() {
        return filasCompletas < coristas.length && coristasEnFila < this.coristas[0].length;
    }
    
    public Corista getCorista(int fila, int posicion){
        return this.coristas[fila][posicion];
    }
    
    public int getFilasPosibles(){
        return this.coristas[0].length;
    }
    public int getCapacidadFilas(){
        return this.coristas.length;
    }
    
    public abstract boolean ordenadoCorrectamente();

    @Override
    public String toString() {
        return "Coro " + "nombre=" + nombre + director.toString() + "\n";
    }
    
    public String coristasToString(){
        String str = "";
        
        for(int i = 0; i < this.getFilasPosibles(); i++){
            str += "Fila " + i + ": " + "\n";
            for(int j = 0; i < this.getCapacidadFilas(); j++){
                str += this.getCorista(i, j).toString() + "\n";
            }
        }
        return str;
    }
    
    
}
