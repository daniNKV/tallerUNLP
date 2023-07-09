package arbol;

import arbol.expresion.Expresion;
import form.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Variable extends Expresion {
   private String value;
   private boolean editable;
   Robot rob;
   RobotAST r;
   private String nombreTipoRobot;
   DeclaracionRobots dr;

   public Variable(Identificador I, Tipo T, DeclaracionVariable var, DeclaracionRobots robAST, String nombreTipoRobot) throws Exception {
      synchronized(this) {
         this.dr = robAST;
         this.DV = var;
         this.setI(I);
         this.value = "Undefined";
         if (T == null) {
            if (var.EstaVariable(I.toString())) {
               Variable tmp = var.findByName(I.toString());
               this.setT(tmp.getT());
               if (this.getT().tipo == 19) {
                  this.value = "00";
               }

               if (this.getT().tipo == 20) {
                  this.value = "F";
               }
            }
         } else {
            this.setT(T);
            if (this.getT().tipo == 19) {
               this.value = "00";
            }

            if (this.getT().tipo == 20) {
               this.value = "F";
            }

            if (this.getT().tipo == 66) {
               this.r = robAST.getRobot(nombreTipoRobot);
            }
         }
      }
   }

   public RobotAST getR() {
      return this.r;
   }

   @Override
   public String getValue(DeclaracionVariable var) throws Exception {
      String res = "undefineD";
      if (var.EstaParametro(this.I.toString())) {
         Variable tmp = var.findByName(this.I.toString());
         return tmp.getValor();
      } else {
         this.reportError("Variable " + this.getI().toString() + " no declarada");
         throw new Exception("Variable " + this.getI().toString() + " no declarada");
      }
   }

   public String getValor() {
      return this.value;
   }

   public void setValue(String str) {
      this.value = str;
   }

   public boolean esEditable() {
      return this.editable;
   }

   public void setEditable(boolean bool) {
      this.editable = bool;
   }

   @Override
   public void reportError(String str) {
      JOptionPane.showMessageDialog(null, str, "ERROR", 0);
   }

   public boolean isCompatible(Variable tmp) {
      switch(tmp.getT().getTipo()) {
         case 19:
         case 23:
            if (this.getT().getTipo() != 23 && this.getT().getTipo() != 19) {
               return false;
            }

            return true;
         case 20:
         case 32:
         case 33:
            if (this.getT().getTipo() != 20 && this.getT().getTipo() != 32 && this.getT().getTipo() != 33) {
               return false;
            }

            return true;
         case 21:
         case 22:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         default:
            return false;
      }
   }

   public void setTipoRobot(String nombreTipoRobot) {
      this.nombreTipoRobot = nombreTipoRobot;
   }

   public String getTipoRobot() {
      return this.nombreTipoRobot;
   }

   @Override
   public Object clone() {
      synchronized(this) {
         Variable obj = null;

         try {
            obj = new Variable(this.I, this.T, this.DV, this.dr, null);
            obj.setValue(this.getValor());
         } catch (Exception var6) {
            System.out.println("error en el clone de Variable!");
            Logger.getLogger(Variable.class.getName()).log(Level.SEVERE, null, var6);
         }

         return obj;
      }
   }
}
