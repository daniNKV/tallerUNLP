package form;

class ttt implements Runnable {
   MonitorActualizarVentana m;

   public ttt(MonitorActualizarVentana m, Object ttt) {
      String s = (String)ttt;
      this.m = m;
      switch(s) {
         case "Max":
            m.setSpeed(165);
            break;
         case "Med":
            m.setSpeed(200);
            break;
         case "Min":
            m.setSpeed(250);
      }
   }

   @Override
   public void run() {
      if (this.m.isTimerOn()) {
         this.m.startTimer();
      }
   }
}
