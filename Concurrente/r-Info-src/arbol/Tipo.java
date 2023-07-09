package arbol;

import form.Token;

public class Tipo extends AST {
   public byte tipo;

   public Tipo(byte tipo) {
      this.tipo = tipo;
   }

   @Override
   public String toString() {
      return Token.spellings[this.tipo];
   }

   public byte getTipo() {
      return this.tipo;
   }

   public void setTipo(byte tipo) {
      this.tipo = tipo;
   }
}
