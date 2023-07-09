package form;

import arbol.Cuerpo;
import arbol.DeclaracionProcesos;
import arbol.DeclaracionVariable;
import arbol.Identificador;
import arbol.ParametroFormal;
import arbol.Proceso;
import arbol.Variable;
import arbol.sentencia.Sentencia;
import arbol.sentencia.primitiva.Mover;
import java.awt.Color;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Robot {
   private int ciclos;
   private ArrayList<Coord> misCalles;
   private DeclaracionProcesos procAST;
   private Cuerpo cueAST;
   private DeclaracionVariable varAST;
   private ImageIcon robotImage = new ImageIcon(this.getClass().getResource("/images/robotAbajo.png"));
   private ArrayList<Coord> ruta = new ArrayList<>();
   private ArrayList<ArrayList<Coord>> rutas = new ArrayList<>();
   public int Av = 0;
   public int Ca = 0;
   private int direccion = 90;
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   private Ciudad city;
   private int floresEnBolsa = 0;
   private int papelesEnBolsa = 0;
   private int floresEnBolsaDeConfiguracion = 0;
   private int papelesEnBolsaDeConfiguracion = 0;
   private ArrayList<Area> areas;
   MonitorEsquinas esquinas = MonitorEsquinas.getInstance();
   MonitorActualizarVentana esperarRefresco = MonitorActualizarVentana.getInstance();
   public int id;
   private static int cant = 0;
   public int offsetAv = 0;
   public int offsetCa = 0;
   public String dir;
   public MonitorMensajes monitor;
   String nombre;
   Color color;
   public String estado;

   public Robot(Ciudad city, String nombre) throws Exception {
      this.misCalles = new ArrayList<>();
      this.areas = new ArrayList<>();
      this.Av = 0;
      this.Ca = 0;
      this.getRuta().add(new Coord(this.Av, this.Ca));
      this.setNombre(nombre);
      this.city = city;
      this.rutas.add(this.ruta);
      this.id = this.getCity().robots.size();
      this.color = this.getColorById(this.id);
      this.setDireccion(90);
      this.setFlores(this.getFloresEnBolsaDeConfiguracion());
      this.setPapeles(this.getPapelesEnBolsaDeConfiguracion());
      this.estado = "nuevo  ";
   }

   public void crear() throws UnknownHostException, IOException {
      this.dir = "";
      System.out.println(this.getId());
      if (this.id == 0) {
         int puerto = 4000;
         File archivo = null;
         FileReader fr = null;
         BufferedReader br = null;
         archivo = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "Conf.txt");

         try {
            fr = new FileReader(archivo);
         } catch (FileNotFoundException var18) {
            Logger.getLogger(Mover.class.getName()).log(Level.SEVERE, null, var18);
         }

         br = new BufferedReader(fr);
         String ip = null;

         String linea;
         while((linea = br.readLine()) != null) {
            System.out.println(linea);
            String[] lineas = linea.split(" ");
            String robot = lineas[0];
            ip = lineas[1];
            System.out.println(" el robot es : " + robot + " y la ip es : " + ip);
         }

         this.dir = "192.168.0.100";
         this.dir = ip;
      } else {
         System.out.println("Entre al else");
         this.dir = "192.168.0.104";
         int puerto = 4000;
      }

      try (Socket s = new Socket(this.dir, 4000)) {
         System.out.println("conectados");
         DataOutputStream dOut = new DataOutputStream(s.getOutputStream());
         dOut.writeByte(this.getId());
         dOut.flush();
         dOut.close();
      }
   }

   public int getCiclos() {
      return this.ciclos;
   }

   public void setCiclos(int ciclos) {
      this.ciclos = ciclos;
   }

   public Color getColorById(int id) {
      switch(id) {
         case 0:
            return Color.RED;
         case 1:
            return Color.BLUE;
         case 2:
            return Color.MAGENTA;
         case 3:
            return Color.CYAN;
         case 4:
            return new Color(102, 66, 25);
         case 5:
            return Color.MAGENTA;
         default:
            return Color.BLACK;
      }
   }

   public void almacenarMensaje(String nombreDestino, String valor) throws Exception {
      Dato d = new Dato(valor, nombreDestino);
      int id = this.getCity().getRobotByNombre(nombreDestino).id;
      this.monitor.llegoMensaje(id, d);
   }

   public void recibirMensaje(Identificador nombreVariable, int id, Identificador NombreRobot) throws Exception {
      this.monitor.recibirMensaje(nombreVariable, id, NombreRobot);
   }

   public void Informar(String msj) {
      this.getCity().Informar(msj, this.id);
      this.esperarRefresco.esperar(this.id);
   }

   public void bloquearEsquina(int Av, int Ca) {
      this.esquinas.bloquear(Av, Ca);
      this.esperarRefresco.esperar(this.id);
      this.getCity().form.jsp.refresh();
   }

   public void liberarEsquina(int Av, int Ca) {
      this.esquinas.liberar(Av, Ca);
      this.esperarRefresco.esperar(this.id);
      this.getCity().form.jsp.refresh();
   }

   public Cuerpo getCuerpo() {
      return this.cueAST;
   }

   public void agregarArea(Area a) {
      this.areas.add(a);

      for(int i = a.getAv1(); i <= a.getAv2(); ++i) {
         for(int j = a.getCa1(); j <= a.getCa2(); ++j) {
            this.misCalles.add(new Coord(i, j));
         }
      }
   }

   public boolean esAreaVacia() {
      return this.areas.isEmpty();
   }

   public void crearMonitor(int cant) {
      this.monitor = MonitorMensajes.crearMonitorActualizarVentana(cant, this);
   }

   public void setCuerpo(Cuerpo cueAST) {
      this.cueAST = cueAST;
   }

   public DeclaracionVariable getVariables() {
      return this.varAST;
   }

   public void setVariables(DeclaracionVariable varAST) {
      this.varAST = varAST;
   }

   public int getFloresEnBolsaDeConfiguracion() {
      return this.floresEnBolsaDeConfiguracion;
   }

   public void setFloresEnBolsaDeConfiguracion(int floresEnBolsaDeConfiguracion) {
      this.floresEnBolsaDeConfiguracion = floresEnBolsaDeConfiguracion;
      this.setFlores(floresEnBolsaDeConfiguracion);
   }

   public int getPapelesEnBolsaDeConfiguracion() {
      return this.papelesEnBolsaDeConfiguracion;
   }

   public void setPapelesEnBolsaDeConfiguracion(int papelesEnBolsaDeConfiguracion) {
      this.papelesEnBolsaDeConfiguracion = papelesEnBolsaDeConfiguracion;
      this.setPapeles(papelesEnBolsaDeConfiguracion);
   }

   public void reset() {
      this.misCalles = new ArrayList<>();
      this.ruta = new ArrayList<>();
      this.rutas = new ArrayList<>();
      this.areas = new ArrayList<>();
      this.rutas.add(this.ruta);
      this.setFlores(this.getFloresEnBolsaDeConfiguracion());
      this.setPapeles(this.getPapelesEnBolsaDeConfiguracion());

      try {
         this.setAv(0);
         this.setCa(0);
      } catch (Exception var2) {
         Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, var2);
      }

      this.setDireccion(90);
   }

   public Image getImage() {
      switch(this.getDireccion()) {
         case 0:
            this.robotImage = new ImageIcon(this.getClass().getResource("/images/robotDerecha.png"));
            break;
         case 90:
            this.robotImage = new ImageIcon(this.getClass().getResource("/images/robotArriba.png"));
            break;
         case 180:
            this.robotImage = new ImageIcon(this.getClass().getResource("/images/robotIzquierda.png"));
            break;
         default:
            this.robotImage = new ImageIcon(this.getClass().getResource("/images/robotAbajo.png"));
      }

      return this.robotImage.getImage();
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public void iniciar(int x, int y) throws Exception {
      this.Pos(x, y);
      this.setNewX(x);
      this.setNewY(y);
      this.setFlores(this.getFlores());
      this.setPapeles(this.getPapeles());
      this.getCity().form.jsp.refresh();
   }

   public void choque(String nom, int id, int av, int ca) throws Exception {
      for(Robot r : this.getCity().robots) {
         if (r.id != id && r.Av == av && r.Ca == ca) {
            this.city.parseError(" Se produjo un choque entre el robot " + nom + " y el robot " + r.getNombre() + " en la avenida " + av + " y la calle " + ca);
            throw new Exception(" Se produjo un choque entre el robot " + nom + " y el robot " + r.getNombre() + " en la avenida " + av + " y la calle " + ca);
         }
      }
   }

   public void mover() throws Exception {
      int av = this.PosAv();
      int ca = this.PosCa();
      switch(this.getDireccion()) {
         case 0:
            ++av;
            break;
         case 90:
            ++ca;
            break;
         case 180:
            --av;
            break;
         case 270:
            --ca;
      }

      if (!this.puedeMover(av, ca, this.areas)) {
         this.city.parseError("No se puede ejecutar la instrucción \"mover\" debido a que no corresponde a un area asignada del robot");
         throw new Exception("No se puede ejecutar la instrucción \"mover\" debido a que no corresponde a un area asignada del robot");
      } else if (this.getCity().isFreePos(ca, av)) {
         this.addPos(av, ca);
         this.setFlores(this.getFlores());
         this.setPapeles(this.getPapeles());
         this.choque(this.nombre, this.id, this.Av, this.Ca);
         this.esperarRefresco.esperar(this.id);
         this.getCity().form.jsp.refresh();
      } else {
         this.city.parseError("No se puede ejecutar la instrucción \"mover\" debido a que hay un obstáculo");
         throw new Exception("No se puede ejecutar la instrucción \"mover\" debido a que hay un obstáculo");
      }
   }

   public boolean puedeMover(int av, int ca, ArrayList<Area> areas) {
      for(Coord c : this.misCalles) {
         if (c.getX() == av && c.getY() == ca) {
            return true;
         }
      }

      return false;
   }

   public int[] getXCoord() {
      int[] x = new int[this.ruta.size()];

      for(int c = 0; c < this.ruta.size(); ++c) {
         Coord p = this.ruta.get(c);
         x[c] = p.getX();
      }

      return x;
   }

   public int[] getYCoord() {
      int[] y = new int[this.ruta.size() + 1];

      for(int c = 0; c < this.ruta.size(); ++c) {
         Coord p = this.ruta.get(c);
         y[c] = p.getY();
      }

      return y;
   }

   public void addPos(int av, int ca) throws Exception {
      try {
         int old = this.city.ciudad[av][ca].getFlores();
         this.setAv(av);
         this.setCa(ca);
         this.pcs.firePropertyChange("esquinaFlores", old, this.city.ciudad[av][ca].getFlores());
      } catch (Exception var4) {
         throw new Exception(
            "Una de las nuevas coordenadas cae fuera de la ciudad.Av: "
               + av
               + " Ca: "
               + ca
               + " Calles: "
               + this.city.getNumCa()
               + " Avenidas: "
               + this.city.getNumAv()
         );
      }
   }

   public void setAv(int av) throws Exception {
      if (av > this.city.getNumAv()) {
         throw new Exception();
      } else {
         if (av != this.PosAv()) {
            this.ruta.add(new Coord(av, this.PosCa()));
            if (av > this.PosAv()) {
               this.setDireccion(0);
            } else {
               this.setDireccion(180);
            }
         }

         this.setNewX(av);
         this.setNewY(this.Ca);
      }
   }

   public void setNewX(int av) {
      int old = this.PosAv();
      this.Av = av;
      this.pcs.firePropertyChange("av", old, av);
   }

   public void setNewY(int ca) {
      int old = this.PosCa();
      this.Ca = ca;
      this.pcs.firePropertyChange("ca", old, ca);
   }

   public void setCa(int ca) throws Exception {
      if (ca > this.city.getNumCa()) {
         throw new Exception();
      } else {
         if (ca != this.PosCa()) {
            this.ruta.add(new Coord(this.PosAv(), ca));
            if (ca < this.PosCa()) {
               this.setDireccion(270);
            } else {
               this.setDireccion(90);
            }
         }

         this.setNewY(ca);
         this.setNewX(this.Av);
      }
   }

   public void setDireccion(int direccion) {
      int old = this.direccion;
      this.direccion = direccion;
      this.pcs.firePropertyChange("direccion", old, direccion);
   }

   public void setEstado(String str) {
      String s = this.getEstado();
      this.estado = str;
      this.pcs.firePropertyChange("estado", s, str);
   }

   public String getEstado() {
      return this.estado;
   }

   public int getDireccion() {
      return this.direccion;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
   }

   public void mirarEnDireccion(int direccion) throws Exception {
      int c;
      for(c = 0; c < 5 && this.getDireccion() != direccion; ++c) {
         this.derecha();
      }

      if (c == 5) {
         throw new Exception("La dirección especificada no corresponde.");
      }
   }

   public void derecha() {
      switch(this.getDireccion()) {
         case 0:
            this.setDireccion(270);
            break;
         case 90:
            this.setDireccion(0);
            break;
         case 180:
            this.setDireccion(90);
            break;
         case 270:
            this.setDireccion(180);
      }

      this.esperarRefresco.esperar(this.id);
      this.getCity().form.jsp.refresh();
   }

   public int PosCa() {
      return this.Ca;
   }

   public int PosAv() {
      return this.Av;
   }

   public void Pos(int Av, int Ca) throws Exception {
      if (!this.puedeMover(Av, Ca, this.areas)) {
         this.city.parseError("No se puede ejecutar la instrucción \"Pos\" debido a que no corresponde a un area asignada del robot");
         throw new Exception("No se puede ejecutar la instrucción \"Pos\" debido a que no corresponde a un area asignada del robot");
      } else if (this.getCity().isFreePos(Ca, Av)) {
         this.getRutas().add(this.getRuta());
         this.setRuta(new ArrayList<>());
         this.getRuta().add(new Coord(Av, Ca));
         this.setNewX(Av);
         this.setNewY(Ca);
         this.choque(this.nombre, this.id, Av, Ca);
         this.esperarRefresco.esperar(this.id);
         this.getCity().form.jsp.refresh();
      } else {
         this.city.parseError("No se puede ejecutar la instrucción \"Pos\" debido a que hay un obstáculo");
         throw new Exception("No se puede ejecutar la instrucción \"Pos\" debido a que hay un obstáculo");
      }
   }

   public ArrayList<Coord> getRuta() {
      return this.ruta;
   }

   public Ciudad getCity() {
      return this.city;
   }

   public void tomarFlor() throws Exception {
      if (this.getCity().levantarFlor(this.PosAv(), this.PosCa())) {
         this.setFlores(this.getFlores() + 1);
         this.esperarRefresco.esperar(this.id);
      } else {
         this.setFlores(this.getFlores());
         throw new Exception("No se puede ejecutar la instrucción \"tomarFlor\" debido a que no hay ninguna flor en la esquina");
      }
   }

   public int getFlores() {
      return this.floresEnBolsa;
   }

   public void setFlores(int flores) {
      int old = this.getFlores();
      this.floresEnBolsa = flores;
      this.pcs.firePropertyChange("flores", old, flores);
   }

   public void tomarPapel() throws Exception {
      if (this.getCity().levantarPapel(this.PosAv(), this.PosCa())) {
         this.setPapeles(this.getPapeles() + 1);
         this.esperarRefresco.esperar(this.id);
      } else {
         this.setPapeles(this.getPapeles());
         throw new Exception("No se puede ejecutar la instrucción \"tomarPapel\" debido a que no hay ningun papel en la esquina");
      }
   }

   public boolean HayPapelEnLaBolsa() {
      return this.getPapeles() > 0;
   }

   public boolean HayFlorEnLaBolsa() {
      return this.getFlores() > 0;
   }

   public int getPapeles() {
      return this.papelesEnBolsa;
   }

   public void setColor(Color col) {
      Color old = this.color;
      this.color = col;
      this.pcs.firePropertyChange("color", old, col);
   }

   public Color getColor() {
      return this.color;
   }

   public void setPapeles(int papeles) {
      int old = this.getPapeles();
      this.papelesEnBolsa = papeles;
      this.pcs.firePropertyChange("papeles", old, papeles);
   }

   public void depositarPapel() throws Exception {
      if (this.getPapeles() > 0) {
         this.setPapeles(this.getPapeles() - 1);
         this.getCity().dejarPapel(this.PosAv(), this.PosCa());
         this.esperarRefresco.esperar(this.id);
      } else {
         this.city.parseError("No se puede ejecutar la instrucción \"depositarPapel\" debido a que no hay ningun papel en la bolsa");
         throw new Exception("No se puede ejecutar la instrucción \"depositarPapel\" debido a que no hay ningun papel en la bolsa");
      }
   }

   public void depositarFlor() throws Exception {
      if (this.getFlores() > 0) {
         this.setFlores(this.getFlores() - 1);
         this.getCity().dejarFlor(this.PosAv(), this.PosCa());
         this.esperarRefresco.esperar(this.id);
      } else {
         this.city.parseError("No se puede ejecutar la instrucción \"depositarFlor\" debido a que no hay ninguna en la bolsa de flores");
         throw new Exception("No se puede ejecutar la instrucción \"depositarFlor\" debido a que no hay ninguna en la bolsa de flores");
      }
   }

   public ArrayList<ArrayList<Coord>> getRutas() {
      return this.rutas;
   }

   public void setRuta(ArrayList<Coord> ruta) {
      this.ruta = ruta;
   }

   public void setRutas(ArrayList<ArrayList<Coord>> rutas) {
      this.rutas = rutas;
   }

   public DeclaracionProcesos getProcAST() {
      return this.procAST;
   }

   public void setProcAST(DeclaracionProcesos procAST) throws CloneNotSupportedException {
      synchronized(this) {
         ArrayList<Proceso> ps = new ArrayList<>();

         for(Proceso j : procAST.getProcesos()) {
            DeclaracionVariable ddvv = j.getDV();
            Identificador I = new Identificador(j.getI().toString());
            ArrayList<ParametroFormal> pfs = new ArrayList<>();

            for(ParametroFormal pformal : j.getPF()) {
               Identificador In = new Identificador(pformal.getI().toString());
               ParametroFormal pf = new ParametroFormal(In, pformal.getT(), pformal.getTA());
               pfs.add(pf);
            }

            ArrayList<Sentencia> ss = new ArrayList<>();

            for(Sentencia sen : j.getC().getS()) {
               System.out.println("Sentencia : " + sen.toString());
               Sentencia s = (Sentencia)sen.clone();
               ss.add(s);
            }

            ArrayList<Variable> dvs = new ArrayList<>();

            for(Variable v : ddvv.variables) {
               Variable V = (Variable)v.clone();
               dvs.add(V);
            }

            DeclaracionVariable ddvs = new DeclaracionVariable(dvs);
            Cuerpo cue = new Cuerpo(ss, ddvs);
            Proceso p = new Proceso(I, pfs, procAST, ddvs, cue);
            ps.add(p);
         }

         DeclaracionProcesos dp = new DeclaracionProcesos(ps);
         this.procAST = dp;
      }
   }

   public DeclaracionVariable getVarAST() {
      return this.varAST;
   }

   public void setVarAST(DeclaracionVariable varAST) {
      this.varAST = varAST;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
