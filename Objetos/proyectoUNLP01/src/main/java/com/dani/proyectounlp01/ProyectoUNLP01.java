/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.dani.proyectounlp01;

/**
 *
 * @author dani
 */
public class ProyectoUNLP01 {

    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto("Genoma Humano", 762 ,"Anibal Gutierrez");
        Investigador investigador1 = new Investigador("Juan Fernandez", 2, "Biotecnologo");
        Investigador investigador2 = new Investigador("Gabriel Manzano", 1, "Quimica");
        Investigador investigador3 = new Investigador("Manolo Torres", 3, "Fisica");
        
        proyecto.agregarInvestigador(investigador1);
        proyecto.agregarInvestigador(investigador2);
        proyecto.agregarInvestigador(investigador3);
        
        investigador1.agregarSubsidio(new Subsidio(20000, "Equipo"));
        investigador1.agregarSubsidio(new Subsidio(30000, "Refacciones"));
        
        investigador2.agregarSubsidio(new Subsidio(10000, "Equipo"));
        investigador2.agregarSubsidio(new Subsidio(10000, "Catering"));
        
        investigador3.agregarSubsidio(new Subsidio(20000, "Equipo"));
        investigador3.agregarSubsidio(new Subsidio(30000, "Refacciones"));
        
        proyecto.otorgarTodos("Juan Fernandez");

        System.out.print(proyecto.toString());
        
        
        
        
    }
    
}