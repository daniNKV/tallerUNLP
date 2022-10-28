/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo;

/**
 *
 * @author dani
 */
public class Pareja {
    private Participante miembro1;
    private Participante miembro2;
    private String estilo;

    public Pareja(Participante miembro1, Participante miembro2, String estilo) {
        this.miembro1 = miembro1;
        this.miembro2 = miembro2;
        this.estilo = estilo;
    }
    
    public double calcularDiferenciaEdad() {
        return Math.abs(this.getMiembro1().getEdad() - this.getMiembro2().getEdad());
    }
    

    public Participante getMiembro1() {
        return miembro1;
    }

    public void setMiembro1(Participante miembro1) {
        this.miembro1 = miembro1;
    }

    public Participante getMiembro2() {
        return miembro2;
    }

    public void setMiembro2(Participante miembro2) {
        this.miembro2 = miembro2;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public String toString() {
        return "Pareja{" + "miembro1=" + miembro1.getNombre() + ", miembro2=" + miembro2.getNombre() + '}';
    }
    
    
    
    
}
