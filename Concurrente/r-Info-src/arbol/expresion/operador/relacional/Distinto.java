package arbol.expresion.operador.relacional;

import arbol.expresion.operador.Operador;

public class Distinto extends Operador {
   String c;

   @Override
   public String resultado(String Op1, String Op2) {
      if (Op1.equals(Op2)) {
         this.c = "F";
      } else {
         this.c = "V";
      }

      return this.c;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new Distinto();
   }
}
