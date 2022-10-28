/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo1;

/**
 *
 * @author dani
 */
public class Hospital {
    private String nombre;
    private String direccion;
    private int camasOcupadas = 0;
    Cama[] camas = new Cama[100];

    public Hospital(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    public void ingresarPaciente(Paciente paciente, int numeroCama){
        this.camas[numeroCama]= new Cama(numeroCama, paciente);
        this.camasOcupadas++;
    }                
    
    public void liberarCama(int dniPaciente) {
        int numeroCama = this.buscarCamaPaciente(dniPaciente);
        this.camas[numeroCama] = new Cama(numeroCama);
        this.camasOcupadas--;
    }
    
    private int buscarCamaPaciente(int dni) {
        int i = 0;
        boolean f = false;
        while (i < this.camasOcupadas && !f) {
            if (dni == camas[i].getPaciente().getDni()){
                f = true;
            }else {
                i++;
            }
        }
        
        return camas[i].getNumero();
    }
    
    public void incrementarDiasOcupacion() {
        for (int i = 0; i < this.camas.length; i++) {
            if (camas[i] != null && camas[i].isOcupada()) {
                camas[i].incrementarDias();
            }
        }
    }
    
    public int contarPacientesInternados() {
        int total = 0;
        for(Cama cama : this.camas) {
            if(cama != null && cama.isOcupada()) {
                total++;
            }
        }
        return total;
    }
}
