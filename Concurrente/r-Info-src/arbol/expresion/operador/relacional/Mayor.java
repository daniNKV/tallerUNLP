package arbol.expresion.operador.relacional;

import arbol.expresion.operador.Operador;

public class Mayor extends Operador {
   String c;

   @Override
   public String resultado(String Op1, String Op2) {
      int Op1x = Integer.parseInt(Op1);
      int Op2x = Integer.parseInt(Op2);
      if (Op1x > Op2x) {
         this.c = "V";
      } else {
         this.c = "F";
      }

      return this.c;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new Mayor();
   }
}
