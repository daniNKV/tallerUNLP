package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.Tipo;

public class ValorNumerico extends Expresion {
   String spelling;

   public ValorNumerico(String spelling) {
      this.spelling = spelling;
      this.setT(new Tipo((byte)19));
   }

   @Override
   public String getValue(DeclaracionVariable DV) {
      return this.spelling;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new ValorNumerico(this.spelling);
   }
}
