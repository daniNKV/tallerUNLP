package form;

public class NodoGenerico<T> {
   private T dato;
   private NodoGenerico<T> siguiente;

   public T getDato() {
      return this.dato;
   }

   public void setDato(T dato) {
      this.dato = dato;
   }

   public NodoGenerico<T> getSiguiente() {
      return this.siguiente;
   }

   public void setSiguiente(NodoGenerico<T> siguiente) {
      this.siguiente = siguiente;
   }
}
