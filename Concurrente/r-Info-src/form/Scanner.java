package form;

class Scanner {
   public char currentChar;
   public byte currentKind;
   public StringBuffer currentSpelling;
   String cadena;
   String parametro;
   int pos;
   int indent;
   int dedent;
   int numeroEspaciosLineaActual;
   int numeroEspaciosLineaAnterior;
   int fil;
   int col;
   boolean contarEspacios;

   Scanner(String cadena) throws Exception {
      this.cadena = cadena + "\n";
      this.pos = 0;
      this.indent = 0;
      this.dedent = 0;
      this.numeroEspaciosLineaActual = 0;
      this.numeroEspaciosLineaAnterior = 0;
      this.fil = 1;
      this.col = 1;
      this.contarEspacios = true;

      try {
         this.nextchar();
      } catch (Exception var3) {
         this.reportScanError(this.fil + " " + this.col + "FATAL Error!!!!!!!!!!!!!!!!!");
      }
   }

   private void reportScanError(String str) throws Exception {
      throw new Exception(str);
   }

   public void nextchar() throws Exception {
      if (this.pos < this.cadena.length()) {
         this.currentChar = this.cadena.charAt(this.pos);
         ++this.pos;
         ++this.col;
      } else {
         throw new FinArchivo();
      }
   }

   private void take(char expectedChar) throws Exception {
      if (this.currentChar == expectedChar) {
         this.currentSpelling.append(this.currentChar);
         this.nextchar();
      } else {
         this.reportScanError("Error");
      }
   }

   public void takeIt() throws Exception {
      this.currentSpelling.append(this.currentChar);
      this.nextchar();
   }

   private boolean isDigit(char c) {
      return Character.isDigit(c);
   }

   public boolean isLetter(char c) {
      return Character.isLetter(c);
   }

   public byte scanToken() throws Exception {
      if (Token.spellings[61].equals(String.valueOf(this.currentChar))) {
         do {
            this.takeIt();
         } while(!Token.spellings[62].equals(String.valueOf(this.currentChar)));

         this.takeIt();
         return 74;
      } else if (this.currentChar == '\'') {
         this.takeIt();
         return 78;
      } else if (this.isLetter(this.currentChar)) {
         this.takeIt();

         while(this.isLetter(this.currentChar) || this.isDigit(this.currentChar) || this.isPunto(this.currentChar) || this.isGuion(this.currentChar)) {
            this.takeIt();
         }

         return 0;
      } else if (this.currentChar == '(') {
         this.takeIt();
         return 21;
      } else if (this.currentChar == ';') {
         this.takeIt();
         return 60;
      } else if (this.currentChar == ':') {
         this.takeIt();
         if (this.currentChar == '=') {
            this.takeIt();
            return 31;
         } else {
            return 28;
         }
      } else if (this.currentChar == ')') {
         this.takeIt();
         return 22;
      } else if (this.currentChar == ',') {
         this.takeIt();
         return 24;
      } else if (this.currentChar == '+') {
         this.takeIt();
         return 45;
      } else if (this.currentChar == '-') {
         this.takeIt();
         return 46;
      } else if (this.currentChar == '/') {
         this.takeIt();
         return 47;
      } else if (this.currentChar == '*') {
         this.takeIt();
         return 48;
      } else if (this.currentChar == '~') {
         this.takeIt();
         return 49;
      } else if (this.currentChar == '&') {
         this.takeIt();
         return 50;
      } else if (this.currentChar == '|') {
         this.takeIt();
         return 51;
      } else if (this.currentChar == '=') {
         this.takeIt();
         return 30;
      } else if (this.currentChar == '<') {
         this.takeIt();
         if (this.currentChar == '=') {
            this.takeIt();
            return 56;
         } else if (this.currentChar == '>') {
            this.takeIt();
            return 54;
         } else {
            return 52;
         }
      } else if (this.currentChar == '>') {
         this.takeIt();
         if (this.currentChar == '=') {
            this.takeIt();
            return 55;
         } else {
            return 53;
         }
      } else if (!this.isDigit(this.currentChar)) {
         System.out.println("ESPACIOS LIBRES");
         this.currentSpelling.append("ERROR scanToken");
         throw new Exception("ERROR scanToken");
      } else {
         this.takeIt();

         while(this.isDigit(this.currentChar)) {
            this.takeIt();
         }

         return 23;
      }
   }

   public Token scan() throws Exception {
      try {
         if (this.currentChar == '\r') {
            this.nextchar();
         }

         if (this.currentChar == '\n') {
            this.numeroEspaciosLineaAnterior = this.numeroEspaciosLineaActual;
            this.col = 1;

            while(true) {
               this.nextchar();
               ++this.fil;
               if (this.currentChar != '\n') {
                  for(this.numeroEspaciosLineaActual = 0; this.currentChar == ' '; ++this.numeroEspaciosLineaActual) {
                     this.takeIt();
                  }

                  if (this.currentChar != '\n') {
                     if (Token.spellings[61].equals(String.valueOf(this.currentChar))) {
                        this.numeroEspaciosLineaActual = this.numeroEspaciosLineaAnterior;
                     } else if (this.numeroEspaciosLineaActual % 2 == 0) {
                        if (this.numeroEspaciosLineaActual != this.numeroEspaciosLineaAnterior) {
                           if (this.numeroEspaciosLineaActual > this.numeroEspaciosLineaAnterior) {
                              this.indent = (this.numeroEspaciosLineaActual - this.numeroEspaciosLineaAnterior) / 2;
                           } else {
                              this.dedent = (this.numeroEspaciosLineaAnterior - this.numeroEspaciosLineaActual) / 2;
                           }
                        }
                     } else {
                        this.reportScanError(
                           "Error de indentación! cantidad de espacios : "
                              + this.numeroEspaciosLineaActual
                              + ", linumeroEspaciosLineaAnterior anterior : "
                              + this.numeroEspaciosLineaAnterior
                        );
                     }
                     break;
                  }
               }
            }
         }

         if (this.indent > 0) {
            --this.indent;
            return new Token((byte)42, "INDENT");
         } else if (this.dedent > 0) {
            --this.dedent;
            return new Token((byte)43, "DEDENT");
         } else {
            this.currentSpelling = new StringBuffer("");
            this.currentKind = this.scanToken();

            while(this.currentChar == ' ') {
               this.nextchar();
            }

            return new Token(this.currentKind, this.currentSpelling.toString());
         }
      } catch (FinArchivo var2) {
         return new Token((byte)26, "EOT");
      }
   }

   private boolean isPunto(char cc) {
      return cc == '.';
   }

   private boolean isGuion(char cc) {
      return cc == '_' || cc == '-';
   }

   private boolean isComilla(char cc) {
      return cc == '\'';
   }
}
