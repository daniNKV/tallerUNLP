package arbol.sentencia;

import arbol.DeclaracionVariable;
import arbol.Programa;
import arbol.expresion.Expresion;
import form.Robot;
import java.util.ArrayList;

public class IteradorIncondicional extends Sentencia {
   Expresion E;
   ArrayList<Sentencia> S;

   public IteradorIncondicional(Expresion E, ArrayList<Sentencia> S, DeclaracionVariable DV) {
      this.E = E;
      this.S = S;
      this.setDV(DV);
   }

   @Override
   public void setDV(DeclaracionVariable DV) {
      this.varAST = DV;

      for(Sentencia s : this.S) {
         s.setDV(this.varAST);
      }

      this.E.setDV(DV);
   }

   @Override
   public void ejecutar() throws Exception {
      int c = 0;
      this.E.setRobot(this.getRobot());

      for(int var4 = 0; var4 < Integer.parseInt(this.E.getValue(this.getDV())); ++var4) {
         for(Sentencia single : this.S) {
            single.setRobot(this.getRobot());
            single.ejecutar();
         }
      }
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         ArrayList<Sentencia> ss = new ArrayList<>();

         for(Sentencia single : this.S) {
            System.out.println("Intentando clonar en Iterador Incondicional: " + single.toString());
            Sentencia sen = (Sentencia)single.clone();
            ss.add(sen);
         }

         return new IteradorIncondicional(this.E, ss, this.getDV());
      }
   }

   @Override
   public void setRobot(Robot r) {
      super.setRobot(r);
      this.E.setRobot(r);

      for(Sentencia s : this.S) {
         s.setRobot(r);
      }
   }

   @Override
   public void setPrograma(Programa P) {
      this.E.setPrograma(P);

      for(Sentencia sen : this.S) {
         sen.setPrograma(P);
      }
   }
}
