package form;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter.HighlightPainter;

class MyLinePainter implements HighlightPainter, CaretListener, MouseListener, MouseMotionListener {
   private JTextComponent component;
   private Color color;
   private Rectangle lastView;

   public MyLinePainter(JTextComponent component) {
      this(component, null);
      this.setLighter(component.getSelectionColor());
   }

   public MyLinePainter(JTextComponent component, Color color) {
      this.component = component;
      this.setColor(color);
      component.addCaretListener(this);
      component.addMouseListener(this);
      component.addMouseMotionListener(this);

      try {
         component.getHighlighter().addHighlight(0, 0, this);
      } catch (BadLocationException var4) {
      }
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public void setLighter(Color color) {
      int red = Math.min(255, (int)((double)color.getRed() * 1.2));
      int green = Math.min(255, (int)((double)color.getGreen() * 1.2));
      int blue = Math.min(255, (int)((double)color.getBlue() * 1.2));
      this.setColor(new Color(red, green, blue));
   }

   @Override
   public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
      try {
         Rectangle r = c.modelToView(c.getCaretPosition());
         g.setColor(this.color);
         g.fillRect(0, r.y, c.getWidth(), r.height);
         if (this.lastView == null) {
            this.lastView = r;
         }
      } catch (BadLocationException var7) {
         System.out.println(var7);
      }
   }

   private void resetHighlight() {
      SwingUtilities.invokeLater(
         new Runnable() {
            @Override
            public void run() {
               try {
                  int offset = MyLinePainter.this.component.getCaretPosition();
                  Rectangle currentView = MyLinePainter.this.component.modelToView(offset);
                  if (MyLinePainter.this.lastView != null && MyLinePainter.this.lastView.y != currentView.y) {
                     MyLinePainter.this.component
                        .repaint(0, MyLinePainter.this.lastView.y, MyLinePainter.this.component.getWidth(), MyLinePainter.this.lastView.height);
                     MyLinePainter.this.lastView = currentView;
                  }
               } catch (BadLocationException var3) {
               }
            }
         }
      );
   }

   @Override
   public void caretUpdate(CaretEvent e) {
      this.resetHighlight();
   }

   @Override
   public void mousePressed(MouseEvent e) {
      this.resetHighlight();
   }

   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }

   @Override
   public void mouseReleased(MouseEvent e) {
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      this.resetHighlight();
   }

   @Override
   public void mouseMoved(MouseEvent e) {
   }
}
