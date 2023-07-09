package form;

import arbol.Programa;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

public class CodePanel extends JPanel {
   private JToolBar toolBar;
   public MyTextPane text;
   Ciudad city;
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   private JButton saveButton;
   private JButton newButton;
   private JButton openButton;
   private JButton runButton;
   private JButton resetButton;
   private JButton stepButton;
   private JButton stopButton;
   private JComboBox speedCombo;
   private JButton pauseButton;
   private JButton compileButton;
   private JButton saveAsButton;
   JTextField NombreRobot = new JTextField(5);
   Thread thread = null;
   Thread threadVentana;
   Runnable exec1;
   Runnable exec2;
   Ejecucion exec;
   String path = "";

   public CodePanel(Ciudad city) throws Exception {
      this.city = city;
      this.setLayout(new BorderLayout());
      this.toolBar = new JToolBar();
      ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/newFile24.png"));
      this.newButton = new JButton(icon);
      this.newButton.setToolTipText("Nuevo");
      icon = new ImageIcon(this.getClass().getResource("/images/compile.png"));
      this.compileButton = new JButton(icon);
      this.compileButton.setToolTipText("Compilar");
      icon = new ImageIcon(this.getClass().getResource("/images/openProject24.png"));
      this.openButton = new JButton(icon);
      this.openButton.setToolTipText("Abrir");
      icon = new ImageIcon(this.getClass().getResource("/images/guardar_como.png"));
      this.saveAsButton = new JButton(icon);
      this.saveAsButton.setToolTipText("Guardar Como");
      icon = new ImageIcon(this.getClass().getResource("/images/save.png"));
      this.saveButton = new JButton(icon);
      this.saveButton.setToolTipText("Guardar");
      icon = new ImageIcon(this.getClass().getResource("/images/runProject24.png"));
      this.runButton = new JButton(icon);
      this.runButton.setToolTipText("Ejecutar");
      icon = new ImageIcon(this.getClass().getResource("/images/escoba.png"));
      this.resetButton = new JButton(icon);
      this.resetButton.setToolTipText("Reiniciar entorno");
      icon = new ImageIcon(this.getClass().getResource("/images/runProject25.png"));
      this.stepButton = new JButton(icon);
      this.stepButton.setToolTipText("Ejecutar paso a paso");
      icon = new ImageIcon(this.getClass().getResource("/images/stop.png"));
      this.stopButton = new JButton(icon);
      this.stopButton.setToolTipText("Parar programa");
      icon = new ImageIcon(this.getClass().getResource("/images/pause.png"));
      this.pauseButton = new JButton(icon);
      this.pauseButton.setToolTipText("Pausar programa");

      try {
         this.path = new File(".").getCanonicalPath();
      } catch (IOException var4) {
         Logger.getLogger(InspectorVariables.class.getName()).log(Level.SEVERE, null, var4);
      }

      this.newButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            CodePanel.this.doNewCommand();
         }
      });
      this.openButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               CodePanel.this.doOpenCommand();
            } catch (IOException var3) {
               System.out.println("error en el abrir archivo");
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.saveAsButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            CodePanel.this.doSaveAsCommand();
         }
      });
      this.saveButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            CodePanel.this.doSaveCommand();
         }
      });
      this.stopButton.setEnabled(false);
      this.stopButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            CodePanel.this.saveButton.setEnabled(true);
            CodePanel.this.newButton.setEnabled(true);
            CodePanel.this.openButton.setEnabled(true);
            CodePanel.this.runButton.setEnabled(false);
            CodePanel.this.resetButton.setEnabled(true);
            CodePanel.this.pauseButton.setEnabled(false);

            try {
               CodePanel.this.doStopStepByStep();
            } catch (Exception var3) {
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.runButton.setEnabled(false);
      this.runButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               CodePanel.this.runButton.setEnabled(false);
               CodePanel.this.stepButton.setEnabled(false);
               CodePanel.this.doRunCommand();
            } catch (Exception var3) {
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.stepButton.setEnabled(false);
      this.stepButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               CodePanel.this.saveButton.setEnabled(false);
               CodePanel.this.newButton.setEnabled(false);
               CodePanel.this.openButton.setEnabled(false);
               CodePanel.this.runButton.setEnabled(false);
               CodePanel.this.resetButton.setEnabled(false);
               CodePanel.this.stepButton.setEnabled(true);
               CodePanel.this.pauseButton.setEnabled(true);
               CodePanel.this.doStepCommand();
            } catch (Exception var3) {
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.resetButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               CodePanel.this.doResetCommand();
            } catch (Exception var3) {
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.pauseButton.setEnabled(false);
      this.pauseButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               CodePanel.this.doPauseCommand();
            } catch (Exception var3) {
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.compileButton.setEnabled(true);
      this.compileButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               CodePanel.this.doCompileCommand();
            } catch (Exception var3) {
               Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      });
      this.speedCombo = new JComboBox();
      this.speedCombo.setMaximumSize(new Dimension(60, 34));
      this.speedCombo.addItem("Max");
      this.speedCombo.addItem("Med");
      this.speedCombo.addItem("Min");
      this.toolBar.add(this.newButton);
      this.toolBar.add(this.openButton);
      this.toolBar.add(this.saveAsButton);
      this.toolBar.add(this.saveButton);
      this.toolBar.add(this.compileButton);
      this.toolBar.add(this.runButton);
      this.toolBar.add(this.speedCombo);
      this.toolBar.add(this.resetButton);
      this.toolBar.add(this.stepButton);
      this.toolBar.add(this.stopButton);
      this.toolBar.add(this.pauseButton);
      this.toolBar.add(new JPanel());
      this.add(this.toolBar, "North");
      this.text = new MyTextPane(this.city);
      this.add(this.text, "Center");
   }

   public void doNewCommand() {
      this.text.setText("");
      this.city.areas = new ArrayList<>();
      this.city.form.jsp.refresh();
   }

   public void doPauseCommand() {
      this.city.form.monitorCola.pausar_ejecucion();
      this.city.form.monitorCola.setEn_ejecucion(true);
      this.habilitarTodo();
      this.runButton.setEnabled(true);
      this.compileButton.setEnabled(false);
   }

   private void formKeyPressed(KeyEvent evt) {
   }

   public void doStepCommand() throws Exception {
      this.compileButton.setEnabled(false);
      this.pauseButton.setEnabled(false);
      this.stopButton.setEnabled(true);
      this.esperarRefresco.setPasoAPaso(true);
      this.esperarRefresco.setApretoF7(false);
      this.esperarRefresco.setTimerOn(false);
      if (!this.esperarRefresco.isEn_ejecucion()) {
         this.thread.start();
         this.threadVentana.start();
         this.stepButton.setEnabled(true);
      } else {
         this.esperarRefresco.despertar();
      }
   }

   public void doStopStepByStep() throws Exception {
      this.city.form.monitorCola.termine_ejecucion();
      this.city.form.monitorCola.setEn_ejecucion(false);
      this.city.form.monitorCola.setSistemaPausado(false);

      for(Thread t : this.city.hilos) {
         t.interrupt();
      }

      this.city.hilos = new ArrayList<>();

      for(Robot rrr : this.city.robots) {
         rrr.setEstado("finalizado");
      }

      this.thread.interrupt();
      this.habilitarTodo();
   }

   public void doResetCommand() throws Exception {
      for(int i = 0; i <= 100; ++i) {
         for(int j = 0; j <= 100; ++j) {
            this.city.ciudad[i][j] = new Bolsa();
         }
      }

      for(Robot rr : this.city.robots) {
         rr.reset();
      }

      this.city.form.jsp.refresh();
   }

   public void doOpenCommand() throws IOException {
      JFileChooser chooser = new JFileChooser(this.path);
      int status = chooser.showOpenDialog(this);
      if (status == 0) {
         File f = chooser.getSelectedFile();
         this.path = f.getAbsolutePath();

         try {
            FileReader fin = new FileReader(f);
            BufferedReader br = new BufferedReader(fin);
            char[] buffer = new char[4096];
            String txt = "";

            int len;
            while((len = br.read(buffer, 0, buffer.length)) != -1) {
               txt = txt + new String(buffer, 0, len);
            }

            txt = txt.replace("\r", "");
            this.text.setText(txt);
         } catch (Exception var9) {
            System.out.println("Exception en el doOpenCommand");
         }
      }
   }

   public void doSaveCommand() {
      if (!this.path.equals("") && !this.path.isEmpty()) {
         FileWriter fichero = null;
         PrintWriter pw = null;

         try {
            int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea guardar en el archivo " + this.path + " ?", "Confirmar", 0);
            if (i == 0) {
               fichero = new FileWriter(this.path);
               pw = new PrintWriter(fichero);
               pw.println(this.text.getText());
            }
         } catch (Exception var12) {
            var12.printStackTrace();
         } finally {
            try {
               if (null != fichero) {
                  fichero.close();
               }
            } catch (Exception var11) {
               var11.printStackTrace();
            }
         }
      } else {
         this.doSaveAsCommand();
      }
   }

   public void doSaveAsCommand() {
      JFileChooser chooser = new JFileChooser(this.path);
      if (chooser.showSaveDialog(this.text) == 0) {
         File fFileName = chooser.getSelectedFile();
         Writer out = null;

         try {
            try {
               out = new OutputStreamWriter(new FileOutputStream(fFileName), "UTF-8");
               out.write(this.text.getText());
            } finally {
               out.close();
            }
         } catch (Exception var8) {
         }
      }
   }

   public void doCompileCommand() throws Exception {
      try {
         Programa[] prgAST = new Programa[1];
         String code = this.text.getText();
         MonitorEsquinas esquinas = MonitorEsquinas.crearMonitorEsquinas();
         if (code.length() > 0) {
            this.city.robots = new ArrayList<>();
            this.city.areas = new ArrayList<>();
            Parser[] parser = new Parser[1];

            try {
               if (this.city.getCantidad_robots() > 0) {
                  this.city.form.c.conf.remove(9);
                  this.city.form.c.conf.JP = new TablaRobot(this.city);
                  this.city.form.c.conf.JP.setBorder(new TitledBorder("ROBOTS"));
                  this.city.form.c.conf.add(this.city.form.c.conf.JP, this.city.form.c.conf.gbc);
                  this.city.form.c.conf.JP.repaint();
               }

               parser[0] = new Parser(code, this.city);
               prgAST[0] = parser[0].parse();
               this.city.robots = new ArrayList<>();
               this.city.areas = new ArrayList<>();
               this.city.form.c.conf.remove(9);
               this.city.form.c.conf.JP = new TablaRobot(this.city);
               this.city.form.c.conf.JP.setBorder(new TitledBorder("ROBOTS"));
               this.city.form.c.conf.add(this.city.form.c.conf.JP, this.city.form.c.conf.gbc);
               this.city.form.c.conf.JP.repaint();
               this.city.form.c.conf.repaint();
               this.city.form.compedos.iv.tempPanel.removeAll();
               ((InspectorVariables.RobotsEnEjecucion)this.city.form.compedos.iv.tempPanelRobots).removeAll();
               this.city.form.compedos.iv.datos_robots = new ArrayList<>();
               this.city.form.compedos.iv.tempPanel.add(this.city.form.compedos.iv.form());
               this.city.form.compedos.iv.tempPanelRobots = this.city.form.compedos.iv.form2();
               this.city.form.compedos.iv.repaint();
               this.city.robots = new ArrayList<>();
               this.city.areas = new ArrayList<>();
               parser[0] = new Parser(code, this.city);
               prgAST[0] = parser[0].parse();

               try {
                  for(Robot r : this.city.robots) {
                     r.crearMonitor(this.city.robots.size());
                  }

                  this.esperarRefresco.setCantidad(this.city.robots.size());
                  this.city.form.monitorCola.setCant_robots(this.city.robots.size());
                  System.out.println("cantidad de robots en la ciudad " + this.city.robots.size());
                  this.city.form.monitorCola.setCant_ejecutandose(this.city.robots.size());
                  System.out.println("cantidad ejecutandose " + this.city.robots.size());
                  prgAST[0].setCity(this.city);
                  prgAST[0].setCodigo(this);
                  this.exec1 = new Ejecucion(prgAST[0], false, this);
                  this.thread = new Thread(this.exec1);
                  this.exec2 = new ttt(this.esperarRefresco, this.speedCombo.getSelectedItem());
                  this.threadVentana = new Thread(this.exec2);
                  this.runButton.setEnabled(true);
                  this.stepButton.setEnabled(true);
               } catch (Exception var7) {
                  parser[0].reportParseError("Error en Ejecutar!!:\n" + var7);
                  this.runButton.setEnabled(true);
                  this.habilitarTodo();
               }
            } catch (Exception var8) {
               parser[0].reportParseError("Error en compilación :\n" + var8);
               this.runButton.setEnabled(true);
               this.habilitarTodo();
            }

            this.city.form.jsp.refresh();
         } else {
            JOptionPane.showMessageDialog(this, "No hay código para compilar...");
            this.habilitarTodo();
         }
      } catch (Exception var9) {
         System.out.println("Error en catch");
         System.out.println(var9.toString());
      }
   }

   public void doRunCommand() throws Exception {
      this.stopButton.setEnabled(true);
      this.pauseButton.setEnabled(true);
      this.compileButton.setEnabled(false);
      if (this.esperarRefresco.isEn_ejecucion()) {
         this.esperarRefresco.despertarPause();
      } else {
         this.esperarRefresco.setTimerOn(true);
         this.thread.start();
         this.threadVentana.start();
         this.runButton.setEnabled(false);
      }
   }

   public void habilitarTodo() {
      this.saveButton.setEnabled(true);
      this.newButton.setEnabled(true);
      this.openButton.setEnabled(true);
      this.runButton.setEnabled(false);
      this.resetButton.setEnabled(true);
      this.stepButton.setEnabled(false);
      this.stopButton.setEnabled(false);
      this.pauseButton.setEnabled(false);
      this.compileButton.setEnabled(true);
   }
}
