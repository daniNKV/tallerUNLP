package arbol.expresion;

import arbol.AST;
import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.Tipo;
import form.Robot;

public abstract class Expresion extends AST {
   public DeclaracionVariable DV;
   public Tipo T;
   public Identificador I;
   public Robot r;

   public DeclaracionVariable getDV() {
      return this.DV;
   }

   public void setDV(DeclaracionVariable DV) {
      this.DV = DV;
   }

   public Identificador getI() {
      return this.I;
   }

   public void setI(Identificador I) {
      this.I = I;
   }

   public boolean ejecutar() {
      return true;
   }

   public String getValue(DeclaracionVariable DV) throws Exception {
      return "undefinedd";
   }

   public Tipo getT() {
      return this.T;
   }

   public void setT(Tipo T) {
      this.T = T;
   }

   public Robot getRobot() {
      return this.r;
   }

   public void setRobot(Robot r) {
      this.r = r;
   }

   @Override
   public abstract Object clone() throws CloneNotSupportedException;
}
