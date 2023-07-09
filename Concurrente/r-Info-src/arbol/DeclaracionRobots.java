package arbol;

import java.util.ArrayList;

public class DeclaracionRobots {
   public ArrayList<RobotAST> robots;

   public DeclaracionRobots(ArrayList<RobotAST> robots) {
      this.robots = robots;
   }

   public ArrayList<RobotAST> getRobots() {
      return this.robots;
   }

   public void setRobots(ArrayList<RobotAST> robots) {
      this.robots = robots;
   }

   public boolean estaRobot(String spelling) {
      for(int i = 0; i < this.robots.size(); ++i) {
         if (this.robots.get(i).getNombre().equals(spelling)) {
            return true;
         }
      }

      return false;
   }

   public RobotAST getRobot(String spelling) {
      RobotAST proc = null;

      for(int i = 0; i < this.robots.size(); ++i) {
         if (this.robots.get(i).getNombre().equals(spelling)) {
            return this.robots.get(i);
         }
      }

      System.out.println("No devolvi nada en el getRobot del Declaracion Robots");
      return proc;
   }
}
