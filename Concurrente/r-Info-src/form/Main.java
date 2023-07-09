package form;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements PropertyChangeListener {
   Ciudad cdad;
   Compedos compedos;
   public CityScroll jsp;
   MonitorActualizarVentana monitorCola = MonitorActualizarVentana.crearMonitorActualizarVentana(4);
   MonitorEsquinas esquinas = MonitorEsquinas.crearMonitorEsquinas();
   private JSplitPane splitPane;
   public Compe c;
   JFrame cod2 = new JFrame();
   ArrayList<JTextArea> jt = new ArrayList<>();

   public Main() {
      super(" R-info   |   VERSIÓN BETA (release 2.9)");
   }

   public void mostrarMensaje(String args, String titulo, int tipo) {
      JOptionPane.showMessageDialog(null, args, titulo, tipo);
   }

   public void createAndShowGUI() throws Exception {
      this.setDefaultCloseOperation(3);
      this.setExtendedState(6);

      try {
         this.cdad = new Ciudad(this);
      } catch (Exception var6) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, var6);
      }

      this.cdad.addPropertyChangeListener(this);
      this.compedos = new Compedos(this.cdad);
      this.jsp = new CityScroll(this.cdad);
      this.monitorCola.setCityScroll(this.jsp);
      FlowLayout exLay = new FlowLayout();
      this.cod2.setLayout(exLay);
      this.cod2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      this.cod2.pack();
      JPanel contentPane = (JPanel)this.getContentPane();
      JPanel contentPaneLeft = new JPanel(new BorderLayout(1, 2));
      this.c = new Compe(this.cdad);
      contentPaneLeft.add(this.c, "North");
      contentPaneLeft.add(this.compedos, "Center");
      contentPane.add(contentPaneLeft, "West");
      JSplitPane splitPane2 = new JSplitPane(0, new CodePanel(this.cdad), this.jsp);
      splitPane2.setOneTouchExpandable(true);
      splitPane2.setDividerLocation(300);
      JPanel contentPaneDerecho = new JPanel();
      contentPaneDerecho.add(this.compedos.iv.tempPanelRobots);
      this.splitPane = new JSplitPane(1, splitPane2, contentPaneDerecho);
      this.splitPane.setOneTouchExpandable(true);
      this.splitPane.setDividerLocation(750);
      this.splitPane.addPropertyChangeListener("dividerLocation", new PropertyChangeListener() {
         @Override
         public void propertyChange(PropertyChangeEvent pce) {
            Main.this.jsp.refresh();
         }
      });
      contentPane.add(this.splitPane, "Center");
      this.pack();
      this.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            try {
               new Main().createAndShowGUI();
            } catch (Exception var2) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, var2);
            }
         }
      });
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
   }
}
