package form;

import java.util.ArrayList;

class StyledScan {
   Ciudad city;
   String text;
   char currentChar;
   byte currentKind;
   int pos;
   int col;
   StringBuffer currentSpelling;
   private int fil;
   boolean ok;
   boolean comillas;
   ArrayList<StyledToken> currentArea = new ArrayList<>();

   public StyledScan(String text, Ciudad city) {
      this.city = city;
      this.text = text.replace("\r", "");
      this.pos = 1;
      this.col = 0;
      this.currentChar = this.text.charAt(0);
      this.ok = false;
      this.comillas = false;
   }

   public void nextchar() {
      if (this.pos < this.text.length()) {
         this.currentChar = this.text.charAt(this.pos);
         ++this.pos;
         ++this.col;
      } else {
         this.currentChar = '#';
      }
   }

   public char peakNextChar() {
      if (this.pos + 1 < this.text.length()) {
         return this.text.charAt(this.pos + 1);
      } else {
         System.out.println("Fin de archivo en el peakNextChar");
         return '#';
      }
   }

   private void take(char expectedChar) throws Exception {
      if (this.currentChar == expectedChar) {
         this.currentSpelling.append(this.currentChar);
         this.nextchar();
      } else {
         throw new Exception("Se esperaba el caracter " + expectedChar);
      }
   }

   public void takeIt() throws Exception {
      if (this.currentChar != '\r') {
         this.currentSpelling.append(this.currentChar);
      } else {
         System.out.println("Era barra r en el takeit");
      }

      this.nextchar();
   }

   public StyledToken scan() throws Exception {
      for(; this.currentChar == '\n' || this.currentChar == ' '; this.nextchar()) {
         if (this.currentChar == '\n') {
         }
      }

      this.currentSpelling = new StringBuffer("");
      int from = this.pos - 1;
      this.currentKind = this.scanToken();
      StyledToken myTkn = new StyledToken(this.currentKind, from, this.currentSpelling.toString(), this.ok, this.comillas);
      this.currentArea.add(myTkn);
      return myTkn;
   }

   public byte scanToken() throws Exception {
      if (Character.isLetter(this.currentChar)) {
         this.takeIt();

         while(Character.isLetter(this.currentChar) || Character.isDigit(this.currentChar)) {
            this.takeIt();
         }

         return StyledToken.IDENTIFER;
      } else if (!Character.isDigit(this.currentChar)) {
         byte kind;
         kind = -1;
         label52:
         switch(this.currentChar) {
            case '"':
               kind = StyledToken.LITERAL;
               this.comillas = !this.comillas;
               break;
            case '#':
               kind = StyledToken.EOF;
               break;
            case '&':
               kind = StyledToken.OPERADOR;
               break;
            case '(':
               kind = StyledToken.OPERADOR;
               break;
            case ')':
               kind = StyledToken.OPERADOR;
               break;
            case '*':
               kind = StyledToken.OPERADOR;
               break;
            case '+':
               kind = StyledToken.OPERADOR;
               break;
            case ',':
               kind = StyledToken.OPERADOR;
               break;
            case '-':
               kind = StyledToken.OPERADOR;
               break;
            case '/':
               kind = StyledToken.OPERADOR;
               break;
            case ':':
               if (this.peakNextChar() == '=') {
                  this.takeIt();
                  kind = StyledToken.OPERADOR;
               } else {
                  kind = StyledToken.OPERADOR;
               }
               break;
            case ';':
               kind = StyledToken.OPERADOR;
               break;
            case '<':
               switch(this.peakNextChar()) {
                  case '=':
                     this.takeIt();
                     kind = StyledToken.OPERADOR;
                     break label52;
                  case '>':
                     this.takeIt();
                     kind = StyledToken.OPERADOR;
                     break label52;
                  default:
                     kind = StyledToken.OPERADOR;
                     break label52;
               }
            case '=':
               kind = StyledToken.OPERADOR;
               break;
            case '>':
               if (this.peakNextChar() == '=') {
                  this.takeIt();
                  kind = StyledToken.OPERADOR;
               } else {
                  kind = StyledToken.OPERADOR;
               }
               break;
            case '{':
               kind = StyledToken.COMENTARIO;
               this.ok = true;
               break;
            case '|':
               kind = StyledToken.OPERADOR;
               break;
            case '}':
               kind = StyledToken.COMENTARIO;
               this.ok = false;
               break;
            case '~':
               kind = StyledToken.OPERADOR;
         }

         this.takeIt();
         return kind;
      } else {
         this.takeIt();

         while(Character.isDigit(this.currentChar)) {
            this.takeIt();
         }

         return StyledToken.LITERAL;
      }
   }
}
