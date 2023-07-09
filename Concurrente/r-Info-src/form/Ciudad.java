package form;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Ciudad {
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   boolean ok;
   private int numAv;
   private int numCa;
   public int cantidad_robots = 1;
   public Bolsa[][] ciudad = new Bolsa[101][101];
   ArrayList<Robot> robots = new ArrayList<>();
   ArrayList<Area> areas = new ArrayList<>();
   ArrayList<Thread> hilos = new ArrayList<>();
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   Main form;

   public Ciudad(Main city) throws Exception {
      this.setNumAv(100);
      this.setNumCa(100);

      for(int i = 0; i < this.ciudad.length; ++i) {
         for(int j = 0; j < this.ciudad[0].length; ++j) {
            this.ciudad[i][j] = new Bolsa();
         }
      }

      this.form = city;
      this.ok = false;
   }

   public void agregarHilo(Thread t) {
      this.hilos.add(t);
   }

   public void setNumAv(int num) {
      this.numAv = num;
   }

   public void setNumCa(int num) {
      this.numCa = num;
   }

   public int getNumAv() {
      return this.numAv;
   }

   public int getNumCa() {
      return this.numCa;
   }

   public boolean getOk() {
      return this.ok;
   }

   public void setOk(boolean ok) {
      this.ok = ok;
   }

   public int getCantidad_robots() {
      return this.cantidad_robots;
   }

   public void setCantidad_robots(int cantidad_robots) {
      this.cantidad_robots = cantidad_robots;
   }

   public boolean isFreePos(int ca, int av) throws Exception {
      if (ca < 1 || ca > this.getNumCa()) {
         this.parseError("No se puede ejecutar la instrucción \"mover\" debido a que el robot se caería de la ciudad");
         throw new Exception("No se puede ejecutar la instrucción \"mover\" debido a que el robot se caería de la ciudad");
      } else if (av >= 1 && av <= this.getNumAv()) {
         return !this.ciudad[av][ca].getObstaculo();
      } else {
         this.parseError("No se puede ejecutar la instrucción \"mover\" debido a que el robot se caería de la ciudad");
         throw new Exception("No se puede ejecutar la instrucción \"mover\" debido a que hay un obstáculo");
      }
   }

   public boolean isFreePosRobot(int ca, int av) {
      return this.ciudad[av][ca].isOcupado();
   }

   public boolean levantarFlor(int Av, int Ca) {
      boolean res = true;
      if (this.HayFlorEnLaEsquina(Av, Ca)) {
         this.ciudad[Av][Ca].setFlores(this.ciudad[Av][Ca].getFlores() - 1);
      } else {
         res = false;
         JOptionPane.showMessageDialog(
            null, "Run Time Error: No se puede ejecutar la instrucción \"tomarFlor\" debido a que no hay ninguna flor en la esquina ", "error", 0
         );
      }

      this.form.jsp.refresh();
      return res;
   }

   public boolean HayFlorEnLaEsquina(int Av, int Ca) {
      return this.getFlores(Av, Ca) > 0;
   }

   public boolean HayObstaculoEnLaEsquina(int Av, int Ca) throws Exception {
      switch(this.robots.get(0).getDireccion()) {
         case 0:
            if (Av < 100) {
               return this.ciudad[Av + 1][Ca].getObstaculo();
            }

            return false;
         case 90:
            if (Ca < 100) {
               return this.ciudad[Av][Ca + 1].getObstaculo();
            }

            return false;
         case 180:
            if (Av > 1) {
               return this.ciudad[Av - 1][Ca].getObstaculo();
            }

            return false;
         case 270:
            if (Ca > 1) {
               return this.ciudad[Av][Ca - 1].getObstaculo();
            }

            return false;
         default:
            System.out.println("Fallo en la direccion de Ciudad");
            throw new Exception("Fallo en la direccion de Ciudad");
      }
   }

   public int getFlores(int Av, int Ca) {
      return this.ciudad[Av][Ca].getFlores();
   }

   public boolean HayPapelEnLaEsquina(int Av, int Ca) {
      return this.getPapeles(Av, Ca) > 0;
   }

   public int getPapeles(int Av, int Ca) {
      return this.ciudad[Av][Ca].getPapeles();
   }

   public boolean levantarPapel(int Av, int Ca) {
      boolean res = true;
      if (this.HayPapelEnLaEsquina(Av, Ca)) {
         this.ciudad[Av][Ca].setPapeles(this.ciudad[Av][Ca].getPapeles() - 1);
      } else {
         res = false;
         JOptionPane.showMessageDialog(
            null, "Run Time Error: No se puede ejecutar la instrucción \"tomarPapel\" debido a que no hay ningun papel en la esquina", "error", 0
         );
      }

      this.form.jsp.refresh();
      return res;
   }

   public void dejarPapel(int Av, int Ca) {
      int old = this.getPapeles(Av, Ca);
      this.ciudad[Av][Ca].setPapeles(old + 1);
      this.pcs.firePropertyChange("EsquinaPapeles", old, this.getPapeles(Av, Ca));
      this.form.jsp.refresh();
   }

   public void dejarFlor(int Av, int Ca) {
      int old = this.getFlores(Av, Ca);
      this.ciudad[Av][Ca].setFlores(old + 1);
      this.pcs.firePropertyChange("EsquinaFlores", old, this.getFlores(Av, Ca));
      this.form.jsp.refresh();
   }

   public void parseError(String msg) {
      JOptionPane.showMessageDialog(null, "Run Time Error: " + msg, "error", 0);
   }

   public void addRobot(String name) {
      try {
         int old = this.robots.size();
         this.robots.add(new Robot(this, name));
         this.esperarRefresco.setCant_robots(this.robots.size());
         this.esperarRefresco.setCant_ejecutandose(this.robots.size());
         this.pcs.firePropertyChange("numRobots", old, this.robots.size());
      } catch (Exception var3) {
         Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, var3);
      }
   }

   public boolean estaRobot(String str) {
      boolean ok = false;
      int cant = this.robots.size();

      for(int i = 0; i < cant; ++i) {
         if (this.robots.get(i).nombre.equals(str)) {
            ok = true;
         }
      }

      return ok;
   }

   public boolean estaRobotConId(int id) {
      boolean ok = false;
      int cant = this.robots.size();

      for(int i = 0; i < cant; ++i) {
         if (this.robots.get(i).getId() == id) {
            ok = true;
         }
      }

      return ok;
   }

   public Robot getRobotByNombre(String str) throws Exception {
      int cant = this.robots.size();

      for(int i = 0; i < cant; ++i) {
         if (this.robots.get(i).getNombre().equals(str)) {
            return this.robots.get(i);
         }
      }

      this.parseError("El robot " + str + " no se encuentra en la ciudad");
      throw new Exception("El robot " + str + " no se encuentra en la ciudad");
   }

   public Robot getRobotById(int id) throws Exception {
      int cant = this.robots.size();

      for(int i = 0; i < cant; ++i) {
         if (this.robots.get(i).getId() == id) {
            return this.robots.get(i);
         }
      }

      throw new Exception("El robot con id " + id + " no se encuentra en la ciudad");
   }

   public Area getAreaByNombre(String str) throws Exception {
      int cant = this.areas.size();

      for(int i = 0; i < cant; ++i) {
         if (this.areas.get(i).getName().equals(str)) {
            return this.areas.get(i);
         }
      }

      throw new Exception("El area " + str + " no se encuentra declarada");
   }

   public void Informar(String args, int id) {
      this.form.mostrarMensaje(args, "Informar", 1);
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
   }

   public boolean HayObstaculo(int Av, int Ca) throws Exception {
      return this.ciudad[Av][Ca].getObstaculo();
   }
}
