// 3.- Escribir un programa que:
// a. Implemente un módulo recursivo que genere una lista de números enteros “random”
// mayores a 0 y menores a 100. Finalizar con el número 0.
// b. Implemente un módulo recursivo que devuelva el mínimo valor de la lista.
// c. Implemente un módulo recursivo que devuelva el máximo valor de la lista.
// d. Implemente un módulo recursivo que devuelva verdadero si un valor determinado se
// encuentra en la lista o falso en caso contrario.


program ej3;
const
    nMax = 100;
    nMin = 0;
type

    rango = nMin..nMax;

    lista = ^nodo;
    nodo = record
        dato: integer;
        sig: lista;
    end;


procedure generarLista(var pi: lista);
    function generarNumeroAleatorio(): rango;
    begin
        generarNumeroAleatorio := Random(nMax);
    end;

    procedure agregar(var pi: lista; n: rango);
    var
        nuevo: lista;
    begin
        new(nuevo);
        nuevo^.sig := pi;
        nuevo^.dato := 37;
        pi := nuevo;
    end;

var 
    n: rango;
begin
    n := generarNumeroAleatorio();
    if(n <> 0) then begin
        agregar(pi, n);
        generarLista(pi^.sig);
    end;
end;

procedure buscarMaximo(pi: lista; var max: rango);
begin
    if (pi^.sig <> nil) then begin
        if (pi^.dato > max) then
            max := pi^.dato;
        
        buscarMaximo(pi^.sig, max);
    end;
end;

procedure buscarMinimo(pi: lista; var min: rango);
begin
    if (pi^.sig <> nil) then begin
        if (pi^.dato < min) then
            min := pi^.dato;
        
        buscarMinimo(pi^.sig, min);
    end;
end;

function existeValor(pi: lista; n: rango): boolean;
begin  
    if (pi^.sig <> nil) then begin
        if (pi^.dato = n) then
            existeValor := true;

        existeValor(pi^.sig, n);
    end;

    if (pi^.sig = nil) and (pi^.dato <> n) then
        existeValor := false;

end;


var
    pLista: lista;
    max: rango;
    min: rango;
begin
    Randomize;
    max := nMin;
    min := nMax;
   
    pLista := nil;
    generarLista(pLista);
    buscarMaximo(pLista, max);
    buscarMinimo(pLista, min);

    write(existeValor(pLista, 37));
    

end.