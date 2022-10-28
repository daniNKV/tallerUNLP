/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo;

/**
 *
 * @author dani
 */
public class Concurso {
    private Pareja[] parejas;
    private int cantidadAnotadas = 0;

    public Concurso(int cantidadParejas) {
        this.parejas = new Pareja[cantidadParejas];
    }
    
    public void agregarPareja(Pareja pareja){
        this.parejas[cantidadAnotadas] = pareja;
        this.cantidadAnotadas++;
    }
    
    public Pareja getMayorDiferenciaEdad(){
        Pareja parejaMayorDiferencia = null;
        double mayorDiferencia = -1;
       
        for (int i = 0; i < cantidadAnotadas; i++){
            double diferenciaActual = parejas[i].calcularDiferenciaEdad();
            if (diferenciaActual > mayorDiferencia) {
                parejaMayorDiferencia = parejas[i];
                mayorDiferencia = diferenciaActual;
            }
        }
        
        return parejaMayorDiferencia;
    }
    
    public int getCantidadAnotados() {
        return this.cantidadAnotadas;
    }
    
    
    
}
