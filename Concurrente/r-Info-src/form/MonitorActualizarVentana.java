package form;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorActualizarVentana {
   private CityScroll jsp;
   private static MonitorActualizarVentana instance;
   private final ReentrantLock lock = new ReentrantLock();
   final Condition condicion = this.lock.newCondition();
   final Condition pause = this.lock.newCondition();
   private int cant_robots = 0;
   private boolean en_ejecucion = false;
   private boolean[] resultado;
   Condition[] espera;
   private boolean apretoF7 = false;
   final Condition barrera = this.lock.newCondition();
   private boolean pasoAPaso = false;
   private int bar = 0;
   private int cant_ejecutandose = 0;
   private boolean timerOn = false;
   private int dormidos = 0;
   private int time = 165;
   private boolean sistemaPausado = false;

   public int getBar() {
      return this.bar;
   }

   public void setBar(int bar) {
      this.bar = bar;
   }

   public int getDormidos() {
      return this.dormidos;
   }

   public void setDormidos(int dormidos) {
      this.dormidos = dormidos;
   }

   public boolean isTimerOn() {
      return this.timerOn;
   }

   public void setTimerOn(boolean timerOn) {
      this.timerOn = timerOn;
   }

   public boolean isSistemaPausado() {
      return this.sistemaPausado;
   }

   public void setSistemaPausado(boolean sistemaPausado) {
      this.sistemaPausado = sistemaPausado;
   }

   public void setSpeed(int t) {
      this.time = t;
   }

   private MonitorActualizarVentana(int cant) {
      this.setCant_robots(cant);
      this.setCant_ejecutandose(cant);
      this.setDormidos(0);
      this.espera = new Condition[cant];
      this.resultado = new boolean[cant];

      for(int i = 0; i < cant; ++i) {
         this.espera[i] = this.lock.newCondition();
         this.resultado[i] = false;
      }
   }

   public void setCityScroll(CityScroll jsp) {
      this.jsp = jsp;
   }

   public void setCantidad(int cant) {
      this.setCant_robots(cant);
      this.setCant_ejecutandose(cant);
      this.setDormidos(0);
      this.espera = new Condition[cant];
      this.resultado = new boolean[cant];

      for(int i = 0; i < cant; ++i) {
         this.espera[i] = this.lock.newCondition();
         this.resultado[i] = false;
      }
   }

   public void startTimer() {
      this.setTimerOn(true);
      this.waitAgain();
   }

   private void waitAgain() {
      try {
         Thread.sleep((long)this.time);
      } catch (InterruptedException var2) {
      }

      if (!this.sistemaPausado) {
         this.despertar();
      }

      if (this.isTimerOn()) {
         this.waitAgain();
      }
   }

   public int getCant_ejecutandose() {
      return this.cant_ejecutandose;
   }

   public void setCant_ejecutandose(int cant_ejecutandose) {
      this.cant_ejecutandose = cant_ejecutandose;
   }

   public boolean isEn_ejecucion() {
      return this.en_ejecucion;
   }

   public void setEn_ejecucion(boolean en_ejecucion) {
      this.en_ejecucion = en_ejecucion;
   }

   public boolean isPasoAPaso() {
      return this.pasoAPaso;
   }

   public void setPasoAPaso(boolean pasoAPaso) {
      this.pasoAPaso = pasoAPaso;
   }

   public boolean isApretoF7() {
      return this.apretoF7;
   }

   public void setApretoF7(boolean apretoF7) {
      this.apretoF7 = apretoF7;
   }

   public int getCant_robots() {
      return this.cant_robots;
   }

   public void setCant_robots(int cant_robots) {
      this.cant_robots = cant_robots;
   }

   public static MonitorActualizarVentana getInstance() {
      return instance;
   }

   public static MonitorActualizarVentana crearMonitorActualizarVentana(int cantidadClientes) {
      instance = new MonitorActualizarVentana(cantidadClientes);
      return instance;
   }

   public void esperaMensaje() {
      this.lock.lock();

      try {
         if (this.getCant_ejecutandose() - this.getBar() == 1) {
            this.barrera.signalAll();
         }

         this.setCant_ejecutandose(this.getCant_ejecutandose() - 1);
         this.setDormidos(this.getDormidos() + 1);
      } finally {
         this.lock.unlock();
      }
   }

   public void esperaEsquina() {
      this.lock.lock();

      try {
         if (this.cant_ejecutandose - this.bar == 1) {
            this.barrera.signalAll();
         }

         --this.cant_ejecutandose;
      } finally {
         this.lock.unlock();
      }
   }

   public void esperar(int id) {
      this.lock.lock();

      try {
         while(!this.apretoF7) {
            this.espera[id].await();
         }

         this.setBar(this.getBar() + 1);
         if (this.getBar() < this.getCant_ejecutandose()) {
            this.barrera.await();
         } else {
            this.barrera.signalAll();
            this.setApretoF7(false);
            this.setBar(0);
         }
      } catch (InterruptedException var6) {
         System.out.println("Interrupted Exc ");
         throw new RuntimeException();
      } finally {
         this.lock.unlock();
      }
   }

   public void pintarRuta(Robot r) throws Exception {
      this.lock.lock();

      try {
         this.jsp.jc.dibujarRuta(r);
         this.jsp.city.form.compedos.iv.vent.dibujarRuta(r);
      } finally {
         this.lock.unlock();
      }
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
         this.setApretoF7(true);

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

   public boolean termine_ejecucion() {
      this.lock.lock();

      boolean var3;
      try {
         System.out.println("Termine ejecucion");
         boolean resultado = false;
         int canti = this.getCant_ejecutandose();
         this.setCant_ejecutandose(this.getCant_ejecutandose() - 1);
         resultado = this.getCant_ejecutandose() == 0 && this.getDormidos() == 0;
         System.out.println("ejecutandose 0 y dormidos 0,el resultado es : " + resultado);
         if (this.getBar() == this.getCant_ejecutandose()) {
            System.out.println("termine y desperte a todos ");
            this.barrera.signalAll();
            this.setBar(0);
         }

         var3 = resultado;
      } finally {
         this.lock.unlock();
      }

      return var3;
   }

   public void pausar_ejecucion() {
      this.lock.lock();

      try {
         this.sistemaPausado = true;
      } finally {
         this.lock.unlock();
      }
   }
}
