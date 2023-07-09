package arbol;

import arbol.sentencia.Sentencia;
import form.Robot;
import java.util.ArrayList;

public class Cuerpo extends AST {
   private ArrayList<Sentencia> S;
   DeclaracionVariable varAST;
   Robot rob;

   public Cuerpo(ArrayList<Sentencia> S, DeclaracionVariable varAST) {
      this.setS(S);
      this.varAST = varAST;
   }

   public void ejecutar() throws Exception {
      for(Sentencia single : this.S) {
         single.setDV(this.varAST);
         single.ejecutar();
      }
   }

   public Robot getRobot() {
      return this.rob;
   }

   public void setRobot(Robot rob) {
      this.rob = rob;

      for(Sentencia single : this.S) {
         single.setRobot(this.rob);
      }
   }

   public ArrayList<Sentencia> getS() {
      return this.S;
   }

   public void setS(ArrayList<Sentencia> S) {
      this.S = S;
   }

   @Override
   public void setPrograma(Programa P) {
      this.programa = P;

      for(Sentencia single : this.S) {
         single.setPrograma(P);
      }
   }

   public void setDV(DeclaracionVariable DV) {
      for(Sentencia single : this.S) {
         single.setDV(DV);
      }
   }
}
