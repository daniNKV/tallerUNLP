package arbol.llamada;

import arbol.DeclaracionVariable;
import arbol.expresion.Expresion;
import java.util.ArrayList;

public class Pos extends Llamada {
   int ciclo = 1;

   public int getCiclo() {
      return this.ciclo;
   }

   @Override
   public Llamada nuevo() throws Exception {
      return new Pos(this.getDV());
   }

   @Override
   public void ejecutar(ArrayList<Expresion> E) throws Exception {
      synchronized(this) {
         for(Expresion exp : E) {
            exp.setRobot(this.getRobot());
         }

         int x = Integer.parseInt(E.get(0).getValue(this.DV));
         int y = Integer.parseInt(E.get(1).getValue(this.DV));
         if (x > 0 && x < 101 && y > 0 && y < 101) {
            if (!this.getPrograma().getCity().HayObstaculo(x, y)) {
               this.getRobot().Pos(x, y);
            } else {
               super.getPrograma().getCity().parseError("No se puede ejecutar la instrucción Pos ya que en esa posición hay un Obstaculo");
               throw new Exception("No se puede ejecutar la instrucción Pos ya que en esa posición hay un Obstaculo");
            }
         } else {
            super.getPrograma().getCity().parseError("Se esperaba valor numerico mayor a 0 (cero) en la instrucción Pos y menor a 100(cien)");
            throw new Exception("Se esperaba valor numerico mayor a 0 (cero) en la instrucción Pos y menor a 100(cien)");
         }
      }
   }

   public Pos(DeclaracionVariable DV) {
      this.DV = DV;
   }
}
