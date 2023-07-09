package arbol.expresion.operador;

import arbol.expresion.Expresion;

public abstract class Operador extends Expresion {
   public String resultado(String Op1) throws Exception {
      return "undefined";
   }

   public String resultado(String Op1, String Op2) {
      return "undeefined";
   }
}
