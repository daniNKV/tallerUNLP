package arbol.expresion.operador.booleano;

import arbol.expresion.operador.Operador;

public class Or extends Operador {
   boolean c;

   @Override
   public String resultado(String Op1, String Op2) {
      boolean a = "V".equals(Op1);
      boolean b = "V".equals(Op2);
      this.c = a | b;
      return this.c ? "V" : "F";
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new Or();
   }
}
