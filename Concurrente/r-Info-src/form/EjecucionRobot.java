package form;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjecucionRobot implements Runnable, PropertyChangeListener {
   Robot prgAST;
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   boolean pasoAPaso = false;
   CodePanel codigo;
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public EjecucionRobot(Robot prg, boolean paso_a_paso, CodePanel code) {
      this.pasoAPaso = paso_a_paso;
      this.prgAST = prg;
      this.codigo = code;
   }

   @Override
   public void run() {
      try {
         System.out.println("ejecutando robot: " + this.prgAST.getNombre());
         this.prgAST.setEstado("ejecutandose");
         this.prgAST.getCuerpo().ejecutar();
         if (this.esperarRefresco.termine_ejecucion()) {
            System.out.println("SOY EL ULTIMO" + this.prgAST.getNombre());
            this.esperarRefresco.setEn_ejecucion(false);
            this.esperarRefresco.setApretoF7(false);
            this.esperarRefresco.setTimerOn(false);
            this.codigo.habilitarTodo();
         } else {
            System.out.println("No soy el ultimo, pero termine :" + this.prgAST.getNombre());
         }

         this.prgAST.setEstado("finalizado");
         System.out.println("nombre de robot : " + this.prgAST.getNombre() + " termino ");
      } catch (Exception var4) {
         System.out.println(var4);

         try {
            this.codigo.doStopStepByStep();
         } catch (Exception var3) {
            System.out.println(var3);
            Logger.getLogger(Ejecucion.class.getName()).log(Level.SEVERE, null, var3);
         }

         this.codigo.habilitarTodo();
      }
   }

   public void dormir() {
      this.esperarRefresco.dormir();
   }

   public void arrancar() {
      this.esperarRefresco.arrancar();
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      System.out.println("HOLA");
   }
}
