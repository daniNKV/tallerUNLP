package form;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;

public class Compe extends JPanel {
   public Configuraciones conf;
   Ciudad city;
   GridBagConstraints gbc;

   Compe(Ciudad city) {
      this.city = city;
      GridBagConstraints gbc = new GridBagConstraints();
      this.conf = new Configuraciones(this.city);
      gbc.fill = 1;
      gbc.anchor = 10;
      this.add(this.conf, gbc);
   }
}
