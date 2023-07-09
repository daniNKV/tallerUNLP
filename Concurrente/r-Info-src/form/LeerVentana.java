package form;

import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LeerVentana extends JFrame {
   public DeclaracionVariable DV;
   public Expresion E;

   public LeerVentana(DeclaracionVariable DV, Expresion E) throws Exception {
      this.E = E;
      this.DV = DV;
      String nombre = E.getI().toString();
      if (E.getT().getTipo() == 20) {
         Object[] possibilities = new Object[]{"V", "F"};
         String s = (String)JOptionPane.showInputDialog(this, "Seleccione valor booleano para la variable: " + nombre, "leer", 3, null, possibilities, null);
         if (s == null) {
            JOptionPane.showMessageDialog(null, "Se debe elegir un valor booleano para la variable : " + nombre, "ERROR", 0);
            throw new Exception("Se debe elegir un valor booleano para la variable : " + nombre);
         }

         this.DV.findByName(nombre).setValue(s);
      } else {
         if (E.getT().getTipo() != 19) {
            JOptionPane.showMessageDialog(null, "No existe variable : " + nombre, "ERROR", 0);
            throw new Exception("No existe variable : " + nombre);
         }

         String s = (String)JOptionPane.showInputDialog(this, "Ingrese un valor numerico para la variable: " + nombre, "leer", 3, null, null, null);
         if (s == null) {
            JOptionPane.showMessageDialog(null, "Se debe elegir un valor numerico para la variable : " + nombre, "ERROR", 0);
            throw new Exception("Se debe elegir un valor numerico para la variable : " + nombre);
         }

         try {
            int x = Integer.parseInt(s);
            System.out.println("la variable desde teclado fue :  " + x);
            System.out.println("el nombre de la varibles es :  " + nombre);
            System.out.println(s);
            this.DV.findByName(nombre).setValue(s);
         } catch (Exception var6) {
            JOptionPane.showMessageDialog(null, "Se debe elegir un valor numerico para la variable : " + nombre, "ERROR", 0);
            throw new Exception("Se debe elegir un valor numerico para la variable : " + nombre);
         }
      }
   }
}
