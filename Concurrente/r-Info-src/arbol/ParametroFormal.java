package arbol;

public class ParametroFormal extends AST {
   Identificador I;
   Tipo T;
   String TA;
   public String value;

   public Identificador getI() {
      return this.I;
   }

   public void setI(Identificador I) {
      this.I = I;
   }

   public Tipo getT() {
      return this.T;
   }

   public void setT(Tipo T) {
      this.T = T;
   }

   public String getTA() {
      return this.TA;
   }

   public void setTA(String TA) {
      this.TA = TA;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public ParametroFormal(Identificador I, Tipo T, String TA) {
      this.I = I;
      this.T = T;
      this.TA = TA;
   }
}
