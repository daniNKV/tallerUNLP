package arbol.llamada;

import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import java.util.ArrayList;

public class Informar extends Llamada {
   int ciclo = 1;
   DeclaracionVariable DV;
   String msg = "";
   String str = "";

   public int getCiclo() {
      return this.ciclo;
   }

   public Informar(DeclaracionVariable DV, String s) {
      this.DV = DV;
      this.str = s;
   }

   @Override
   public DeclaracionVariable getDV() {
      return this.DV;
   }

   @Override
   public void setDV(DeclaracionVariable DV) {
      this.DV = DV;
   }

   @Override
   public void ejecutar(ArrayList<Expresion> E) throws Exception {
      synchronized(this) {
         this.msg = this.getRobot().getNombre() + " informa: ";
         this.msg = this.msg + " " + this.str;

         for(Expresion exp : E) {
            exp.setRobot(this.getRobot());
            this.msg = this.msg + " " + exp.getValue(this.DV) + " ";
         }

         this.getRobot().Informar(this.msg);
         this.msg = "";
      }
   }

   @Override
   public Llamada nuevo() throws Exception {
      return new Informar(this.DV, this.str);
   }
}
