package arbol.sentencia.primitiva;

import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.expresion.Expresion;

public class EnviarMensaje extends Primitiva {
   Identificador NombreRobot;
   Expresion E;

   public EnviarMensaje(Expresion E, DeclaracionVariable DV, Identificador NombreRobot) {
      this.setDV(DV);
      this.NombreRobot = NombreRobot;
      this.E = E;
   }

   @Override
   public Object clone() {
      return new EnviarMensaje(this.E, this.getDV(), this.NombreRobot);
   }

   @Override
   public void ejecutar() throws Exception {
      synchronized(this) {
         String nom = this.getRobot().getNombre();
         this.E.setDV(this.getDV());
         String x = this.E.getValue(this.getDV());
         this.getPrograma().getCity().getRobotByNombre(this.NombreRobot.toString()).almacenarMensaje(nom, x);
      }
   }
}
