package arbol;

public class RobotAST extends AST {
   private Cuerpo cuerpo;
   private DeclaracionVariable DV;
   private String nombre;
   private DeclaracionProcesos procAST;

   public RobotAST(Cuerpo c, DeclaracionVariable dv, String nom, DeclaracionProcesos procAST) {
      this.cuerpo = c;
      this.DV = dv;
      this.nombre = nom;
      this.procAST = procAST;
   }

   public DeclaracionVariable getDV() {
      return this.DV;
   }

   public void setDV(DeclaracionVariable DV) {
      this.DV = DV;
   }

   public Cuerpo getCuerpo() {
      return this.cuerpo;
   }

   public void setCuerpo(Cuerpo cuerpo) {
      this.cuerpo = cuerpo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public DeclaracionProcesos getProcAST() {
      return this.procAST;
   }

   public void setProcAST(DeclaracionProcesos procAST) {
      this.procAST = procAST;
   }
}
