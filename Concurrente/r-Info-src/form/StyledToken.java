package form;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

class StyledToken {
   String spelling;
   byte kind;
   int from;
   SimpleAttributeSet attributes;
   static byte IDENTIFER = 0;
   static byte OPERADOR = 1;
   static byte LITERAL = 2;
   static byte PRIMITIVA = 3;
   static byte VARIABLE = 4;
   static byte PALABRACLAVE = 5;
   static byte TIPOVARIABLE = 6;
   static byte COMENTARIO = 7;
   static byte EOF = 8;
   static byte AREA = 9;
   public static final String[] spellings = new String[]{"IDENTIFICADOR", "OPERADOR", "LITERAL", "PRIMITIVA", "VARIABLE", "PALABRACLAVE", "FIN DE ARCHIVO"};
   public static final String[] primitivas = new String[]{
      "EnviarMensaje",
      "RecibirMensaje",
      "BloquearEsquina",
      "LiberarEsquina",
      "Pos",
      "Informar",
      "AsignarArea",
      "Iniciar",
      "Leer",
      "Random",
      "mover",
      "derecha",
      "tomarFlor",
      "tomarPapel",
      "depositarFlor",
      "depositarPapel"
   };
   public static final String[] variables = new String[]{
      "PosAv", "PosCa", "HayFlorEnLaEsquina", "HayFlorEnLaBolsa", "HayPapelEnLaEsquina", "HayPapelEnLaBolsa", "HayObstaculo"
   };
   public static final String[] palabrasClave = new String[]{
      "comenzar", "variables", "fin", "programa", "procesos", "proceso", "ES", "S", "E", "areas", "robots", "robot"
   };
   public static final String[] areas = new String[]{"AreaP", "AreaC", "AreaPC"};
   public static final String[] tipoVariable = new String[]{"numero", "boolean"};
   public static final String[] comentario = new String[]{"{", "}"};

   StyledToken(byte currentKind, int from, String spelling, boolean ok, boolean comillas) {
      this.kind = currentKind;
      this.spelling = spelling;
      this.from = from;
      this.attributes = new SimpleAttributeSet();
      this.spelling = spelling.replace("\r", " ");
      if (ok) {
         StyleConstants.setForeground(this.attributes, new Color(100, 100, 100, 255));
      } else if (comillas) {
         StyleConstants.setForeground(this.attributes, new Color(253, 1, 254, 255));
      } else {
         if (this.kind == IDENTIFER) {
            for(String type : primitivas) {
               if (type.equals(spelling)) {
                  this.kind = PRIMITIVA;
                  StyleConstants.setItalic(this.attributes, true);
                  break;
               }
            }
         }

         if (this.kind == IDENTIFER) {
            for(String type : variables) {
               if (type.equals(spelling)) {
                  this.kind = VARIABLE;
                  StyleConstants.setForeground(this.attributes, new Color(0, 153, 0, 255));
               }
            }
         }

         if (this.kind == IDENTIFER) {
            for(String type : palabrasClave) {
               if (type.equals(spelling)) {
                  this.kind = PALABRACLAVE;
                  StyleConstants.setBold(this.attributes, true);
                  StyleConstants.setForeground(this.attributes, Color.RED);
                  break;
               }
            }
         }

         if (this.kind == IDENTIFER) {
            if ("V".equals(spelling)) {
               this.kind = LITERAL;
            }

            if ("F".equals(spelling)) {
               this.kind = LITERAL;
            }
         }

         if (this.kind == IDENTIFER) {
            for(String type : tipoVariable) {
               if (type.equals(spelling)) {
                  this.kind = TIPOVARIABLE;
                  StyleConstants.setForeground(this.attributes, new Color(0, 0, 230, 255));
               }
            }
         }

         if (this.kind == IDENTIFER) {
            for(String type : areas) {
               if (type.equals(spelling)) {
                  this.kind = AREA;
                  StyleConstants.setBold(this.attributes, true);
               }
            }
         }

         if (this.kind == LITERAL) {
            StyleConstants.setForeground(this.attributes, new Color(253, 1, 254, 255));
         }

         if (this.kind == COMENTARIO) {
            StyleConstants.setForeground(this.attributes, new Color(100, 100, 100, 255));
         }
      }
   }

   public SimpleAttributeSet getAttributes() {
      return this.attributes;
   }

   public int getFrom() {
      return this.from;
   }

   public int getLength() {
      return this.spelling.length();
   }
}
