package arbol.sentencia;

import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.Programa;
import arbol.Variable;
import arbol.expresion.Expresion;

public class Asignacion extends Sentencia {
   Identificador I;
   Expresion E;
   DeclaracionVariable DV;

   public Asignacion(Identificador I, Expresion E, DeclaracionVariable DV) {
      this.I = I;
      this.E = E;
      this.DV = DV;
   }

   @Override
   public void ejecutar() throws Exception {
      synchronized(this) {
         this.E.setRobot(this.getRobot());
         if (this.DV.EstaParametro(this.I.toString())) {
            Variable tmp = this.DV.findByName(this.I.toString());
            String eValue = this.E.getValue(this.DV);
            if (tmp.getT().toString().equals("boolean") && !eValue.equals("V") && !eValue.equals("F")) {
               this.programa.getCity().parseError("Se esperaba un valor booleano en la variable/parametro : " + tmp.getI().toString());
               throw new Exception("Se esperaba un valor booleano en la variable/parametro : " + tmp.getI().toString());
            } else {
               if (tmp.getT().toString().equals("numero")) {
                  try {
                     System.out.println("e value vale:  " + eValue);
                     int ex = Integer.parseInt(eValue);
                  } catch (Exception var6) {
                     this.programa.getCity().parseError("Se esperaba un valor numerico en la variable/parametro : " + tmp.getI().toString());
                     throw new Exception("Se esperaba un valor numerico en la variable/parametro : " + tmp.getI().toString());
                  }
               }

               this.DV.findByName(this.I.toString()).setValue(eValue);
            }
         } else {
            this.programa.getCity().parseError("Variable: " + this.I.toString() + "no encontrada");
            throw new Exception("Variable: " + this.I.toString() + "no encontrada");
         }
      }
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      Object obj = null;
      Identificador II = new Identificador(this.I.toString());
      System.out.println("Intentando clonar expresion en asignacion");
      Expresion EE = (Expresion)this.E.clone();
      return new Asignacion(II, EE, this.DV);
   }

   @Override
   public void setDV(DeclaracionVariable varAST) {
      this.DV = varAST;
   }

   @Override
   public void setPrograma(Programa P) {
      this.programa = P;
      this.E.setPrograma(P);
   }
}
