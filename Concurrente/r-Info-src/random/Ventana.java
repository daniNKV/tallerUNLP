package random;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Ventana extends JPanel {
   public Ventana() {
      JPanel principal = new JPanel();
      JLabel label1 = new JLabel("Esto es el panel principal");
      principal.add(label1);
      JPanel avanzado = new JPanel();
      JLabel label2 = new JLabel("Esto es el panel azanzado");
      principal.add(label2);
      JPanel privado = new JPanel();
      JLabel label3 = new JLabel("Esto es el panel privado");
      principal.add(label3);
      JTabbedPane Ventana = new JTabbedPane();
      ImageIcon icon = new ImageIcon("flor.png");
      principal.setPreferredSize(new Dimension(410, 500));
      Ventana.addTab("Avanzado", icon, avanzado);
      Ventana.addTab("Privado", icon, privado);
      this.add(Ventana);
      Ventana.setTabLayoutPolicy(1);
   }

   public static void Crear() {
      JFrame marco = new JFrame("Ejemploo solapass");
      marco.setDefaultCloseOperation(3);
      marco.add(new Ventana(), "Center");
      marco.pack();
      marco.setVisible(true);
   }

   public static void main(String[] args) {
      Crear();
   }
}
