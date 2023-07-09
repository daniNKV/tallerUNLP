package arbol.expresion.operador.aritmetico;

import arbol.expresion.operador.Operador;

public class Suma extends Operador {
   int c;

   @Override
   public String resultado(String Op1, String Op2) {
      int a = Integer.parseInt(Op1);
      int b = Integer.parseInt(Op2);
      this.c = a + b;
      return String.valueOf(this.c);
   }

   @Override
   public String resultado(String Op1) {
      int a = Integer.parseInt(Op1);
      this.c = a + 0;
      return String.valueOf(this.c);
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new Suma();
   }
}
