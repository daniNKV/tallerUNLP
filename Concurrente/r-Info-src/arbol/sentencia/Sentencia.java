package arbol.sentencia;

import arbol.AST;
import arbol.DeclaracionVariable;
import arbol.Programa;
import form.Robot;

public abstract class Sentencia extends AST {
   DeclaracionVariable varAST;
   Robot r;

   public Robot getRobot() {
      return this.r;
   }

   public void setRobot(Robot r) {
      this.r = r;
   }

   public void ejecutar() throws Exception {
   }

   public void setDV(DeclaracionVariable varAST) {
      this.varAST = varAST;
   }

   public DeclaracionVariable getDV() {
      return this.varAST;
   }

   @Override
   public void setPrograma(Programa P) {
      this.programa = P;
   }

   @Override
   public Programa getPrograma() {
      return this.programa;
   }
}
