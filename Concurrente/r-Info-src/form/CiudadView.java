package form;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollBar;

public class CiudadView extends JComponent implements MouseListener, MouseWheelListener {
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   private Color colorManzana;
   private int cityWidth;
   private int cityHeight;
   private int blockSize;
   private ImageIcon flor = new ImageIcon(this.getClass().getResource("/images/flor.png"));
   private ImageIcon papel = new ImageIcon(this.getClass().getResource("/images/papel.png"));
   private ImageIcon obstaculo = new ImageIcon(this.getClass().getResource("/images/obstaculo.png"));
   Ciudad city;
   Graphics lienzo;

   public CiudadView(Ciudad city) {
      this.setDoubleBuffered(true);
      this.setOpaque(true);
      this.city = city;
      this.addMouseListener(this);
      this.colorManzana = Color.GRAY;
      this.setBlockSize(10);
      this.addMouseWheelListener(this);
   }

   public void setBlockSize(int blockSize) {
      if (blockSize < 1) {
         blockSize = 1;
      }

      if (blockSize > 30) {
         blockSize = 30;
      }

      this.blockSize = blockSize;
      this.cityHeight = this.city.getNumCa() * blockSize * 2;
      this.cityWidth = this.city.getNumAv() * blockSize * 2;
   }

   public int getBlockSize() {
      return this.blockSize;
   }

   public Color getColorManzana() {
      return this.colorManzana;
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.lienzo = g;

      try {
         this.dibujarCiudad();
      } catch (Exception var5) {
         System.out.println("Error en Paint Component" + var5.getMessage());
      }

      for(int i = 0; i < this.city.robots.size(); ++i) {
         try {
            this.esperarRefresco.pintarRuta(this.city.robots.get(i));
         } catch (Exception var4) {
            System.out.println("Error en Paint Component en la clase CiudadView");
            Logger.getLogger(CiudadView.class.getName()).log(Level.SEVERE, null, var4);
         }
      }
   }

   public int x2Av(int x) {
      int pos = (x + 4) / (this.blockSize * 2) + 1;
      if (pos < 1) {
         pos = 1;
      }

      if (pos > this.city.getNumAv()) {
         pos = this.city.getNumAv();
      }

      return pos;
   }

   public int y2Ca(int y) {
      int pos = this.city.getNumCa() - (y + 4) / (this.blockSize * 2);
      if (pos < 1) {
         pos = 1;
      }

      if (pos > this.city.getNumCa()) {
         pos = this.city.getNumCa();
      }

      return pos;
   }

   public int Av2x(int Av) {
      return this.blockSize * 2 * (Av - 1) + this.blockSize / 2;
   }

   public int Ca2y(int Ca) {
      Ca = this.city.getNumCa() + 1 - Ca;
      return this.blockSize * 2 * (Ca - 1) + this.blockSize + this.blockSize / 2;
   }

   protected void dibujarCiudad() throws Exception {
      this.lienzo.setColor(Color.white);
      this.lienzo.fillRect(0, 0, this.getWidth(), this.getHeight());
      this.lienzo.setColor(Color.black);
      this.lienzo.setColor(this.getColorManzana());

      for(int ca = 1; ca <= this.city.getNumCa(); ++ca) {
         for(int av = 1; av <= this.city.getNumAv(); ++av) {
            if (ca == 1 || av == 100) {
               this.lienzo.setColor(Color.WHITE);
            }

            this.dibujarManzana(av, ca);
            this.dibujarEsquina(av, ca);
            this.lienzo.setColor(this.colorManzana);
         }
      }

      this.dibujarArea();
   }

   protected void dibujarCiudadVentanita() throws Exception {
      this.lienzo.setColor(Color.white);
      this.lienzo.fillRect(0, 0, this.getWidth(), this.getHeight());
      this.lienzo.setColor(Color.black);
      this.lienzo
         .drawRect(
            0,
            this.getBlockSize(),
            this.city.getNumAv() * this.getBlockSize() * 2 + this.getBlockSize(),
            this.city.getNumCa() * this.getBlockSize() * 2 + this.getBlockSize()
         );
      this.lienzo.setColor(this.getColorManzana());

      for(int ca = 1; ca <= this.city.getNumCa(); ++ca) {
         for(int av = 1; av <= this.city.getNumAv(); ++av) {
            if (ca == 0 || av == 100) {
               this.lienzo.setColor(Color.WHITE);
            }

            this.dibujarManzana(av, ca);
            this.dibujarEsquina(av, ca);
            this.lienzo.setColor(this.colorManzana);
         }
      }
   }

   protected void dibujarArea() throws Exception {
      Graphics2D g2 = (Graphics2D)this.lienzo;
      Stroke strokeBefore = g2.getStroke();
      int av = 1;
      int ca = 1;

      for(Area area : this.city.areas) {
         int x = this.Av2x(area.getAv1());
         int y = this.Ca2y(area.getCa2());
         int width = (area.getAv2() - area.getAv1()) * this.getBlockSize() * 2;
         int height = (area.getCa2() - area.getCa1()) * this.getBlockSize() * 2;
         g2.setStroke(strokeBefore);
         g2.setColor(Color.WHITE);
         g2.fillRect(x, y, width, height);
         g2.setColor(area.getColor());

         for(int var12 = area.getCa1() + 1; var12 <= area.getCa2(); ++var12) {
            for(int var11 = area.getAv1(); var11 < area.getAv2(); ++var11) {
               if (var12 == 1 || var11 == 100) {
                  this.lienzo.setColor(Color.WHITE);
               }

               this.lienzo.setColor(area.getColor());
               this.dibujarManzana(var11, var12);
               this.dibujarEsquina(var11, var12);
            }
         }

         g2.setColor(area.getColor());
         g2.setStroke(new BasicStroke(3.0F));
         g2.drawRect(x, y, width, height);
         this.lienzo.setColor(this.colorManzana);
      }

      g2.setStroke(strokeBefore);
   }

