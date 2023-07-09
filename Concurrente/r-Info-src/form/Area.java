package form;

import java.awt.Color;

public class Area {
   protected String name;
   protected int ca1;
   protected int av1;
   protected int ca2;
   protected int av2;
   protected Color color = null;

   public Area(int av1, int ca1, int av2, int ca2, String nombre) {
      this.ca1 = ca1;
      this.av1 = av1;
      this.ca2 = ca2;
      this.av2 = av2;
      this.setName(nombre);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAv1() {
      return this.av1;
   }

   public void setAv1(int av1) {
      this.av1 = av1;
   }

   public int getAv2() {
      return this.av2;
   }

   public void setAv2(int av2) {
      this.av2 = av2;
   }

   public int getCa1() {
      return this.ca1;
   }

   public void setCa1(int ca1) {
      this.ca1 = ca1;
   }

   public int getCa2() {
      return this.ca2;
   }

   public void setCa2(int ca2) {
      this.ca2 = ca2;
   }

   public Color getColor() {
      return this.color;
   }
}
