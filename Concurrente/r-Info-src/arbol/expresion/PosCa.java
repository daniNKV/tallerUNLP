package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.Tipo;

public class PosCa extends Expresion {
   public PosCa() {
      this.setT(new Tipo((byte)19));
   }

   @Override
   public String getValue(DeclaracionVariable DV) {
      return String.valueOf(this.getRobot().PosCa());
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         PosCa obj = new PosCa();
         obj.setT(new Tipo((byte)19));
         return obj;
      }
   }
}
