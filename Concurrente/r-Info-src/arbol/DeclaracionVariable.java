package arbol;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DeclaracionVariable extends AST {
   public ArrayList<Variable> variables;

   public DeclaracionVariable(ArrayList<Variable> var) {
      this.variables = var;
   }

   public boolean EstaParametro(String act) throws Exception {
      int i;
      for(i = 0; i < this.variables.size(); ++i) {
         if (this.variables.get(i).getI().equals(act)) {
            return true;
         }
      }

      if (i == this.variables.size()) {
         this.reportError("Variable  '" + act + "'  no declarada.");
         throw new Exception("Variable '" + act + "' no declarada.");
      } else {
         return false;
      }
   }

   public boolean EstaVariable(String act) throws Exception {
      for(int i = 0; i < this.variables.size(); ++i) {
         if (this.variables.get(i).getI().equals(act)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void reportError(String str) {
      JOptionPane.showMessageDialog(null, str, "ERROR", 0);
   }

   public Variable findByName(String act) throws Exception {
      for(int i = 0; i < this.variables.size(); ++i) {
         if (this.variables.get(i).getI().toString().equals(act)) {
            return this.variables.get(i);
         }
      }

      this.reportError("Variable  '" + act + "'  no declarada.");
      throw new Exception("Variable '" + act + "' no declarada.");
   }
}
