package form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Datos extends JPanel implements PropertyChangeListener {
   Ciudad city;
   JTextField lab5 = new JTextField("Av");
   JTextField lab6 = new JTextField("Ca");
   JLabel lab2;
   JLabel lab1;
   JLabel lab11;
   JLabel lab12;
   JLabel lab13;
   JLabel lab14;
   JLabel lab15;
   JLabel lab16;
   JLabel lab17;
   JLabel lab18;
   JLabel estado;
   ImageIcon icon;
   JLabel lab0 = new JLabel();
   PanelImg pa;
   Robot x;
   Color C = Color.red;

   Datos(Ciudad city, Robot x) {
      Dimension dim = new Dimension(200, 100);
      this.setMaximumSize(dim);
      this.city = city;
      this.x = x;
      this.lab5.setText(Integer.toString(x.Av));
      this.lab6.setText(String.valueOf(x.Ca));
      GridBagConstraints gbc = new GridBagConstraints();
      this.setLayout(new GridBagLayout());
      this.setBorder(new TitledBorder(x.getNombre()));
      this.lab1 = new JLabel("Pos:");
      this.lab2 = new JLabel("(0" + x.PosAv() + " , 0" + x.PosCa() + ")");
      JLabel lab3 = new JLabel("Bolsa   ");
      JLabel lab4 = new JLabel("Esquina");
      this.lab11 = new JLabel();
      this.lab11.setText("F  ");
      this.lab11.setForeground(x.getColor());
      this.lab11.setOpaque(true);
      this.lab12 = new JLabel("P ");
      this.lab13 = new JLabel();
      this.lab13.setForeground(x.getColor());
      this.lab13.setText("F  ");
      this.lab14 = new JLabel("P ");
      this.lab15 = new JLabel();
      this.lab15.setForeground(x.getColor());
      this.lab15.setText("00");
      this.lab16 = new JLabel("00");
      this.lab17 = new JLabel();
      this.lab17.setForeground(x.getColor());
      this.lab17.setText("00");
      this.lab18 = new JLabel("00");
      this.estado = new JLabel();
      this.estado.setText("Nuevo  ");
      int dir = x.getDireccion();
      switch(dir) {
         case 0:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotDerecha.png"));
            break;
         case 90:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotArriba.png"));
            break;
         case 270:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotAbajo.png"));
            break;
         default:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotIzquierda.png"));
      }

      Image img = this.icon.getImage();
      Image newimg = img.getScaledInstance(16, 16, 4);
      new ImageIcon(newimg);
      this.pa = new PanelImg(newimg);
      this.pa.setMaximumSize(new Dimension(16, 16));
      this.pa.setMinimumSize(new Dimension(16, 16));
      this.pa.setPreferredSize(new Dimension(16, 16));
      this.pa.setBackground(x.color);
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.gridy = 0;
      gbc.gridx = 0;
      gbc.gridwidth = 2;
      this.add(this.pa, gbc);
      gbc.gridwidth = 2;
      gbc.gridx = 2;
      this.add(this.lab1, gbc);
      gbc.gridwidth = 1;
      gbc.gridx = 4;
      this.add(this.lab2, gbc);
      gbc.gridy = 1;
      gbc.gridwidth = 2;
      gbc.gridx = 0;
      this.add(lab3, gbc);
      gbc.gridx = 2;
      this.add(lab4, gbc);
      gbc.gridwidth = 1;
      gbc.gridx = 0;
      gbc.gridy = 2;
      this.add(this.lab11, gbc);
      gbc.gridx = 1;
      gbc.gridy = 2;
      this.add(this.lab12, gbc);
      gbc.gridx = 2;
      gbc.gridy = 2;
      this.add(this.lab13, gbc);
      gbc.gridx = 3;
      gbc.gridy = 2;
      this.add(this.lab14, gbc);
      gbc.gridx = 0;
      gbc.gridy = 3;
      this.add(this.lab15, gbc);
      gbc.gridx = 1;
      gbc.gridy = 3;
      this.add(this.lab16, gbc);
      gbc.gridx = 2;
      gbc.gridy = 3;
      this.add(this.lab17, gbc);
      gbc.gridx = 3;
      gbc.gridy = 3;
      this.add(this.lab18, gbc);
      gbc.gridwidth = 4;
      gbc.gridx = 0;
      gbc.gridy = 4;
      this.add(this.estado, gbc);
   }

   public void setAv(String str) {
      str = this.convert(str);
      this.lab5.setText(str);
      this.lab2.setText("(" + this.lab5.getText() + " , " + this.lab6.getText() + ")");
      this.city.form.repaint();
   }

   public String convert(String str) {
      if (str.equals("1")) {
         str = "01";
      }

      if (str.equals("2")) {
         str = "02";
      }

      if (str.equals("3")) {
         str = "03";
      }

      if (str.equals("4")) {
         str = "04";
      }

      if (str.equals("5")) {
         str = "05";
      }

      if (str.equals("6")) {
         str = "06";
      }

      if (str.equals("7")) {
         str = "07";
      }

      if (str.equals("8")) {
         str = "08";
      }

      if (str.equals("9")) {
         str = "09";
      }

      if (str.equals("0")) {
         str = "00";
      }

      return str;
   }

   public void setFloresEsq(String num) {
      num = this.convert(num);
      this.lab17.setText(num);
      this.city.form.repaint();
   }

   public void setPapelesEsq(String num) {
      num = this.convert(num);
      this.lab18.setText(num);
      this.city.form.repaint();
   }

   public void setFloresBolsa(String num) {
      num = this.convert(num);
      this.lab15.setText(num);
      this.city.form.repaint();
   }

   public void setPapelesBolsa(String num) {
      num = this.convert(num);
      this.lab16.setText(num);
      this.city.form.repaint();
   }

   public void setCa(String str) {
      str = this.convert(str);
      this.lab6.setText(str);
      this.lab2.setText("(" + this.lab5.getText() + " , " + this.lab6.getText() + ")");
      this.city.form.repaint();
   }

   public void setIcon(int dir) {
      switch(dir) {
         case 0:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotDerecha.png"));
            break;
         case 90:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotArriba.png"));
            break;
         case 270:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotAbajo.png"));
            break;
         default:
            this.icon = new ImageIcon(this.getClass().getResource("/images/robotIzquierda.png"));
      }

      Image img = this.icon.getImage();
      Image newimg = img.getScaledInstance(16, 16, 4);
      ImageIcon newIcon = new ImageIcon(newimg);
      this.lab0.setIcon(newIcon);
      this.lab0.setIcon(newIcon);
      this.pa.setImage(newimg);
      this.city.form.repaint();
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      String propertyName = evt.getPropertyName();
      String propertyValue = evt.getNewValue().toString();
      if (propertyName.equals("av")) {
         this.setAv(propertyValue);
         this.setCa(Integer.toString(this.x.Ca));
         int flores = this.city.getFlores(this.x.PosAv(), this.x.PosCa());
         this.setFloresEsq(Integer.toString(flores));
         int papeles = this.city.getPapeles(this.x.PosAv(), this.x.PosCa());
         this.setPapelesEsq(Integer.toString(papeles));
      } else if (propertyName.equals("ca")) {
         this.setCa(propertyValue);
         this.setAv(Integer.toString(this.x.Av));
         int flores = this.city.getFlores(this.x.PosAv(), this.x.PosCa());
         this.setFloresEsq(Integer.toString(flores));
         int papeles = this.city.getPapeles(this.x.PosAv(), this.x.PosCa());
         this.setPapelesEsq(Integer.toString(papeles));
      } else if (propertyName.equals("direccion")) {
         this.setIcon(Integer.parseInt(propertyValue));
      } else if (propertyName.equals("flores")) {
         int flores = this.city.getFlores(this.x.PosAv(), this.x.PosCa());
         this.setFloresEsq(Integer.toString(flores));
         this.setFloresBolsa(propertyValue);
      } else if (propertyName.equals("papeles")) {
         this.setPapelesBolsa(propertyValue);
         int papeles = this.city.getPapeles(this.x.PosAv(), this.x.PosCa());
         this.setPapelesEsq(Integer.toString(papeles));
      } else if (propertyName.equals("EsquinaPapeles")) {
         int papeles = this.city.getPapeles(this.x.PosAv(), this.x.PosCa());
         this.setPapelesEsq(Integer.toString(papeles));
      } else if (propertyName.equals("EsquinaFlores")) {
         int flores = this.city.getFlores(this.x.PosAv(), this.x.PosCa());
         this.setFloresEsq(Integer.toString(flores));
      } else if (propertyName.equals("color")) {
         this.pa.setBackground((Color)evt.getNewValue());
         this.C = (Color)evt.getNewValue();
      } else if (propertyName.equals("estado")) {
         System.out.println(evt.getOldValue());
         this.estado.setText((String)evt.getNewValue());
         this.repaint();
      }
   }
}
