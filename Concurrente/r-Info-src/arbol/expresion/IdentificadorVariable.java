package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.Identificador;

public class IdentificadorVariable extends Expresion {
   Identificador I;

   public IdentificadorVariable(Identificador I) {
      this.I = I;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new IdentificadorVariable(this.I);
   }

   @Override
   public String getValue(DeclaracionVariable DV) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.    GetValue IdentificadorVariable");
   }
}
