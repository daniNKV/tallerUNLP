package form;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D.Double;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.DefaultEditorKit.PasteAction;

class myTextBox extends JTextPane implements KeyListener {
   StyledDocument doc;
   StyledScan scanner;
   Ciudad city;
   MyTextPane mtp;
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();

   @Override
   public void setText(String t) {
      super.setText(t);
      this.mtp.setLineNumbers();
      this.updateStyles(0, t.length());
   }

   public int getLengthText() {
      return this.getText().length();
   }

   public myTextBox(Ciudad city, MyTextPane mtp) {
      this.city = city;
      this.mtp = mtp;
   }

   void appendAtCaretPosition(String str) {
      StyledDocument sdoc = this.getStyledDocument();

      try {
         sdoc.insertString(this.getCaretPosition(), str, null);
      } catch (BadLocationException var4) {
         Logger.getLogger(myTextBox.class.getName()).log(Level.SEVERE, null, var4);
      }
   }

   @Override
   public void keyTyped(KeyEvent e) {
      if (e.getKeyChar() == '\n') {
         try {
            String mySpaces = "";
            int c = this.getCaretPosition();
            String txt = this.getText(0, c);
            c -= 2;

            while(c >= 0 && txt.charAt(c) != '\n') {
               --c;
            }

            ++c;

            while(txt.charAt(c) == ' ') {
               mySpaces = mySpaces + " ";
               ++c;
            }

            this.appendAtCaretPosition(mySpaces);
         } catch (BadLocationException var5) {
            System.out.println("BadLocationException del keyTyped");
            Logger.getLogger(MyTextPane.class.getName()).log(Level.SEVERE, null, var5);
         }
      }
   }

   @Override
   public void keyPressed(KeyEvent e) {
      String texto = "";
      if (e.getKeyCode() == 118) {
         this.esperarRefresco.despertar();
      }

      if (e.isControlDown() && e.getKeyCode() == 86) {
         Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
         Transferable t = cb.getContents(this);

         try {
            DataFlavor dataFlavorStringJava = new DataFlavor("text/plain;class=java.lang.String");
            if (t.isDataFlavorSupported(dataFlavorStringJava)) {
               try {
                  String str = (String)t.getTransferData(dataFlavorStringJava);
                  String fin = System.getProperty("line.separator");
                  str = str.replace(fin, "\n");
                  String[] ss = str.split("\n");
                  System.out.println(ss.length);
                  System.out.println(ss[0].length());
                  if (ss.length == 1) {
                     System.out.println(" paste : una");
                     StringSelection strSel = new StringSelection(str);
                     cb.setContents(strSel, null);
                     new PasteAction().actionPerformed(null);
                     this.mtp.updateSyntaxHighlighting();
                  } else {
                     System.out.println("paste : varias");

                     for(int i = 0; i < ss.length; ++i) {
                        System.out.println(ss[i]);
                        str = ss[i].replace(fin, "");
                        str = str + fin;
                        StringSelection strSel = new StringSelection(str);
                        cb.setContents(strSel, null);
                        new PasteAction().actionPerformed(null);
                     }

                     String act = this.mtp.getText();
                     act = act.replace(fin, "\n");
                     this.setText(act);
                  }

                  e.consume();
               } catch (UnsupportedFlavorException var11) {
                  System.out.println("error CadePanel L 107x");
                  Logger.getLogger(myTextBox.class.getName()).log(Level.SEVERE, null, var11);
               } catch (IOException var12) {
                  System.out.println("error CadePanel L 107x");
                  Logger.getLogger(myTextBox.class.getName()).log(Level.SEVERE, null, var12);
               }
            }
         } catch (ClassNotFoundException var13) {
            System.out.println("error CadePanel L 107x");
            Logger.getLogger(myTextBox.class.getName()).log(Level.SEVERE, null, var13);
         }
      }
   }

   public void controlV(String str) {
   }

   @Override
   public void keyReleased(KeyEvent e) {
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Document sdoc = this.getDocument();
      String txt = "";
      ArrayList<Integer> spaces = new ArrayList<>();
      int len = sdoc.getLength();

      try {
         txt = sdoc.getText(0, len);
      } catch (BadLocationException var14) {
         System.out.println("BadLocationException del paintComponent en CODEPANEL");
      }

      boolean contarEspacios = false;
      int esp = 0;

      for(int c = 0; c < txt.length(); ++c) {
         switch(txt.charAt(c)) {
            case '\n':
               if (contarEspacios) {
                  for(; esp > 0; --esp) {
                     if (esp % 2 == 0) {
                        spaces.remove(spaces.size() - 1);
                     }
                  }
               }

               contarEspacios = true;
               esp = 0;
               break;
            case '\r':
               System.out.println("BARRA R");
               break;
            case ' ':
               if (contarEspacios) {
                  if (++esp % 2 == 0) {
                     spaces.add(new Integer(c));
                  }
               }
               break;
            default:
               contarEspacios = false;
         }
      }

      if (contarEspacios) {
         for(int c = esp; c > 0; --c) {
            if (c % 2 == 0) {
               spaces.remove(spaces.size() - 1);
            }
         }
      }

      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(new Color(219, 219, 219, 255));
      g2.setStroke(new BasicStroke(1.0F));

      for(Integer space : spaces) {
         try {
            Rectangle r = this.modelToView(space);
            g2.setColor(new Color(255, 255, 255, 255));
            g2.draw(new Double((double)(r.x + 1), (double)r.y, (double)(r.x + 6), (double)r.y));
            g2.setColor(new Color(200, 200, 200, 255));
            g2.draw(new Double((double)(r.x + 1), (double)r.y, (double)(r.x + 1), (double)r.y + r.getHeight()));
            g2.draw(new Double((double)(r.x + 1), (double)r.y + r.getHeight(), (double)(r.x + 6), (double)r.y + r.getHeight()));
         } catch (BadLocationException var13) {
            Logger.getLogger(MyTextPane.class.getName()).log(Level.SEVERE, null, var13);
         }
      }
   }

   public void updateStyles(final int from, int length) {
      if (length > 0) {
         this.doc = this.getStyledDocument();
         this.scanner = null;

         try {
            String xx = this.doc.getText(from, length);
            this.scanner = new StyledScan(xx, this.city);
         } catch (BadLocationException var6) {
            System.out.println("BadLocationException Exception");
            Logger.getLogger(MyTextPane.class.getName()).log(Level.SEVERE, null, var6);
         }

         if (this.scanner != null) {
            SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {
                  myTextBox.this.parseStyles(from);
               }
            });
         }
      }
   }

   private void parseStyles(int lastFrom) {
      StyledToken tkn;
      try {
         while((tkn = this.scanner.scan()).kind != StyledToken.EOF) {
            this.doc.setCharacterAttributes(lastFrom + tkn.getFrom(), tkn.getLength(), tkn.getAttributes(), true);
         }
      } catch (Exception var4) {
         Logger.getLogger(MyTextPane.class.getName()).log(Level.SEVERE, null, var4);
      }
   }
}
