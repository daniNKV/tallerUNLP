package form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class TablaRobot extends JPanel implements PropertyChangeListener {
   final String[] columnNames = new String[]{"Robot", "Flores", "Papeles", "Color"};
   GridBagConstraints gbc = new GridBagConstraints();
   Ciudad city;
   final DefaultTableModel modelo;

   TablaRobot(final Ciudad city) {
      this.city = city;
      this.setLayout(new GridBagLayout());
      this.gbc.fill = 1;
      this.modelo = new TablaRobot.MiModelo();
      this.modelo.addColumn("Robot");
      this.modelo.addColumn("Flores");
      this.modelo.addColumn("Papeles");
      this.modelo.addColumn("Color");
      this.city.addPropertyChangeListener(this);
      this.modelo.addTableModelListener(new TableModelListener() {
         @Override
         public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int col = e.getColumn();
            if (col >= 0) {
               Object value = TablaRobot.this.modelo.getValueAt(row, col);
               switch(col) {
                  case 1:
                     if (value == null) {
                        city.robots.get(row).setFloresEnBolsaDeConfiguracion(0);
                     } else {
                        int x = Integer.parseInt(value.toString());
                        city.robots.get(row).setFloresEnBolsaDeConfiguracion(x);
                     }
                     break;
                  case 2:
                     if (value == null) {
                        city.robots.get(row).setPapelesEnBolsaDeConfiguracion(0);
                     } else {
                        int x = Integer.parseInt(value.toString());
                        city.robots.get(row).setPapelesEnBolsaDeConfiguracion(x);
                     }
                     break;
                  case 3:
                     Color co = (Color)value;
                     city.robots.get(row).setColor(co);
                     city.form.jsp.refresh();
               }
            }
         }
      });
      Object[] datos = new Object[4];

      for(Robot rr : this.city.robots) {
         datos[0] = rr.getNombre();
         datos[1] = 0;
         datos[2] = 0;
         datos[3] = rr.getColor();
         this.modelo.addRow(datos);
      }

      JTable table1 = new JTable(this.modelo);
      table1.setDefaultRenderer(Color.class, new ColorRenderer(true));
      table1.setDefaultEditor(Color.class, new ColorEditor());
      table1.setPreferredScrollableViewportSize(new Dimension(200, 80));
      this.add(new JScrollPane(table1), this.gbc);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      String propertyName = evt.getPropertyName();
      String propertyValue = evt.getNewValue().toString();
      if (propertyName.equals("numRobots")) {
         int i = Integer.parseInt(propertyValue) - 1;
         Robot rr = this.city.robots.get(i);
         Object[] datos = new Object[]{rr.getNombre(), 0, 0, rr.getColor()};
         this.modelo.addRow(datos);
      }
   }

   public class MiModelo extends DefaultTableModel implements TableCellEditor {
      private LinkedList suscriptores = new LinkedList();

      MiModelo() {
      }

      @Override
      public void addCellEditorListener(CellEditorListener l) {
         this.suscriptores.add(l);
      }

      protected void editado(boolean cambiado) {
         ChangeEvent evento = new ChangeEvent(this);

         for(int i = 0; i < this.suscriptores.size(); ++i) {
            CellEditorListener aux = (CellEditorListener)this.suscriptores.get(i);
            if (cambiado) {
               aux.editingStopped(evento);
            } else {
               aux.editingCanceled(evento);
            }
         }
      }

      @Override
      public boolean isCellEditable(int row, int column) {
         return column > 0;
      }

      @Override
      public Class getColumnClass(int columna) {
         if (columna == 1) {
            return Integer.class;
         } else {
            return columna == 2 ? Integer.class : this.getValueAt(0, columna).getClass();
         }
      }

      @Override
      public int getColumnCount() {
         return TablaRobot.this.columnNames.length;
      }

      @Override
      public String getColumnName(int col) {
         return TablaRobot.this.columnNames[col];
      }

      @Override
      public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      public Object getCellEditorValue(int aRow, int aColumn) {
         System.out.println(" ACA ESTOY");
         return null;
      }

      @Override
      public boolean shouldSelectCell(EventObject anEvent) {
         return true;
      }

      @Override
      public boolean stopCellEditing() {
         return true;
      }

      @Override
      public void cancelCellEditing() {
      }

      @Override
      public void removeCellEditorListener(CellEditorListener l) {
         this.suscriptores.remove(l);
      }

      @Override
      public boolean isCellEditable(EventObject anEvent) {
         return true;
      }

      @Override
      public Object getCellEditorValue() {
         System.out.println("IMPRIMI");
         return null;
      }
   }
}
