package arbol.sentencia;

import arbol.DeclaracionVariable;
import arbol.Programa;
import arbol.expresion.Expresion;
import form.Robot;
import java.util.ArrayList;

public class IteradorCondicional extends Sentencia {
   Expresion E;
   ArrayList<Sentencia> S;

   public IteradorCondicional(Expresion E, ArrayList<Sentencia> S, DeclaracionVariable DV) {
      this.E = E;
      this.S = S;
      this.setDV(DV);
   }

   @Override
   public void ejecutar() throws Exception {
      this.E.setPrograma(this.getPrograma());
      this.E.setRobot(this.getRobot());

      while(this.E.getValue(this.getDV()).equals("V")) {
         for(Sentencia single : this.S) {
            single.setRobot(this.getRobot());
            single.ejecutar();
         }
      }
   }

   @Override
   public void setPrograma(Programa P) {
      this.programa = P;
      this.E.setPrograma(P);

      for(Sentencia sen : this.S) {
         sen.setPrograma(P);
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
   public void setDV(DeclaracionVariable DV) {
      this.varAST = DV;

      for(Sentencia s : this.S) {
         s.setDV(this.varAST);
      }

      this.E.setDV(DV);
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         ArrayList<Sentencia> ss = new ArrayList<>();

         for(Sentencia single : this.S) {
            System.out.println("Intentando clonar en Iterador Condicional: " + single.toString());
            Sentencia sen = (Sentencia)single.clone();
            ss.add(sen);
         }

         System.out.println("Intentando clonar la expresion en iterador condicional");
         Expresion e = (Expresion)this.E.clone();
         return new IteradorCondicional(e, ss, this.getDV());
      }
   }

   public Expresion getE() {
      return this.E;
   }

   public void setE(Expresion E) {
      this.E = E;
   }

   public ArrayList<Sentencia> getS() {
      return this.S;
   }

   public void setS(ArrayList<Sentencia> S) {
      this.S = S;
   }
}
