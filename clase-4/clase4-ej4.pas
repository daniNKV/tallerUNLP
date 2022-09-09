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

    rangoDias = 0 .. diasSemana;
    
    vectorFunciones = array[rangoDias] of listaEntradasVendidas;

procedure generarListaEntradasVendidas(var pi: listaEntradasVendidas);
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


begin

end;


var
begin

end.