package form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Configuraciones extends JPanel {
   Ciudad city;
   JButton bot1 = new JButton("Agregar");
   JButton bot2 = new JButton("OK");
   JButton bot3 = new JButton("OK");
   JButton bot4 = new JButton("Agregar");
   JComboBox com = new JComboBox();
   JComboBox com1 = new JComboBox();
   JComboBox com2 = new JComboBox();
   JTextField jf1 = new JTextField(3);
   JTextField jf2 = new JTextField(3);
   public JTextField jf3 = new JTextField(3);
   JLabel lab1 = new JLabel("Avenida: ");
   JLabel lab2 = new JLabel("Calle:   ");
   JLabel lab3 = new JLabel("Elemento:      ");
   JLabel lab4 = new JLabel("Bolsa de Flores ");
   JLabel lab5 = new JLabel("Bolsa de Papeles  ");
   JLabel lab6 = new JLabel("Cantidad:    ");
   GridBagConstraints gbc = new GridBagConstraints();
   JPanel JP;
   private int limite = 4;

   Configuraciones(Ciudad city) {
      this.jf3.addKeyListener(new KeyListener() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (Configuraciones.this.jf3.getText().length() == Configuraciones.this.limite) {
               e.consume();
            }
         }

         @Override
         public void keyPressed(KeyEvent arg0) {
         }

         @Override
         public void keyReleased(KeyEvent arg0) {
         }
      });
      new TitledBorder("Configuraciones");
      this.city = city;
      this.jf1.setText("0");
      this.jf2.setText("0");
      this.jf3.setText("0");
      this.jf3.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != '\b' && c != 127) {
               Configuraciones.this.getToolkit().beep();
               e.consume();
            }
         }
      });
      this.setLayout(new GridBagLayout());
      this.com.setModel(new DefaultComboBoxModel<>(new String[]{"Flores", "Papeles", "Obstaculos"}));
      this.com2
         .setModel(
            new DefaultComboBoxModel<>(
               new String[]{
                  "*",
                  "1",
                  "2",
                  "3",
                  "4",
                  "5",
                  "6",
                  "7",
                  "8",
                  "9",
                  "10",
                  "11",
                  "12",
                  "13",
                  "14",
                  "15",
                  "16",
                  "17",
                  "18",
                  "19",
                  "20",
                  "21",
                  "22",
                  "23",
                  "24",
                  "25",
                  "26",
                  "27",
                  "28",
                  "29",
                  "30",
                  "31",
                  "32",
                  "33",
                  "34",
                  "35",
                  "36",
                  "37",
                  "38",
                  "39",
                  "40",
                  "41",
                  "42",
                  "43",
                  "44",
                  "45",
                  "46",
                  "47",
                  "48",
                  "49",
                  "50",
                  "51",
                  "52",
                  "53",
                  "54",
                  "55",
                  "56",
                  "57",
                  "58",
                  "59",
                  "60",
                  "61",
                  "62",
                  "63",
                  "64",
                  "65",
                  "66",
                  "67",
                  "68",
                  "69",
                  "70",
                  "71",
                  "72",
                  "73",
                  "74",
                  "75",
                  "76",
                  "77",
                  "78",
                  "79",
                  "80",
                  "81",
                  "82",
                  "83",
                  "84",
                  "85",
                  "86",
                  "87",
                  "88",
                  "89",
                  "90",
                  "91",
                  "92",
                  "93",
                  "94",
                  "95",
                  "96",
                  "97",
                  "98",
                  "99",
                  "100"
               }
            )
         );
      this.com1
         .setModel(
            new DefaultComboBoxModel<>(
               new String[]{
                  "*",
                  "1",
                  "2",
                  "3",
                  "4",
                  "5",
                  "6",
                  "7",
                  "8",
                  "9",
                  "10",
                  "11",
                  "12",
                  "13",
                  "14",
                  "15",
                  "16",
                  "17",
                  "18",
                  "19",
                  "20",
                  "21",
                  "22",
                  "23",
                  "24",
                  "25",
                  "26",
                  "27",
                  "28",
                  "29",
                  "30",
                  "31",
                  "32",
                  "33",
                  "34",
                  "35",
                  "36",
                  "37",
                  "38",
                  "39",
                  "40",
                  "41",
                  "42",
                  "43",
                  "44",
                  "45",
                  "46",
                  "47",
                  "48",
                  "49",
                  "50",
                  "51",
                  "52",
                  "53",
                  "54",
                  "55",
                  "56",
                  "57",
                  "58",
                  "59",
                  "60",
                  "61",
                  "62",
                  "63",
                  "64",
                  "65",
                  "66",
                  "67",
                  "68",
                  "69",
                  "70",
                  "71",
                  "72",
                  "73",
                  "74",
                  "75",
                  "76",
                  "77",
                  "78",
                  "79",
                  "80",
                  "81",
                  "82",
                  "83",
                  "84",
                  "85",
                  "86",
                  "87",
                  "88",
                  "89",
                  "90",
                  "91",
                  "92",
                  "93",
                  "94",
                  "95",
                  "96",
                  "97",
                  "98",
                  "99",
                  "100"
               }
            )
         );
      this.gbc.anchor = 17;
      this.gbc.insets = new Insets(2, 10, 0, 0);
      this.gbc.gridx = 0;
      this.gbc.gridy = 0;
      this.add(this.lab3, this.gbc);
      this.gbc.gridx = 1;
      this.gbc.gridy = 0;
      this.add(this.lab1, this.gbc);
      this.gbc.gridx = 2;
      this.gbc.gridy = 0;
      this.add(this.lab2, this.gbc);
      this.gbc.gridx = 0;
      this.gbc.gridy = 1;
      this.add(this.com, this.gbc);
      this.gbc.gridx = 1;
      this.gbc.gridy = 1;
      this.add(this.com1, this.gbc);
      this.gbc.gridx = 2;
      this.gbc.gridy = 1;
      this.add(this.com2, this.gbc);
      this.gbc.gridx = 0;
      this.gbc.gridy = 2;
      this.add(this.lab6, this.gbc);
      this.gbc.gridx = 0;
      this.gbc.gridy = 3;
      this.gbc.gridwidth = 1;
      this.gbc.fill = 1;
      this.add(this.jf3, this.gbc);
      this.gbc.gridwidth = 0;
      this.gbc.fill = 0;
      this.gbc.gridx = 1;
      this.gbc.gridy = 3;
      this.gbc.weighty = 1.0;
      this.gbc.gridheight = 1;
      this.gbc.weightx = 1.0;
      this.add(this.bot4, this.gbc);
      this.gbc.fill = 2;
      this.gbc.gridx = 0;
      this.gbc.gridy = 4;
      this.JP = new TablaRobot(this.city);
      this.JP.setBorder(new TitledBorder("ROBOTS"));
      this.add(this.JP, this.gbc);
      this.revalidate();
      this.bot4.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent evt) {
            Configuraciones.this.jButton4MouseClicked(evt);
         }
      });
      this.bot2.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent evt) {
            Configuraciones.this.jtest(evt);
         }
      });
   }

   private void jtest(MouseEvent evt) {
   }

   private void jButton4MouseClicked(MouseEvent evt) {
      int x1 = this.com1.getSelectedIndex();
      int x2 = this.com2.getSelectedIndex();
      int aux = Integer.parseInt(this.jf3.getText());
      if (aux > 0) {
         for(int i = 0; i < aux; ++i) {
            int aux1;
            if (x1 == 0) {
               aux1 = (int)(Math.random() * 100.0) + 1;
            } else {
               aux1 = this.com1.getSelectedIndex();
            }

            int aux2;
            if (x2 == 0) {
               aux2 = (int)(Math.random() * 100.0) + 1;
            } else {
               aux2 = this.com2.getSelectedIndex();
            }

            if (this.com.getSelectedItem().toString().equals("Flores") && x1 == 0 && x2 == 0) {
               this.city.ciudad[aux1][aux2].setFlores(this.city.ciudad[aux1][aux2].getFlores() + 1);
            } else if (this.com.getSelectedItem().toString().equals("Flores") && x1 != 0 && x2 != 0) {
               this.city.ciudad[aux1][aux2].setFlores(aux);
            } else if (this.com.getSelectedItem().toString().equals("Flores")) {
               this.city.ciudad[aux1][aux2].setFlores(this.city.ciudad[aux1][aux2].getFlores() + 1);
            }

            if (this.com.getSelectedItem().toString().equals("Papeles") && x1 == 0 && x2 == 0) {
               this.city.ciudad[aux1][aux2].setPapeles(this.city.ciudad[aux1][aux2].getPapeles() + 1);
            } else if (this.com.getSelectedItem().toString().equals("Papeles") && x1 != 0 && x2 != 0) {
               this.city.ciudad[aux1][aux2].setPapeles(aux);
            } else if (this.com.getSelectedItem().toString().equals("Papeles")) {
               this.city.ciudad[aux1][aux2].setPapeles(this.city.ciudad[aux1][aux2].getPapeles() + 1);
            }

            if (this.com.getSelectedItem().toString().equals("Obstaculos")) {
               this.city.ciudad[aux1][aux2].setObstaculo(true);
            }
         }
      }

      this.city.form.jsp.refresh();
   }
}
