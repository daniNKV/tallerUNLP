/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo1;

/**
 *
 * @author dani
 */
public class Cama {
    private int numero;
    private boolean ocupada = false;
    private Paciente paciente = null;
    private int diasOcupada = 0;

    public Cama(int numero, Paciente p) {
        this.numero = numero;
        this.paciente = p;
        this.ocupada = true;
        this.diasOcupada++;
    }
    
    public Cama(int numero) {
        this.numero = numero;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }
    
    public void incrementarDias(){
        this.diasOcupada++;
    }
    
    
    
    
    
}
