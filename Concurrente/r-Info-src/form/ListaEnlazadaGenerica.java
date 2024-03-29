package form;

public class ListaEnlazadaGenerica<T> extends ListaGenerica<T> {
   private NodoGenerico<T> inicio;
   private NodoGenerico<T> actual;

   @Override
   public void comenzar() {
      this.actual = this.inicio;
   }

   @Override
   public void proximo() {
      this.actual = this.actual.getSiguiente();
   }

   @Override
   public T elemento() {
      return this.actual.getDato();
   }

   @Override
   public T elemento(int pos) {
      this.comenzar();

      while(pos-- > 0) {
         this.proximo();
      }

      return this.actual.getDato();
   }

   @Override
   public boolean agregar(T elem, int pos) {
      if (pos >= 0 && pos <= this.tamanio()) {
         ++this.tamanio;
         NodoGenerico<T> aux = new NodoGenerico<>();
         aux.setDato(elem);
         if (pos == 0) {
            aux.setSiguiente(this.inicio);
            this.inicio = aux;
         } else {
            this.comenzar();

            while(--pos > 0) {
               this.proximo();
            }

            aux.setSiguiente(this.actual.getSiguiente());
            this.actual.setSiguiente(aux);
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean eliminar() {
      if (this.actual == null) {
         return false;
      } else {
         --this.tamanio;
         if (this.actual == this.inicio) {
            this.inicio = this.inicio.getSiguiente();
            return true;
         } else {
            NodoGenerico<T> p = this.inicio;

            while(p.getSiguiente() != this.actual) {
               p = p.getSiguiente();
            }

            p.setSiguiente(this.actual.getSiguiente());
            this.actual = p.getSiguiente();
            return true;
         }
      }
   }

   @Override
   public boolean eliminar(int pos) {
      if (pos >= 0 && pos < this.tamanio()) {
         --this.tamanio;
         if (pos == 0) {
            this.inicio = this.inicio.getSiguiente();
            return true;
         } else {
            this.comenzar();

            while(--pos > 0) {
               this.proximo();
            }

            this.actual.setSiguiente(this.actual.getSiguiente().getSiguiente());
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean incluye(T elem) {
      this.comenzar();

      while(!this.fin() && !this.elemento().equals(elem)) {
         this.proximo();
      }

      return !this.fin();
   }

   @Override
   public int tamanio() {
      return this.tamanio;
   }

   @Override
   public boolean fin() {
      return this.actual == null;
   }

   @Override
   public boolean esVacia() {
      return this.tamanio() == 0;
   }

   @Override
   public boolean agregar(T elem) {
      if (this.actual == null && this.tamanio() > 0) {
         return false;
      } else {
         ++this.tamanio;
         NodoGenerico<T> aux = new NodoGenerico<>();
         if (this.actual == this.inicio) {
            aux.setSiguiente(this.inicio);
            this.inicio = aux;
            aux.setDato(elem);
         } else {
            T temp = this.actual.getDato();
            aux.setSiguiente(this.actual.getSiguiente());
            this.actual.setSiguiente(aux);
            aux.setDato(temp);
            this.actual.setDato(elem);
         }

         this.actual = aux;
         return true;
      }
   }
}
