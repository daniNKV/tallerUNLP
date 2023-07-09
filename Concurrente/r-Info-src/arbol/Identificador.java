package arbol;

public class Identificador extends AST {
   String spelling;

   public Identificador(String spelling) {
      this.spelling = spelling;
   }

   public boolean equals(String str) {
      return this.spelling.equals(str);
   }

   @Override
   public String toString() {
      return this.spelling;
   }
}
