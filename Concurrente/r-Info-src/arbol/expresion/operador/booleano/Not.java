package arbol.expresion.operador.booleano;

import arbol.expresion.operador.Operador;
import javax.swing.JOptionPane;

public class Not extends Operador {
   boolean a;
   boolean c;

   @Override
   public String resultado(String Op1) throws Exception {
      if ("V".equals(Op1)) {
         this.c = true;
      } else {
         if (!"F".equals(Op1)) {
            this.parseError("Se esperaba un valor booleano para la operación negación");
            throw new Exception("Se esperaba un valor booleano para la operación negación");
         }

         this.c = false;
      }

      this.a = !this.c;
      return this.a ? "V" : "F";
   }

   public void parseError(String msg) {
      JOptionPane.showMessageDialog(null, "Run Time Error: " + msg, "error", 0);
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return new Not();
   }
}
