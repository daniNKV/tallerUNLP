package arbol.sentencia;

import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import java.util.ArrayList;

public class Seleccion extends Sentencia {
   Expresion E;
   ArrayList<Sentencia> S1;
   ArrayList<Sentencia> S2;

   public Seleccion(Expresion E, ArrayList<Sentencia> S1, ArrayList<Sentencia> S2, DeclaracionVariable DV) {
      this.E = E;
      this.S1 = S1;
      this.S2 = S2;
      this.setDV(DV);
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      ArrayList<Sentencia> S = new ArrayList<>();

      for(Sentencia sen : this.getS1()) {
         System.out.println("Sentencia  clonada : " + sen.toString());
         Sentencia ss = (Sentencia)sen.clone();
         S.add(ss);
      }

      ArrayList<Sentencia> SS2 = new ArrayList<>();
      if (this.getS2() != null) {
         for(Sentencia sen : this.getS2()) {
            System.out.println("Sentencia  clonada : " + sen.toString());
            Sentencia sss = (Sentencia)sen.clone();
            SS2.add(sss);
         }
      }

      Expresion EE = (Expresion)this.E.clone();
      return new Seleccion(EE, S, SS2, this.getDV());
   }

   public Expresion getE() {
      return this.E;
   }

   public void setE(Expresion E) {
      this.E = E;
   }

   public ArrayList<Sentencia> getS1() {
      return this.S1;
   }

   public void setS1(ArrayList<Sentencia> S1) {
      this.S1 = S1;
   }

   public ArrayList<Sentencia> getS2() {
      return this.S2;
   }

   public void setS2(ArrayList<Sentencia> S2) {
      this.S2 = S2;
   }

   @Override
   public void ejecutar() throws Exception {
      synchronized(this) {
         this.E.setPrograma(this.getPrograma());
         this.E.setRobot(this.getRobot());
         this.E.setDV(this.getDV());
         if (this.E.getValue(this.getDV()).equals("V")) {
            for(Sentencia single : this.S1) {
               single.setPrograma(this.getPrograma());
               single.setRobot(this.getRobot());
               single.setDV(this.getDV());
               single.ejecutar();
            }
         } else if (this.S2 != null) {
            for(Sentencia single : this.S2) {
               single.setPrograma(this.getPrograma());
               single.setRobot(this.getRobot());
               single.setDV(this.getDV());
               single.ejecutar();
            }
         }
      }
   }
}
