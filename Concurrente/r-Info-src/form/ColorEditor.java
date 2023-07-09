package form;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ColorEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
   Color currentColor;
   JButton button = new JButton();
   JColorChooser colorChooser;
   JDialog dialog;
   protected static final String EDIT = "edit";

   public ColorEditor() {
      this.button.setActionCommand("edit");
      this.button.addActionListener(this);
      this.button.setBorderPainted(false);
      this.colorChooser = new JColorChooser();
      this.dialog = JColorChooser.createDialog(this.button, "Seleccione un color", true, this.colorChooser, this, null);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if ("edit".equals(e.getActionCommand())) {
         this.button.setBackground(this.currentColor);
         this.colorChooser.setColor(this.currentColor);
         this.dialog.setVisible(true);
         this.fireEditingStopped();
      } else {
         this.currentColor = this.colorChooser.getColor();
      }
   }

   @Override
   public Object getCellEditorValue() {
      return this.currentColor;
   }

   @Override
   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
      this.currentColor = (Color)value;
      return this.button;
   }
}
