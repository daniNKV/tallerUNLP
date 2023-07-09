package arbol;

import form.Ciudad;
import form.CodePanel;
import form.Robot;

public class Programa extends AST {
   Identificador I;
   DeclaracionRobots DR;
   DeclaracionProcesos DP = null;
   DeclaracionVariable DV;
   DeclaracionAreas DA;
   Cuerpo C;
   Robot R;
   Ciudad city;
   CodePanel codigo;

   public Programa(Identificador I, DeclaracionProcesos DP, DeclaracionVariable DV, DeclaracionRobots DR, Ciudad city, Cuerpo C, DeclaracionAreas DA) {
      this.setI(I);
      this.setC(C);
      if (DP != null) {
         this.setDP(DP);
      }

      if (DV != null) {
         this.setDV(DV);
      }

      this.setDR(DR);
      this.setDA(DA);
      this.city = city;
   }

   public DeclaracionAreas getDA() {
      return this.DA;
   }

   public void setDA(DeclaracionAreas DA) {
      this.DA = DA;
   }

   public Cuerpo getC() {
      return this.C;
   }

   public DeclaracionProcesos getDP() {
      return this.DP;
   }

   public DeclaracionVariable getDV() {
      return this.DV;
   }

   public DeclaracionRobots getDR() {
      return this.DR;
   }

   public void setDR(DeclaracionRobots DR) {
      this.DR = DR;
   }

   public Identificador getI() {
      return this.I;
   }

   public Robot getRobot() {
      return this.R;
   }

   public void setI(Identificador I) {
      this.I = I;
   }

   public void setC(Cuerpo C) {
      this.C = C;
      this.C.setPrograma(this);
   }

   public void setDP(DeclaracionProcesos DP) {
      this.DP = DP;
   }

   public void setDV(DeclaracionVariable DV) {
      this.DV = DV;
   }

   public void setRobot(Robot R) {
      this.R = R;
   }

   public Ciudad getCity() {
      return this.city;
   }

   public void setCity(Ciudad c) {
      this.city = c;
   }

   public CodePanel getCodigo() {
      return this.codigo;
   }

   public void setCodigo(CodePanel codigo) {
      this.codigo = codigo;
   }

   public void ejecutar() throws Exception {
      this.getC().ejecutar();
   }
}
