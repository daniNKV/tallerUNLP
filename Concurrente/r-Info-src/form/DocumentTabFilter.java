package form;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

class DocumentTabFilter extends DocumentFilter {
   @Override
   public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
      string = string.replace("\t", "  ");
      string = string.replace("\r", "");
      string = string.replace("^M", "");
      super.insertString(fb, offset, string, attr);
   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
      text = text.replace("\t", "  ");
      text = text.replace("\r", "");
      text = text.replace("^M", "");
      super.replace(fb, offset, length, text, attrs);
   }
}
