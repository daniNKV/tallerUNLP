package arbol.llamada;

import arbol.DeclaracionProcesos;
import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.Proceso;
import arbol.Tipo;
import arbol.Variable;
import arbol.expresion.Expresion;
import java.util.ArrayList;

public class IdentificadorLlamada extends Llamada {
   Identificador I = null;
   DeclaracionProcesos DP = null;

   public IdentificadorLlamada(Identificador I, DeclaracionVariable DV) {
      this.I = I;
      this.DV = DV;
   }

   @Override
   public Llamada nuevo() throws Exception {
      return new IdentificadorLlamada(this.I, this.DV);
   }

   @Override
   public void ejecutar(ArrayList<Expresion> E) throws Exception {
      synchronized(this) {
         for(Expresion exp : E) {
            exp.setRobot(this.getRobot());
         }

         ArrayList<Variable> devolverValorOrigen = new ArrayList<>();
         ArrayList<Variable> devolverValorDestino = new ArrayList<>();
         String spelling = this.I.toString();
         Proceso proc = null;
         this.DP = this.getRobot().getProcAST();
         if (this.DP.estaProceso(spelling)) {
            proc = this.DP.getProceso(spelling);
            if (proc.getPF().size() != E.size()) {
               super.programa.getCity().parseError("Los parametros no coinciden en el proceso " + proc.getI().toString());
               throw new Exception("Los parametros actuales no coinciden con los parametros formales");
            } else {
               if (proc.getPF().size() > 0) {
                  for(int i = 0; i < proc.getPF().size(); ++i) {
                     if (proc.getPF().get(i).getTA().equals("ES")) {
                        try {
                           Variable a = this.DV.findByName(E.get(i).getI().toString());
                           if (!this.DV.EstaParametro(a.getI().toString())) {
                              super.getPrograma()
                                 .getCity()
                                 .parseError("Se esperaba una variable en la posición del parametro ES " + proc.getPF().get(i).getI().toString());
                              throw new Exception("Se esperaba una variable en la posición del parametro ES " + proc.getPF().get(i).getI().toString());
                           }

                           if (proc.getPF().get(i).getT().tipo != a.getT().tipo) {
                              super.getPrograma()
                                 .getCity()
                                 .parseError(
                                    "Se esperaba una variable de tipo "
                                       + proc.getPF().get(i).getT().toString()
                                       + " en el parametro "
                                       + proc.getPF().get(i).getI().toString()
                                 );
                              throw new Exception(
                                 "Se esperaba una variable de tipo "
                                    + proc.getPF().get(i).getT().toString()
                                    + " en el parametro "
                                    + proc.getPF().get(i).getI().toString()
                              );
                           }
                        } catch (Exception var12) {
                           super.getPrograma()
                              .getCity()
                              .parseError(
                                 "Se esperaba una variable de tipo "
                                    + proc.getPF().get(i).getT().toString()
                                    + " en la posición del parametro ES "
                                    + proc.getPF().get(i).getI().toString()
                              );
                           throw new Exception(var12.toString());
                        }
                     }

                     if (proc.getPF().get(i).getTA().equals("E")) {
                        if (E.get(i).getT() == null) {
                           if ((E.get(i).getValue(this.DV).equals("V") || E.get(i).getValue(this.DV).equals("F"))
                              && proc.getPF().get(i).getT().toString().equals("numero")) {
                              super.getPrograma()
                                 .getCity()
                                 .parseError(
                                    "El proceso "
                                       + proc.getPF().get(i).getI().toString()
                                       + " esperaba recibir un parametro de tipo "
                                       + proc.getPF().get(i).getT().toString()
                                 );
                              throw new Exception(
                                 "El proceso "
                                    + proc.getPF().get(i).getI().toString()
                                    + " esperaba recibir un parametro de tipo "
                                    + proc.getPF().get(i).getT().toString()
                              );
                           }

                           if (!E.get(i).getValue(this.DV).equals("V")
                              && !E.get(i).getValue(this.DV).equals("F")
                              && proc.getPF().get(i).getT().toString().equals("boolean")) {
                              super.getPrograma()
                                 .getCity()
                                 .parseError(
                                    "El proceso "
                                       + proc.getPF().get(i).getI().toString()
                                       + " esperaba recibir un parametro de tipo "
                                       + proc.getPF().get(i).getT().toString()
                                 );
                              throw new Exception(
                                 "El proceso "
                                    + proc.getPF().get(i).getI().toString()
                                    + " esperaba recibir un parametro de tipo "
                                    + proc.getPF().get(i).getT().toString()
                              );
                           }
                        } else if (!proc.getPF().get(i).getT().toString().equals(E.get(i).getT().toString())) {
                           super.getPrograma()
                              .getCity()
                              .parseError(
                                 "El proceso "
                                    + proc.getPF().get(i).getI().toString()
                                    + " esperaba recibir un parametro de tipo "
                                    + proc.getPF().get(i).getT().toString()
                              );
                           throw new Exception(
                              "El proceso "
                                 + proc.getPF().get(i).getI().toString()
                                 + " esperaba recibir un parametro de tipo "
                                 + proc.getPF().get(i).getT().toString()
                           );
                        }
                     }
                  }
               }

               if (E.size() > 0) {
                  int aux = E.size();

                  for(int i = 0; i < aux; ++i) {
                     Variable var = new Variable(proc.getPF().get(i).getI(), proc.getPF().get(i).getT(), this.DV, null, null);
                     var.setValue(E.get(i).getValue(this.DV));
                     this.DP.getProceso(spelling).getPF().get(i).setValue(E.get(i).getValue(this.DV));
                     if (this.DP.getProceso(spelling).getDV().EstaVariable(this.DP.getProceso(spelling).getPF().get(i).getI().toString())) {
                        this.DP
                           .getProceso(spelling)
                           .getDV()
                           .findByName(this.DP.getProceso(spelling).getPF().get(i).getI().toString())
                           .setValue(E.get(i).getValue(this.DV));
                     } else {
                        this.DP.getProceso(spelling).getDV().variables.add(var);
                        Variable var3 = new Variable(new Identificador("pepe"), new Tipo((byte)19), this.DV, null, null);
                        var3.setValue("222222");
                        this.DP.getProceso(spelling).getDV().variables.add(var3);
                     }

                     if (proc.getPF().get(i).getTA().equals("ES")) {
                        devolverValorOrigen.add(var);
                        Variable var1 = (Variable)E.get(i);
                        devolverValorDestino.add(var1);
                     }
                  }
               }

               proc.getC().setPrograma(super.getPrograma());
               proc.getC().setRobot(this.getRobot());
               proc.getC().ejecutar();
               if (devolverValorOrigen.size() > 0) {
                  int x = devolverValorOrigen.size();

                  for(int j = 0; j < x; ++j) {
                     devolverValorDestino.get(j).setValue(devolverValorOrigen.get(j).getValue(proc.getDV()));
                  }
               }

               if (devolverValorDestino.size() > 0) {
                  int z = devolverValorOrigen.size();

                  for(int j = 0; j < z; ++j) {
                     Variable exp = devolverValorDestino.get(j);
                     Variable exp1 = null;
                     this.DV.findByName(exp.getI().toString()).setValue(exp.getValor());
                  }
               }
            }
         } else {
            super.getPrograma().getCity().parseError("Error, instrucción desconocida: " + spelling);
            throw new Exception("Error, instrucción desconocida: " + spelling);
         }
      }
   }
}
