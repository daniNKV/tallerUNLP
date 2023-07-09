package arbol.expresion;

import arbol.DeclaracionVariable;
import arbol.Programa;
import arbol.expresion.operador.Operador;

public class ExpresionBinaria extends Expresion {
   Expresion E1;
   Expresion E2;
   Operador O;

   public ExpresionBinaria(Operador O, Expresion E1, Expresion E2) {
      this.E1 = E1;
      this.E2 = E2;
      this.O = O;
   }

   @Override
   public String getValue(DeclaracionVariable DV) throws Exception {
      synchronized(this) {
         this.E1.setRobot(this.getRobot());
         this.E2.setRobot(this.getRobot());
         String E1Value = this.E1.getValue(DV);
         String E2Value = this.E2.getValue(DV);
         return this.O.resultado(E1Value, E2Value);
      }
   }

   @Override
   public void setPrograma(Programa P) {
      this.programa = P;
      this.E1.setPrograma(P);
      this.E2.setPrograma(P);
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         System.out.println("Entre al clone de expresion binaria");
         Expresion E3 = (Expresion)this.E1.clone();
         Expresion E4 = (Expresion)this.E2.clone();
         return new ExpresionBinaria(this.O, E3, E4);
      }
   }
}
