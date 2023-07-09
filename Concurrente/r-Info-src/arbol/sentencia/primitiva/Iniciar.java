package arbol.sentencia.primitiva;

import arbol.Cuerpo;
import arbol.DeclaracionProcesos;
import arbol.DeclaracionRobots;
import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.RobotAST;
import arbol.Variable;
import arbol.sentencia.Sentencia;
import form.EjecucionRobot;
import form.Robot;
import java.util.ArrayList;

public class Iniciar extends Primitiva {
   RobotAST r;
   int x;
   int y;
   Identificador Ident;
   DeclaracionRobots robAST;
   DeclaracionVariable varAST;

   public Iniciar(Identificador I, int x, int y, DeclaracionRobots robAST, DeclaracionVariable varAST) throws Exception {
      this.varAST = varAST;
      this.robAST = robAST;
      this.Ident = I;
      this.x = x;
      this.y = y;
      if (varAST.EstaVariable(I.toString())) {
         this.r = varAST.findByName(I.toString()).getR();
         System.out.println("para la variable " + I.toString() + " el robot es : " + this.r.getNombre());
      } else {
         throw new Exception("El robot tiene que estar declarado en las variables");
      }
   }

   @Override
   public void ejecutar() throws Exception {
      Robot rob = null;
      Cuerpo cu = this.r.getCuerpo();
      DeclaracionVariable dv = this.r.getDV();
      DeclaracionProcesos procAST = this.r.getProcAST();
      String nom = this.Ident.toString();
      if (this.programa.getCity().estaRobot(nom)) {
         System.out.println("El robot estaba " + nom + " y su tipo es : " + this.r.getNombre());
         rob = this.programa.getCity().getRobotByNombre(nom);
      } else {
         System.out.println("El robot no estaba : " + nom);
      }

      if (rob.esAreaVacia()) {
         String msj = "El robot: " + rob.getNombre() + " no tiene area designada";
         this.getPrograma().getCity().parseError(msj);
         throw new Exception(msj);
      } else {
         IniciarRobot ini = new IniciarRobot(this.x, this.y);
         ini.setRobot(rob);
         ArrayList<Sentencia> sent = new ArrayList<>();
         sent.add(ini);

         for(Sentencia single : cu.getS()) {
            System.out.println("Sentencia clone es : " + single.toString());
            Sentencia i = (Sentencia)single.clone();
            i.setPrograma(this.getPrograma());
            sent.add(i);
         }

         ArrayList<Variable> dvs = new ArrayList<>();

         for(Variable b : dv.variables) {
            dvs.add((Variable)b.clone());
         }

         DeclaracionVariable fv = new DeclaracionVariable(dvs);
         System.out.println("La cantidad de sentencias de este cuerpo es: " + sent.size());
         cu = new Cuerpo(sent, fv);
         cu.setRobot(rob);
         rob.setVariables(fv);
         rob.setProcAST(procAST);
         rob.setCuerpo(cu);
         rob.getCuerpo().setPrograma(this.getPrograma());
         Runnable exec1 = new EjecucionRobot(rob, false, this.getPrograma().getCodigo());
         Thread thread = new Thread(exec1);
         this.programa.getCity().agregarHilo(thread);
         thread.start();
      }
   }
}
