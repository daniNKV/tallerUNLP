package arbol.sentencia;

import arbol.DeclaracionVariable;
import arbol.Programa;
import arbol.expresion.Expresion;
import arbol.llamada.Llamada;
import form.Robot;
import java.util.ArrayList;

public class Invocacion extends Sentencia {
   Llamada L = null;
   ArrayList<Expresion> E = null;

   public Invocacion(Llamada L, ArrayList<Expresion> E, DeclaracionVariable DV) {
      this.L = L;
      this.E = E;
      this.varAST = DV;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         ArrayList<Expresion> es = new ArrayList<>();

         for(Expresion ee : this.E) {
            Expresion e = (Expresion)ee.clone();
            e.setDV(this.varAST);
            es.add(e);
         }

         Llamada ll = null;

         try {
            ll = this.L.nuevo();
         } catch (Exception var7) {
            System.out.println("Algo anda mal en el clone de invocacion");
         }

         return new Invocacion(ll, es, this.getDV());
      }
   }

   @Override
   public void setRobot(Robot r) {
      for(Expresion ee : this.E) {
         ee.setRobot(r);
      }

      this.L.setRobot(r);
   }

   @Override
   public void ejecutar() throws Exception {
      synchronized(this) {
         for(Expresion ee : this.E) {
            ee.setRobot(this.getRobot());
         }

         this.L.setDV(this.getDV());
         this.L.ejecutar(this.E);
      }
   }

   @Override
   public void setPrograma(Programa P) {
      this.L.setPrograma(P);

      for(Expresion exp : this.E) {
         exp.setPrograma(P);
      }
   }
}