   private void dibujarManzana(int av, int ca) {
      int xCoord = this.Av2x(av) + this.blockSize / 2;
      int yCoord = this.Ca2y(ca) + this.blockSize / 2;
      this.lienzo.fillRect(xCoord, yCoord, this.blockSize, this.blockSize);
   }

   private void dibujarEsquina(int av, int ca) throws Exception {
      ImageIcon image = null;
      int xCoord = this.Av2x(av) - this.blockSize / 2;
      int yCoord = this.Ca2y(ca) - this.blockSize / 2;
      if (!this.city.isFreePos(ca, av)) {
         image = this.obstaculo;
      } else {
         if (this.city.HayPapelEnLaEsquina(av, ca)) {
            image = this.papel;
         }

         if (this.city.HayFlorEnLaEsquina(av, ca)) {
            image = this.flor;
         }
      }

      if (image != null) {
         this.lienzo.drawImage(image.getImage(), xCoord, yCoord, this.blockSize, this.blockSize, null);
      }
   }

   protected void dibujarRuta(Robot robot) throws Exception {
      synchronized(this) {
         try {
            int c = 0;
            Graphics2D g2 = (Graphics2D)this.lienzo;

            for(ArrayList<Coord> item : robot.getRutas()) {
               c = 0;
               int[] x = new int[item.size()];
               int[] y = new int[item.size()];
               g2.setColor(robot.getColor());
               g2.setStroke(new BasicStroke(2.0F));

               for(Coord punto : item) {
                  x[c] = this.Av2x(punto.getX());
                  y[c] = this.Ca2y(punto.getY());
                  ++c;
               }

               g2.drawPolyline(x, y, c);
               int xCoord = this.Av2x(robot.PosAv()) - this.blockSize / 2;
               int yCoord = this.Ca2y(robot.PosCa()) - this.blockSize / 2;
               this.lienzo.drawImage(robot.getImage(), xCoord, yCoord, this.blockSize, this.blockSize, null);
            }

            c = 0;
            ArrayList<Coord> itemx = robot.getRuta();
            int[] x = new int[itemx.size()];
            int[] y = new int[itemx.size()];
            g2.setColor(robot.getColor());
            g2.setStroke(new BasicStroke(2.0F));

            for(Coord punto : itemx) {
               x[c] = this.Av2x(punto.getX());
               y[c] = this.Ca2y(punto.getY());
               ++c;
            }

            g2.drawPolyline(x, y, c);
            int xCoord = this.Av2x(robot.PosAv()) - this.blockSize / 2;
            int yCoord = this.Ca2y(robot.PosCa()) - this.blockSize / 2;
            this.lienzo.fillRect(xCoord, yCoord, this.blockSize, this.blockSize);
            this.lienzo.drawImage(robot.getImage(), xCoord, yCoord, this.blockSize, this.blockSize, null);
         } catch (Exception var13) {
            throw new Exception("Algo anda mal en dibujarRuta  " + var13.getMessage());
         }
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(this.cityHeight, this.cityWidth);
   }

   @Override
   public Dimension getMinimumSize() {
      return this.getPreferredSize();
   }

   @Override
   public Dimension getMaximumSize() {
      return this.getPreferredSize();
   }

   @Override
   public void mousePressed(MouseEvent e) {
   }

   public void mouseDragged(MouseEvent e) {
   }

   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }

   public void mouseMoved(MouseEvent e) {
   }

   @Override
   public void mouseReleased(MouseEvent e) {
   }

   public int getHeight2(int offset) {
      return this.city.getNumCa() * (this.getBlockSize() + offset) * 2;
   }

   public int getHeight2() {
      return this.getHeight2(0);
   }

   public int getWidth2(int offset) {
      return this.city.getNumAv() * (this.getBlockSize() + offset) * 2;
   }

   public int getWidth2() {
      return this.getWidth2(0);
   }

   @Override
   public void mouseWheelMoved(MouseWheelEvent e) {
      Container tmp = this.getParent();

      for(int c = 0; c < 100 && !(tmp instanceof CityScroll); ++c) {
         tmp = tmp.getParent();
      }

      CityScroll tmp2 = (CityScroll)tmp;
      if (e.isControlDown()) {
         Rectangle rr = tmp2.getViewport().getBounds();
         if (this.getHeight2(e.getWheelRotation()) > rr.height && this.getWidth2(e.getWheelRotation()) > rr.width) {
            this.setBlockSize(this.getBlockSize() + e.getWheelRotation());
         }
      } else {
         JScrollBar vbar = this.city.form.jsp.getVerticalScrollBar();
         int newValue = vbar.getValue() + e.getWheelRotation() * this.getBlockSize() * 5;
         vbar.setValue(newValue);
      }

      tmp2.refresh();
   }

   @Override
   public void mouseClicked(MouseEvent e) {
   }
}
