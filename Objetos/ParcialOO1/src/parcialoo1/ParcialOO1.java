/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcialoo1;
/*
Un hospital posee un nombre, dirección y cuenta con 100 camas. Cada cama conoce su
número, si está ocupada o no y en caso de estar ocupada tiene la información del paciente que
la ocupa (DNI, nombre y obra social) y la cantidad de días que lleva ocupada por su último
paciente.
1) Modele el problema generando las clases que considere necesarias, cada una con los
constructores, estado, getters y setter que considere necesarios. Tenga en cuenta que el
hospital debe ser iniciado con todas sus camas desocupadas.
2) Incorpore a las clases implementadas los métodos necesarios para incorporar la siguiente
funcionalidad:
a) Ingresar un paciente (se recibe el paciente y el número de cama donde se lo va
a internar).
b) Dar de alta un paciente liberando la cama que ocupa (se recibe el DNI del
paciente que seguro existe).
c)Incrementar en uno la cantidad de días de ocupación de todas las camas que
estén ocupadas.
d) Devohver la cantidad de pacientes internados
3) Implemente una función main que instancie un hospital, simule el ingreso de tres
pacientes, invoque al método que incrementa la cantidad de días de ocupación, imprimir
la cantidad de pacientes internados y finalmente le de alta un paciente cuyo DNI se
ingresa por teclado.
*/
/**
 *
 * @author dani
 */
public class ParcialOO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Hospital hospital = new Hospital("Larrain V. Gutierrez", "Alcorta al 2000");
    
    hospital.ingresarPaciente(new Paciente(20020, "Juan Rodriguez", "OSPE"), 0);
    hospital.ingresarPaciente(new Paciente(25202, "Rodrigo Perez", "OSDE"), 10);
    hospital.ingresarPaciente(new Paciente(32050, "Mariano Alvear", "OTCA"), 56);
    
    hospital.incrementarDiasOcupacion();
    System.out.print(hospital.contarPacientesInternados());
    
    hospital.liberarCama(20020);
    
    System.out.print(hospital.contarPacientesInternados());
    
    }
}
