package arbol.sentencia.primitiva;

import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.expresion.Expresion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Random extends Primitiva {
   Expresion E1;
   Expresion E2;

   public Random(Identificador I, DeclaracionVariable DV, Expresion E1, Expresion E2) throws Exception {
      this.setDV(DV);
      this.setI(I);
      this.setE1(E1);
      this.setE2(E2);
      boolean ok = this.getDV().EstaParametro(I.toString());
      if (this.getDV().findByName(I.toString()).getT().tipo != 19) {
         throw new Exception("Se esperaba una variable de tipo número en la primitiva random");
      }
   }

   @Override
   public void ejecutar() throws Exception {
      int desde = Integer.parseInt(this.getE1().getValue(this.getDV()));
      int hasta = Integer.parseInt(this.getE2().getValue(this.getDV()));
      int aux = (int)(Math.random() * (double)(hasta - desde + 1) + (double)desde);
      String str = String.valueOf(aux);
      this.getDV().findByName(this.I.toString()).setValue(str);
   }

   public Expresion getE1() {
      return this.E1;
   }

   public void setE1(Expresion E1) {
      this.E1 = E1;
   }

   public Expresion getE2() {
      return this.E2;
   }

   public void setE2(Expresion E2) {
      this.E2 = E2;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      Random obj = null;

      try {
         obj = new Random(this.getI(), this.getDV(), this.getE1(), this.getE2());
      } catch (Exception var3) {
         Logger.getLogger(Random.class.getName()).log(Level.SEVERE, null, var3);
      }

      return obj;
   }
}
