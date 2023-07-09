package form;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JViewport;

public class Ventanita extends CiudadView {
   private int rectanguloAncho;
   private int rectanguloAlto;
   int xCoord;
   int yCoord;

   public Ventanita(Ciudad city) {
      super(city);
      this.setBlockSize(1);
   }

   public int getXrect() {
      JViewport vp = this.city.form.jsp.getViewport();
      Point p = vp.getViewPosition();
      CiudadView cv = (CiudadView)vp.getComponent(0);
      return (int)((double)p.x / (double)cv.getWidth2() * (double)this.getWidth2());
   }

   public int getYrect() {
      JViewport vp = this.city.form.jsp.getViewport();
      Point p = vp.getViewPosition();
      CiudadView cv = (CiudadView)vp.getComponent(0);
      return (int)((double)p.y / (double)cv.getHeight2() * (double)this.getHeight2());
   }

   public int getRectWidth() {
      return this.rectanguloAncho;
   }

   public int getRectHeight() {
      return this.rectanguloAlto;
   }

   public void updateRectWidth() {
      double blockVisible = this.city.form.jsp.jc.getVisibleRect().getWidth() / (double)this.city.form.jsp.jc.getWidth2();
      this.rectanguloAncho = (int)((double)this.getWidth2() * blockVisible);
   }

   public void updateRectHeight() {
      double blockVisible = this.city.form.jsp.jc.getVisibleRect().getHeight() / (double)this.city.form.jsp.jc.getHeight2();
      this.rectanguloAlto = (int)((double)this.getHeight2() * blockVisible);
   }

   @Override
   protected void dibujarCiudad() throws Exception {
      super.dibujarCiudadVentanita();
      this.updateRectHeight();
      this.updateRectWidth();
      this.lienzo.setColor(Color.black);
      this.lienzo.drawRect(this.getXrect(), this.getYrect(), this.getRectWidth(), this.getRectHeight());
   }

   @Override
   protected void dibujarRuta(Robot robot) {
      this.xCoord = this.Av2x(robot.PosAv()) - this.getBlockSize() / 2;
      this.yCoord = this.Ca2y(robot.PosCa()) - this.getBlockSize() / 2;
      this.lienzo.setColor(robot.getColor());
      if (this.yCoord != 201) {
         this.lienzo.fillOval(this.xCoord, this.yCoord, 4, 4);
      }
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      double x = (double)(e.getX() - this.getRectWidth() / 2) / (double)this.getWidth();
      double y = (double)(e.getY() - this.getRectHeight() / 2) / (double)this.getHeight();
      this.city.form.jsp.view(x, y);
   }

   public void Camarita() {
      double x = (double)(this.xCoord - this.getRectWidth() / 2) / (double)this.getWidth();
      double y = (double)(this.yCoord - this.getRectHeight() / 2) / (double)this.getHeight();
      this.city.form.jsp.view(x, y);
   }

   @Override
   public void mouseWheelMoved(MouseWheelEvent e) {
   }
}
