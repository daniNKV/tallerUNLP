package form;

public class Bolsa {
   private int papeles = 0;
   private int flores = 0;
   private boolean obstaculo = false;
   private boolean ocupado = false;

   public int getPapeles() {
      return this.papeles;
   }

   public void setPapeles(int algo) {
      this.papeles = algo;
   }

   public int getFlores() {
      return this.flores;
   }

   public void setFlores(int algo) {
      this.flores = algo;
   }

   public boolean getObstaculo() {
      return this.obstaculo;
   }

   public void setObstaculo(boolean algo) {
      this.obstaculo = algo;
   }

   public boolean isOcupado() {
      return this.ocupado;
   }

   public void ocupar() {
      this.ocupado = true;
   }

   public void desocupar() {
      this.ocupado = false;
   }
}
