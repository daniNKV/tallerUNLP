// 4. Un teatro tiene funciones los 7 días de la semana. Para cada día se tiene una lista con las
// entradas vendidas. Se desea procesar la información de una semana. Implementar un
// programa que:
// a. Genere 7 listas con las entradas vendidas para cada día. De cada entrada se lee día (de 1 a
// 7), código de la obra, asiento y monto. La lectura finaliza con el código de obra igual a 0. Las
// listas deben estar ordenadas por código de obra de forma ascendente.
// b. Genere una nueva lista que totalice la cantidad de entradas vendidas por obra. Esta lista
// debe estar ordenada por código de obra de forma ascendente.

program ej4;
const diasSemana = 7;

type
    entrada = record
        dia: rangoDias;
        codigoObra: integer;
        asiento: integer;
        monto: real;
    end;
    
    listaEntradasVendidas = ^nodoEntradas;
    
    nodoEntradas = record
        dato: entrada;
        sig: listaEntradasVendidas;
    end;

    entradasVendidasObra = record
        codigoObra: integer;
        cantidadVendida: integer
    end;

    listaTotalObra = ^nodoTotal;

    nodoTotal = record;
        dato: entradasVendidasObra;
        sig: listaTotalObra;
    end;

    rangoDias = 0 .. diasSemana;record
record
record
record
    
    vectorFunciones = array[rangoDias] of listaEntradasVendidas;

procedure generarListaEntradasVendidas(var pi: listaEntradasVendidas);
    procedure iniciarListas(var v: vectorFunciones);
    var i: integer;
    begin
        for i := 1 to diasSemana do begin
            v[i] := nil;
    end;
    procedure leerEntrada(var e: entrada);
    begin
        write('Ingrese codigo de obra de la funcion: ');
        readln(e.codigoObra);
        if(e.codigoObra <> -1) then begin
            write('Ingrese dia de la funcion: ');
            readln(e.dia);
            write('Ingrese asiento: ');
            readln(e.asiento);
            write('Ingrese monto abonado: ');
            readln(e.monto);
        end;
    end;
    procedure insertarOrdenado(var pi: listaEntradasVendidas; e: entrada);
    var
        nuevo: listaEntradasVendidas;
        act, ant: listaEntradasVendidas;
    begin
        new(nuevo);
        nuevo^.dato := e;
        nuevo^.sig := nil;
        
        act := pi;

        while (act <> nil) do begin
            ant := act;
            act := act^.sig;
        end;

        if (act = nil) then
            pi := nuevo;
        else
            ant^.sig := nuevo;
            nuevo^.sig := act;
    end;

var
    entradaLeida: entrada;

begin
    pi := niL;

    leerEntrada(entradaLeida);

    while (entradaLeida.codigoObra <> 0) do begin
        insertarOrdenado(pi, entradaLeida);
        leerEntrada(entradaLeida);
    
end;

procedure generarListaTotalVentasPorFuncion(vectorDias: vectorFunciones; var pi: listaTotalObra);
    procedure insertarAtras(var pi, pf: listaTotalObra; obra: entradasVendidasObra);
    var
        nuevo: listaTotalObra;
    begin
        new(nuevo);
        nuevo^.sig := nil;
        nuevo^.dato := obra;

        if (pi = nil) then begin
            pi := nuevo;
            pf := nuevo;
        end;
        else
            pf^.sig := nuevo;
            pf := nuevo;
    end;
    procedure buscarMinimo(v: vectorDias; oMin: entradasVendidasObra);
    var
        i: integer;
        iMin: integer;
    begin   
        oMin.codigoObra := 9999;

        for i := 1 to diasSemana do begin
            if (v[i] <> nil) then
                if (oMin.codigoObra < v[i]^.dato.codigoObra) then begin
                    oMin.codigoObra := v[i]^.dato.codigoObra;
                    oMin.cantidadVendida := v[i]^.dato.cantidadVendida;
                    iMin := i;
            end;
        end;

        if (oMin.codigoObra <> 9999) then
            v[iMin] := v[iMin]^.sig


    end;


var
    pf: listaTotalObra;
    obraMin: entradasVendidasObra;
    obraActual: entradasVendidasObra;
begin
    pi := nil;
    pf := nil;

    buscarMinimo(vectorDias, obraMin);

    while (obraMin.codigoObra <> 9999) do begin
        obraActual.codigoObra := obraMin.codigoObra;
        obraActual.cantidadVendida := 0; 
        
        while (obraMin.codigoObra <> 9999) and (obraMin.codigoObra = obraActual.codigoObra) do begin
            obraActual.cantidadVendida := obraActual + obraMin.cantidadVendida;
            buscarMinimo(vectorDias, obraMin);
        end;

        insertarAtras(pi, pf, obraActual);
    end; 
end;


var
    listasEntradasVendidasPorDia: vectorFunciones;
    listaTotalObras: listaTotalObra;
begin
    generarListaEntradasVendidas(listasEntradasVendidasPorDia);
    generarListaTotalVentasPorFuncion(listasEntradasVendidasPorDia, listaTotalObras);

end.