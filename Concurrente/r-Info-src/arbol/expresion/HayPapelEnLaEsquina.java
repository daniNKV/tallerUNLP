package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.Tipo;

public class HayPapelEnLaEsquina extends Expresion {
   public HayPapelEnLaEsquina() {
      this.setT(new Tipo((byte)20));
   }

   @Override
   public String getValue(DeclaracionVariable DV) {
      synchronized(this) {
         int Av = this.getRobot().PosAv();
         int Ca = this.getRobot().PosCa();
         return this.getRobot().getCity().HayPapelEnLaEsquina(Av, Ca) ? "V" : "F";
      }
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new HayPapelEnLaEsquina();
   }
}
