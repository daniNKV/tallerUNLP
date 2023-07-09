package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.expresion.operador.Operador;

public class ExpresionUnaria extends Expresion {
   Operador O;
   Expresion E;

   public ExpresionUnaria(Operador O, Expresion E) {
      this.O = O;
      this.E = E;
      this.E.setPrograma(this.getPrograma());
   }

   @Override
   public String getValue(DeclaracionVariable DV) throws Exception {
      this.O.setRobot(this.getRobot());
      this.E.setRobot(this.getRobot());
      return this.O.resultado(this.E.getValue(DV));
   }

   public Operador getO() {
      return this.O;
   }

   public void setO(Operador O) {
      this.O = O;
   }

   public Expresion getE() {
      return this.E;
   }

   public void setE(Expresion E) {
      this.E = E;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         System.out.println("Entre al clone de expresion unaria");
         Expresion E3 = (Expresion)this.getE().clone();
         return new ExpresionUnaria(this.getO(), E3);
      }
   }
}
