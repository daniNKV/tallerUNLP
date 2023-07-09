package arbol;

import javax.swing.JOptionPane;

public abstract class AST implements Cloneable {
   protected Programa programa;

   public Programa getPrograma() {
      return this.programa;
   }

   public void setPrograma(Programa programa) {
      this.programa = programa;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      Object obj = null;

      try {
         System.out.println("Clone no definido");
         obj = super.clone();
      } catch (CloneNotSupportedException var3) {
         System.out.println(" no se puede duplicar");
      }

      return obj;
   }

   public void reportError(String str) {
      JOptionPane.showMessageDialog(null, str, "ERROR", 0);
   }
}
