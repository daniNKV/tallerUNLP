package form;

import arbol.Identificador;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorMensajes {
   public ArrayList<Dato> datos;
   private static MonitorMensajes instance;
   private final ReentrantLock lock = new ReentrantLock();
   final Condition condicion = this.lock.newCondition();
   final Condition pause = this.lock.newCondition();
   private int cant_robots = 0;
   private boolean en_ejecucion = false;
   private boolean[] resultado;
   final Condition[] espera;
   final Condition esperaCualquiera = this.lock.newCondition();
   private boolean apretoF7 = false;
   final Condition barrera = this.lock.newCondition();
   private boolean pasoAPaso = false;
   private int bar = 0;
   private int cant_ejecutandose = 0;
   private boolean timerOn = false;
   private int time = 50;
   private boolean sistemaPausado = false;
   public Robot r;

   private MonitorMensajes(int cant, Robot r) {
      this.r = r;
      this.datos = new ArrayList<>();
      this.espera = new Condition[cant];
      this.resultado = new boolean[cant];

      for(int i = 0; i < cant; ++i) {
         this.espera[i] = this.lock.newCondition();
         this.resultado[i] = false;
      }
   }

   private void waitAgain() {
      try {
         Thread.sleep((long)this.time);
      } catch (InterruptedException var2) {
      }

      if (!this.sistemaPausado) {
         this.despertar();
      }

      if (this.timerOn) {
         this.waitAgain();
      }
   }

   public static MonitorMensajes getInstance() {
      return instance;
   }

   public static MonitorMensajes crearMonitorActualizarVentana(int cantidadClientes, Robot r) {
      instance = new MonitorMensajes(cantidadClientes, r);
      return instance;
   }

   public void recibirMensaje(Identificador nombreVariable, int id, Identificador NombreRobot) throws Exception {
      this.lock.lock();

      try {
         if (this.estaNombreRobotEnDatos(NombreRobot.toString())) {
            String valor = this.getValorByNombreRobot(NombreRobot.toString());
            this.getRobot().getVariables().findByName(nombreVariable.toString()).setValue(valor);
         } else if (NombreRobot.toString().equals("*")) {
            String str = this.getValorByComodin();
            if (str == null) {
               this.getRobot().esperarRefresco.esperaMensaje();
               System.out.println("Me duermo");
               this.esperaCualquiera.await();
               System.out.println("Me despierto");
               str = this.getValorByComodin();
               this.getRobot().getVariables().findByName(nombreVariable.toString()).setValue(str);
               int x = this.getRobot().esperarRefresco.getCant_ejecutandose();
               this.getRobot().esperarRefresco.setCant_ejecutandose(x + 1);
               this.getRobot().esperarRefresco.setDormidos(this.getRobot().esperarRefresco.getDormidos() - 1);
            } else {
               this.getRobot().getVariables().findByName(nombreVariable.toString()).setValue(str);
            }
         } else {
            this.getRobot().esperarRefresco.esperaMensaje();
            this.espera[id].await();
            if (this.estaNombreRobotEnDatos(NombreRobot.toString())) {
               String valor = this.getValorByNombreRobot(NombreRobot.toString());
               this.getRobot().getVariables().findByName(nombreVariable.toString()).setValue(valor);
               int x = this.getRobot().esperarRefresco.getCant_ejecutandose();
               this.getRobot().esperarRefresco.setCant_ejecutandose(x + 1);
               this.getRobot().esperarRefresco.setDormidos(this.getRobot().esperarRefresco.getDormidos() - 1);
            }
         }
      } catch (InterruptedException var9) {
         System.out.println("Interrupted Exc ");
         throw new RuntimeException();
      } finally {
         this.lock.unlock();
      }
   }

   private String getValorByNombreRobot(String nombre) {
      int i = this.getIndexByNombreRobot(nombre);
      String valor = this.datos.get(i).valor;
      this.datos.remove(i);
      return valor;
   }

   private String getValorByComodin() throws InterruptedException {
      String valor = null;
      int i = 0;
      if (i < this.datos.size()) {
         valor = this.datos.get(i).valor;
         this.datos.remove(i);
      }

      return valor;
   }

   private int getIndexByNombreRobot(String nombre) {
      int i = 0;

      for(Dato d : this.datos) {
         if (d.NombreRobot.equals(nombre)) {
            return i;
         }

         ++i;
      }

      System.out.println("Algo anda mal en getIndexByNombreRobot");
      return i;
   }

   public void llegoMensaje(int id, Dato d) {
      this.lock.lock();

      try {
         this.datos.add(d);
         this.espera[id].signal();
         this.esperaCualquiera.signal();
      } finally {
         this.lock.unlock();
      }
   }

   public boolean estaNombreRobotEnDatos(String nombre) {
      boolean ok = false;

      for(Dato d : this.datos) {
         if (d.NombreRobot.equals(nombre)) {
            ok = true;
         }
      }

      return ok;
   }

   public Robot getRobot() {
      return this.r;
   }

   public void dormir() {
      this.lock.lock();

      try {
         this.pause.await();
      } catch (InterruptedException var5) {
         System.out.println("Interrupted Exc ");
         throw new RuntimeException();
      } finally {
         this.lock.unlock();
      }
   }

   public void arrancar() {
      this.lock.lock();

      try {
         this.pause.signal();
      } finally {
         this.lock.unlock();
      }
   }

   public void despertar() {
      this.lock.lock();

      try {
         this.apretoF7 = true;

         for(int i = 0; i < this.cant_robots; ++i) {
            this.espera[i].signal();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public void despertarPause() {
      this.lock.lock();

      try {
         this.sistemaPausado = false;
      } finally {
         this.lock.unlock();
      }
   }
}
