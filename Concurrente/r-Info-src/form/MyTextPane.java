package form;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

class MyTextPane extends JScrollPane implements DocumentListener {
   public myTextBox text;
   private JTextArea lines;
   private int linesCount;
   Ciudad city;
   MyLinePainter painter;

   public MyTextPane(Ciudad city) {
      this.city = city;
      this.text = new myTextBox(city, this);
      this.text.getDocument().addDocumentListener(this);
      this.text.addKeyListener(this.text);
      this.painter = new MyLinePainter(this.text, new Color(233, 239, 248, 255));
      ((AbstractDocument)this.text.getDocument()).setDocumentFilter(new DocumentTabFilter());
      this.lines = new JTextArea("  1.");
      this.linesCount = 1;
      this.lines.setBackground(new Color(233, 232, 226, 255));
      this.lines.setEditable(false);
      this.lines.setFont(new Font("JetBrains Mono", 0, 15));
      this.lines.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 1, Color.LIGHT_GRAY));
      this.text.setFont(new Font("JetBrains Mono", 0, 15));
      this.getViewport().add(this.text);
      this.setRowHeaderView(this.lines);
   }

   void setText(String t) {
      this.text.setText(t);
      this.setLineNumbers();
   }

   String getText() {
      return this.text.getText();
   }

   String getText(int offs, int len) throws BadLocationException {
      return this.text.getText(offs, len);
   }

   int getCaretPosition() {
      return this.text.getCaretPosition();
   }

   public void setCaretPosition(int position) {
      this.text.setCaretPosition(position);
   }

   @Override
   public void changedUpdate(DocumentEvent de) {
   }

   @Override
   public void insertUpdate(DocumentEvent de) {
      this.setLineNumbers();
      this.updateSyntaxHighlighting();
   }

   @Override
   public void removeUpdate(DocumentEvent de) {
      this.setLineNumbers();
      this.updateSyntaxHighlighting();
   }

   public void setLineNumbers() {
      Element root = this.text.getDocument().getDefaultRootElement();
      int untilLine = root.getElementCount();
      int chars = untilLine > 99 ? ("" + untilLine).length() : 3;
      String t = "         1".substring(10 - chars) + ".";
      if (untilLine != this.linesCount) {
         this.linesCount = untilLine;

         for(int i = 2; i <= untilLine; ++i) {
            String tmp = "          " + i;
            t = t + "\n" + tmp.substring(tmp.length() - chars) + ".";
         }

         this.lines.setText(t);
         this.lines.setCaretPosition(0);
      }
   }

   public void updateSyntaxHighlighting() {
      Element root = this.text.getDocument().getDefaultRootElement();
      int paragraph = root.getElementIndex(this.text.getCaretPosition());
      int from = root.getElement(paragraph).getStartOffset();
      int length = root.getElement(paragraph).getEndOffset() - from;
      this.text.updateStyles(from, length);
   }
}
