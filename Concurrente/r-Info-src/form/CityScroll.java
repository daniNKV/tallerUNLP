package form;

import java.awt.Rectangle;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class CityScroll extends JScrollPane {
   public Ciudad city;
   public CiudadView jc;
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();

   public CityScroll(Ciudad city) {
      this.city = city;
      this.jc = new CiudadView(this.city);
      this.setViewportView(this.jc);
      this.repaint();
   }

   public void refresh() {
      this.jc.repaint();
      this.city.form.compedos.iv.vent.repaint();
   }

   public void view(double px, double py) {
      int y = (int)(py * (double)this.jc.getHeight2());
      int x = (int)(px * (double)this.jc.getWidth2());
      Rectangle b = this.getViewport().getBounds();
      if (y + b.height > this.jc.getHeight2()) {
         y = this.jc.getHeight2() - b.height;
      }

      if (x + b.width > this.jc.getWidth2()) {
         x = this.jc.getWidth2() - b.width;
      }

      JScrollBar vbar = this.getVerticalScrollBar();
      JScrollBar hbar = this.getHorizontalScrollBar();
      vbar.setValue(y);
      hbar.setValue(x);
      this.city.form.compedos.iv.vent.repaint();
   }
}
