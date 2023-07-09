package form;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorEsquinas {
   private static MonitorEsquinas instance;
   private final ReentrantLock lock = new ReentrantLock();
   final Condition condicion = this.lock.newCondition();
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   private boolean[][] libre;
   Condition[][] espera;
   final Condition barrera = this.lock.newCondition();

   private MonitorEsquinas() {
      this.espera = new Condition[101][101];
      this.libre = new boolean[101][101];

      for(int i = 1; i <= 100; ++i) {
         for(int j = 1; j <= 100; ++j) {
            this.espera[i][j] = this.lock.newCondition();
            this.libre[i][j] = true;
         }
      }
   }

   public static MonitorEsquinas getInstance() {
      return instance;
   }

   public static MonitorEsquinas crearMonitorEsquinas() {
      instance = new MonitorEsquinas();
      return instance;
   }

   public void bloquear(int Av, int Ca) {
      this.lock.lock();

      try {
         System.out.println("Entre al bloquear");
         if (this.libre[Av][Ca]) {
            this.libre[Av][Ca] = false;
         } else {
            this.esperarRefresco.esperaEsquina();

            while(!this.libre[Av][Ca]) {
               this.espera[Av][Ca].await();
            }

            this.libre[Av][Ca] = false;
            this.esperarRefresco.setCant_ejecutandose(this.esperarRefresco.getCant_ejecutandose() + 1);
         }

         System.out.println("Sali del bloquear");
      } catch (InterruptedException var7) {
         System.out.println("Interrupted Exc ");
         throw new RuntimeException();
      } finally {
         this.lock.unlock();
      }
   }

   public void liberar(int Av, int Ca) {
      this.lock.lock();

      try {
         this.libre[Av][Ca] = true;
         this.espera[Av][Ca].signalAll();
      } finally {
         this.lock.unlock();
      }
   }
}
