package arbol.sentencia.primitiva;

import form.Area;
import form.Robot;

public class AsignarArea extends Primitiva {
   Robot r;
   Area a;

   public AsignarArea(Robot robo, Area area) {
      this.r = robo;
      this.a = area;
   }

   @Override
   public void ejecutar() {
      this.r.agregarArea(this.a);
   }
}
