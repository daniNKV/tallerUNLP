package arbol.sentencia.primitiva;

import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BloquearEsquina extends Primitiva {
   int ciclo = 1;
   private int Av;
   private int Ca;
   DeclaracionVariable DV;
   Expresion E3;
   Expresion E4;

   public int getCiclo() {
      return this.ciclo;
   }

   public BloquearEsquina(Expresion E1, Expresion E2, DeclaracionVariable DV) throws Exception {
      this.DV = DV;
      this.E3 = E1;
      this.E4 = E2;
   }

   public int getAv() {
      return this.Av;
   }

   private void setAv(int Av) {
      this.Av = Av;
   }

   public int getCa() {
      return this.Ca;
   }

   private void setCa(int Ca) {
      this.Ca = Ca;
   }

   @Override
   public void ejecutar() throws Exception {
      synchronized(this) {
         this.DV = this.getRobot().getVariables();
         this.E3.setRobot(this.getRobot());
         this.E4.setRobot(this.getRobot());
         this.setAv(Integer.parseInt(this.E3.getValue(this.getDV())));
         this.setCa(Integer.parseInt(this.E4.getValue(this.getDV())));
         if (this.getAv() >= 1 && this.getAv() <= 100 && this.getCa() >= 1 && this.getCa() <= 100) {
            this.getRobot().bloquearEsquina(this.getAv(), this.getCa());
         } else {
            this.getPrograma().getCity().parseError("Se esperaban valores entre 1 y 100 en la primitiva BlorquearEsquina");
            throw new Exception("Se esperaban valores entre 1(uno) y 100(cien) en la primitiva BlorquearEsquina");
         }
      }
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      synchronized(this) {
         BloquearEsquina b = null;

         try {
            Expresion ee1 = (Expresion)this.E3.clone();
            Expresion ee2 = (Expresion)this.E4.clone();
            b = new BloquearEsquina(ee1, ee2, this.DV);
         } catch (Exception var6) {
            Logger.getLogger(BloquearEsquina.class.getName()).log(Level.SEVERE, null, var6);
         }

         return b;
      }
   }
}
