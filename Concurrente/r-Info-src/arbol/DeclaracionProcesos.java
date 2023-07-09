package arbol;

import java.util.ArrayList;

public class DeclaracionProcesos extends AST {
   public ArrayList<Proceso> procesos;

   public ArrayList<Proceso> getProcesos() {
      return this.procesos;
   }

   public void setProcesos(ArrayList<Proceso> procesos) {
      this.procesos = procesos;
   }

   public DeclaracionProcesos(ArrayList<Proceso> procesos) {
      this.procesos = procesos;
   }

   public boolean estaProceso(String spelling) {
      for(int i = 0; i < this.procesos.size(); ++i) {
         if (this.procesos.get(i).I.spelling.equals(spelling)) {
            return true;
         }
      }

      return false;
   }

   public Proceso getProceso(String spelling) {
      Proceso proc = null;

      for(int i = 0; i < this.procesos.size(); ++i) {
         if (this.procesos.get(i).I.spelling.equals(spelling)) {
            return this.procesos.get(i);
         }
      }

      return proc;
   }
}
