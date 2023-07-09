package form;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

public class NewJFrame extends JFrame {
   private JButton jButton1;
   private JColorChooser jColorChooser1;
   private JOptionPane jOptionPane1;
   private JPanel jPanel1;
   private JToggleButton jToggleButton1;

   public NewJFrame() {
      this.initComponents();
   }

   private void initComponents() {
      this.jPanel1 = new JPanel();
      this.jButton1 = new JButton();
      this.jColorChooser1 = new JColorChooser();
      this.jToggleButton1 = new JToggleButton();
      this.jOptionPane1 = new JOptionPane();
      this.setDefaultCloseOperation(3);
      this.jButton1.setText("jButton1");
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanel1Layout.createSequentialGroup()
                  .addContainerGap(-1, 32767)
                  .addComponent(this.jColorChooser1, -2, -1, -2)
                  .addGap(18, 18, 18)
                  .addComponent(this.jButton1)
            )
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(161, 32767).addComponent(this.jButton1).addGap(482, 482, 482))
            .addGroup(jPanel1Layout.createSequentialGroup().addGap(49, 49, 49).addComponent(this.jColorChooser1, -2, -1, -2).addContainerGap(280, 32767))
      );
      this.jToggleButton1.setText("jToggleButton1");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(288, 32767).addComponent(this.jOptionPane1, -2, -1, -2))
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(382, 382, 382)
                  .addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jToggleButton1).addComponent(this.jPanel1, -2, -1, -2))
                  .addContainerGap(-1, 32767)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(21, 21, 21)
                  .addComponent(this.jToggleButton1)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jPanel1, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                  .addComponent(this.jOptionPane1, -2, -1, -2)
            )
      );
      this.pack();
   }

   public static void main(String[] args) {
      try {
         for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var5) {
         Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, var8);
      }

      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            new NewJFrame().setVisible(true);
         }
      });
   }
}
