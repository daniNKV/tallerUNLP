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
type

    rango = 0..nMax;

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
        nuevo^.dato := n;
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

var
    pLista: lista;
    max: rango;
begin
    Randomize;
    max := 0;
   
    pLista := nil;
    generarLista(pLista);
    buscarMaximo(pLista, max);


end.