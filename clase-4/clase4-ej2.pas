// 2. Un cine posee la lista de películas que proyectará durante el mes de octubre. De cada
// película se conoce: código de película, código de género (1: acción, 2: aventura, 3: drama, 4:
// suspenso, 5: comedia, 6: bélica, 7: documental y 8: terror) y puntaje promedio otorgado por
// las críticas. Implementar un programa que contenga:
// a. Un módulo que lea los datos de películas y los almacene ordenados por código de película y
// agrupados por código de género, en una estructura de datos adecuada. La lectura finaliza
// cuando se lee el código de película -1.
// b. Un módulo que reciba la estructura generada en el punto a y retorne una estructura de
// datos donde estén todas las películas almacenadas ordenadas por código de película.

program ej2;
const
    cantidadGeneros = 8;
    puntajeMaximo = 10;
    puntajeMinimo = 0;

type
    rangoPuntaje = puntajeMinimo .. puntajeMaximo;
    rangoGeneros = 1 .. cantidadGeneros;

    pelicula = record
        codigo: integer;
        genero: rangoGeneros;
        puntaje: rangoPuntaje;
    end;

    listaPeliculas = ^nodoPelicula;
    
    nodoPelicula = record
        dato: pelicula;
        sig: listaPeliculas;
    end;

    vectorGeneros = array[rangoGeneros] of listaPeliculas;



procedure generarVectorDeListas(var v: vectorGeneros);
    procedure leerPelicula(var p: pelicula);
    begin
        write('Ingrese codigo de pelicula: ');
        readln(p.codigo);
        if (p.codigo <> -1) then begin
            write('Ingrese genero de pelicula: ');
            readln(p.genero);
            write('Ingrese puntaje dado por la crítica: ');
            readln(p.puntaje);
        end;
    end;

    procedure iniciarListas(var v: vectorGeneros);
    var
        i: integer;
    begin
        for i := 1 to cantidadGeneros do
            v[i] := nil;
    end;

    procedure agregarPelicula(var v:vectorGeneros; p: pelicula);
        procedure insertarOrdenado(var pi: listaPeliculas; p: pelicula);
        var
            act: listaPeliculas;
            puntero: listaPeliculas;
            nuevoNodo: listaPeliculas; 
        begin
            puntero := pi;
            new(nuevoNodo);
            nuevoNodo^.dato := p;
            nuevoNodo^.sig := nil;

            if (pi = nil) then
                pi := nuevoNodo
            else
                while (p.codigo > puntero^.dato.codigo) and (puntero <> nil) do begin
                    act := puntero;
                    puntero := puntero^.sig;
                end;
                
                nuevoNodo^.sig := act^.sig;
                act^.sig := nuevoNodo;
            
        end;
    var
        punteroGenero: listaPeliculas;
    begin
        punteroGenero := v[p.codigo];

        insertarOrdenado(punteroGenero, p);


    end;
var
    peliculaNueva: pelicula;
    
begin
    iniciarListas(v);

    leerPelicula(peliculaNueva);
    while (peliculaNueva.codigo <> -1) do begin
        agregarPelicula(v, peliculaNueva);
        leerPelicula(peliculaNueva);
    end;
end;

procedure mergeListasGeneros(v: vectorGeneros; var pi: listaPeliculas);
    procedure buscarMinimo(var v: vectorGeneros; var pMin: pelicula; var min: integer);
    var
        i: integer;
        indiceMinimo: rangoGeneros;
    begin
        min := -1;
        for i := 1 to cantidadGeneros do 
            if (v[i] <> nil) and (v[i]^.dato.codigo < min) then begin
                min := v[i]^.dato.codigo;
                indiceMinimo := i;
            end;
        
        pMin := v[indiceMinimo]^.dato;
        v[indiceMinimo] := v[indiceMinimo]^.sig;
    
    end;

    procedure agregarAtras(var pi: listaPeliculas; var pf: listaPeliculas; p: pelicula);
    var
        nuevoNodo: listaPeliculas;
    begin
        new(nuevoNodo);
        nuevoNodo^.dato := p;

        if (pi = pf) then begin
            if (pi = nil) then begin
                pi := nuevoNodo;
                pf := nuevoNodo;
            end
            else
                pi^.sig := pf;
                pf := nuevoNodo;
        end
        else
            pf^.sig := nuevoNodo;
            pf := nuevoNodo;

    end;
var
    pMin: pelicula;
    min: integer;
    pf: listaPeliculas;
begin
    pi := nil;
    pf := nil;

    buscarMinimo(v, pMin, min);
    while (min <> -1) do begin
        agregarAtras(pi, pf, pMin);
        buscarMinimo(v, pMin, min);
    end;
    
end;


var
    peliculasPorGenero: vectorGeneros;
    todasLasPeliculas: listaPeliculas;
begin
    generarVectorDeListas(peliculasPorGenero);

    mergeListasGeneros(peliculasPorGenero, todasLasPeliculas);
end.