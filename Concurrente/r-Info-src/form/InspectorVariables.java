package form;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class InspectorVariables extends JPanel implements PropertyChangeListener {
   public Ciudad city;
   Ventanita vent;
   JScrollPane ttt;
   public ArrayList<Datos> datos_robots = new ArrayList<>();
   JPanel tempPanel;
   JScrollPane robotsData;
   InspectorVariables.RobotsEnEjecucion robotsEnEjecutando = new InspectorVariables.RobotsEnEjecucion(this.datos_robots);
   public JPanel tempPanelRobots;

   public InspectorVariables(Ciudad city) {
      this.city = city;
      this.city.addPropertyChangeListener(this);

      for(Robot rr : city.robots) {
         System.out.println("ENTRE");
         Datos tmp = new Datos(this.city, rr);
         rr.addPropertyChangeListener(tmp);
         this.city.addPropertyChangeListener(tmp);
         this.datos_robots.add(tmp);
      }

      this.tempPanel = this.form();
      this.add(this.tempPanel);
      this.tempPanelRobots = this.form2();
      this.add(this.tempPanelRobots);
   }

   public JPanel form() {
      JPanel panel = new JPanel();
      GridBagConstraints gbc = new GridBagConstraints();
      panel.setLayout(new GridBagLayout());
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridy = 0;
      gbc.fill = 3;
      this.ttt = this.ventanita();
      panel.add(this.ttt, gbc);

      for(int i = 0; i < this.datos_robots.size(); ++i) {
         gbc.gridy = i + 2;
         gbc.fill = 1;
         panel.add(this.datos_robots.get(i), gbc);
      }

      return panel;
   }

   public JScrollPane ventanita() {
      this.vent = new Ventanita(this.city);
      JScrollPane panel = new JScrollPane(this.vent);
      panel.setOpaque(false);
      panel.setBorder(new TitledBorder("Miniatura..."));
      panel.setPreferredSize(new Dimension(212, 228));
      panel.setViewportView(this.vent);
      panel.setHorizontalScrollBarPolicy(31);
      panel.setVerticalScrollBarPolicy(21);
      panel.setVisible(true);
      panel.repaint();
      return panel;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      String propertyName = evt.getPropertyName();
      String propertyValue = evt.getNewValue().toString();
      if (propertyName.equals("numRobots")) {
         int i = Integer.parseInt(propertyValue) - 1;
         GridBagConstraints gbc = new GridBagConstraints();
         Robot rr = this.city.robots.get(i);
         Datos tmp = new Datos(this.city, rr);
         rr.addPropertyChangeListener(tmp);
         this.city.addPropertyChangeListener(tmp);
         this.datos_robots.add(tmp);
         gbc.gridx = 0;
         gbc.fill = 3;
         gbc.gridy = i + 2;
         gbc.fill = 1;
         this.tempPanel.add(this.datos_robots.get(i), gbc);
         this.tempPanel.revalidate();
         ((InspectorVariables.RobotsEnEjecucion)this.tempPanelRobots).panel.add(this.datos_robots.get(i), gbc);
      }
   }

   public JPanel form2() {
      this.robotsEnEjecutando.initComponents(this.datos_robots);
      return this.robotsEnEjecutando;
   }

   public class RobotsEnEjecucion extends JPanel {
      public JPanel panel;
      public JScrollPane scrollPane;

      public RobotsEnEjecucion(ArrayList<Datos> datos_robots) {
         Dimension dim = super.getToolkit().getScreenSize();
         dim.width = 230;
         dim.height -= 100;
         this.setPreferredSize(dim);
         this.setLayout(new BoxLayout(this, 1));
         this.initComponents(datos_robots);
         this.setBorder(new TitledBorder("ROBOTS EN EJECUCION"));
      }

      private void initComponents(ArrayList<Datos> datos_robots) {
         this.panel = new JPanel();
         this.panel.setLayout(new BoxLayout(this.panel, 1));
         this.scrollPane = new JScrollPane(this.panel);
         this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
         this.add(this.scrollPane, "Center");

         for(int i = 0; i < datos_robots.size(); ++i) {
            this.panel.add(datos_robots.get(i));
         }

         this.panel.revalidate();
      }
   }
}
