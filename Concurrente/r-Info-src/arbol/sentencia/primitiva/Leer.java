package arbol.sentencia.primitiva;

import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import form.LeerVentana;

public class Leer extends Primitiva {
   private Expresion E;
   private DeclaracionVariable DV;

   public Leer(DeclaracionVariable DV, Expresion E) throws Exception {
      this.setE(E);
      this.setDV(DV);
      DV.EstaParametro(E.getI().toString());
   }

   @Override
   public void ejecutar() throws Exception {
      new LeerVentana(this.getDV(), this.getE());
   }

   public Expresion getE() {
      return this.E;
   }

   public void setE(Expresion E) {
      this.E = E;
   }
}
