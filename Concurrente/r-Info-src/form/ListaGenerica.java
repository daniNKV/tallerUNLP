package form;

public abstract class ListaGenerica<T> {
   protected int tamanio;

   public abstract void comenzar();

   public abstract void proximo();

   public abstract boolean fin();

   public abstract T elemento();

   public abstract T elemento(int var1);

   public abstract boolean agregar(T var1);

   public abstract boolean agregar(T var1, int var2);

   public abstract boolean eliminar();

   public abstract boolean eliminar(int var1);

   public abstract boolean esVacia();

   public abstract boolean incluye(T var1);

   public abstract int tamanio();
}
