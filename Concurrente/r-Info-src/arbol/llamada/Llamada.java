package arbol.llamada;

import arbol.AST;
import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import form.Robot;
import java.util.ArrayList;

public abstract class Llamada extends AST {
   DeclaracionVariable DV;
   Robot r;

   public DeclaracionVariable getDV() {
      return this.DV;
   }

   public void setDV(DeclaracionVariable DV) {
      this.DV = DV;
   }

   public Robot getRobot() {
      return this.r;
   }

   public void setRobot(Robot r) {
      this.r = r;
   }

   public void ejecutar(ArrayList<Expresion> E) throws Exception {
   }

   public Llamada nuevo() throws Exception {
      throw new Exception("error en nuevo llamada , similar al clone");
   }
}
