package form;

import arbol.Programa;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejecucion implements Runnable {
   Programa prgAST = null;
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   boolean pasoAPaso = false;
   CodePanel codigo;

   public Ejecucion(Programa prg, boolean paso_a_paso, CodePanel code) {
      this.pasoAPaso = paso_a_paso;
      this.prgAST = prg;
      this.esperarRefresco.setApretoF7(!paso_a_paso);
      this.esperarRefresco.setPasoAPaso(paso_a_paso);
      this.codigo = code;
   }

   @Override
   public void run() {
      try {
         this.esperarRefresco.setEn_ejecucion(true);
         this.prgAST.ejecutar();
      } catch (Exception var4) {
         System.out.println(var4);
         this.prgAST.getCity().parseError(var4.toString());

         try {
            this.codigo.doStopStepByStep();
         } catch (Exception var3) {
            Logger.getLogger(Ejecucion.class.getName()).log(Level.SEVERE, null, var3);
         }

         this.esperarRefresco.setEn_ejecucion(false);
         this.codigo.habilitarTodo();
      }
   }

   public void dormir() {
      this.esperarRefresco.dormir();
   }

   public void arrancar() {
      this.esperarRefresco.arrancar();
   }
}
