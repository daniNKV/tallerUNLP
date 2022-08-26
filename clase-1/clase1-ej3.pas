program clase1Ejercicio3;
const
    cantidadGeneros = 8;
    puntajeMaximo = 10;
    puntajeMinimo = 0;

type  
    puntajesTipo = puntajeMinimo .. puntajeMaximo;
    generosTipo = 1 .. cantidadGeneros;
    listaPeliculas = ^nodo;
    pelicula = record
        codigoPelicula: integer;
        codigoGenero: generosTipo;
        puntajeCriticas: puntajesTipo;
    end;
    nodo = record
        dato: pelicula;
        sig: listaPeliculas;
    end;
    peliculasTop = array[generosTipo] of pelicula;

procedure agregarPelicula(var pi: listaPeliculas; var pf: listaPeliculas; p: pelicula);
var
    nuevaPelicula: listaPeliculas;
begin
    new(nuevaPelicula);
    nuevaPelicula^.sig := nil;
    nuevaPelicula^.dato := p;
    if pf <> pi then 
        pf^.sig := nuevaPelicula
    else 
        pi := nuevaPelicula;
end;

procedure leerPelicula(var p: pelicula);
begin
    write('Ingrese código de película: ');
    readln(p.codigoPelicula);
    if p.codigoPelicula <> -1 then begin
        write('Ingrese código de género: ');
        readln(p.codigoGenero);
        write('Ingrese puntaje promedio de las críticas: ');
        readln(p.puntajeCriticas);
    end;
end;

procedure leerPeliculas(var pi: listaPeliculas; var pf: listaPeliculas);
var
    peliculaLeida: pelicula;

begin
    leerPelicula(peliculaLeida);
    while(peliculaLeida.codigoPelicula <> -1) do begin
        agregarPelicula(pi, pf, peliculaLeida);
        leerPelicula(peliculaLeida);
    end;  
end;

procedure iniciarVector(var v: peliculasTop);
var
    i: generosTipo;
begin
    for i := 1 to cantidadGeneros do 
        v[i].puntajeCriticas := 0;
end;

procedure generarTopGenero(var topPorGenero: peliculasTop; pi: listaPeliculas);
begin
    iniciarVector(topPorGenero);
    while (pi^.sig <> nil) do begin
        if (pi^.dato.puntajeCriticas > topPorGenero[pi^.dato.codigoGenero].puntajeCriticas) then begin
            topPorGenero[pi^.dato.codigoGenero].codigoPelicula := pi^.dato.codigoPelicula;
            topPorGenero[pi^.dato.codigoGenero].puntajeCriticas := pi^.dato.puntajeCriticas;
        end;
        pi := pi^.sig;
    end;
end;

procedure ordenarPorPuntaje(var v: peliculasTop);
var
    aux, p, i, j: integer;
begin
    for i := 1 to cantidadGeneros - 1 do begin
        p := i; 

        for j := i + 1 to cantidadGeneros do begin
            if v[j].puntajeCriticas < v[p].puntajeCriticas then
                p := j;
        end;

        aux := v[p].puntajeCriticas;
        v[p].puntajeCriticas := v[i].puntajeCriticas;
        v[i].puntajeCriticas := aux;

    end;
end;


procedure mostrarMaximosYMinimos(v: peliculasTop);
begin
    writeln('El mayor puntaje de la crítica lo tiene la película: ', v[cantidadGeneros].codigoPelicula);
    writeln('El menor puntaje de la crítica lo tiene la pelicula: ', v[1].codigoPelicula);
end;

var
    pi, pf: listaPeliculas;
    vectorTopPeliculas: peliculasTop;

begin
    pi := nil;
    pf := pi;

    leerPeliculas(pi, pf);
    generarTopGenero(vectorTopPeliculas, pi);
    ordenarPorPuntaje(vectorTopPeliculas);
    mostrarMaximosYMinimos(vectorTopPeliculas);

end.





