package arbol.sentencia.primitiva;

import arbol.DeclaracionVariable;
import arbol.Identificador;

public class RecibirMensaje extends Primitiva {
   Identificador NombreRobot;
   Identificador nombreVariable;

   public RecibirMensaje(Identificador E, DeclaracionVariable DV, Identificador I) {
      this.setDV(DV);
      this.NombreRobot = I;
      this.nombreVariable = E;
   }

   @Override
   public Object clone() {
      return new RecibirMensaje(this.nombreVariable, this.getDV(), this.NombreRobot);
   }

   @Override
   public void ejecutar() throws Exception {
      synchronized(this) {
         if (this.getRobot().getVariables().EstaVariable(this.nombreVariable.toString())) {
            int id;
            if (!this.NombreRobot.toString().equals("*")) {
               id = this.getRobot().getCity().getRobotByNombre(this.NombreRobot.toString()).id;
            } else {
               id = -1;
            }

            this.getRobot().recibirMensaje(this.nombreVariable, id, this.NombreRobot);
         } else {
            this.getPrograma().getCity().parseError("La variable " + this.nombreVariable.toString() + " no esta declarada");
            throw new Exception("La variable " + this.nombreVariable.toString() + " no esta declarada");
         }
      }
   }
}
