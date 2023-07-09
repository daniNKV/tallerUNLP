package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.Tipo;

public class HayFlorEnLaEsquina extends Expresion {
   public HayFlorEnLaEsquina() {
      this.setT(new Tipo((byte)20));
   }

   @Override
   public String getValue(DeclaracionVariable DV) {
      int Av = this.getRobot().PosAv();
      int Ca = this.getRobot().PosCa();
      boolean hayFlor = this.getRobot().getCity().HayFlorEnLaEsquina(Av, Ca);
      return hayFlor ? "V" : "F";
   }

   @Override
   public boolean ejecutar() {
      synchronized(this) {
         String s1 = this.getValue(this.DV);
         return s1.equals("V");
      }
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new HayFlorEnLaEsquina();
   }
}
