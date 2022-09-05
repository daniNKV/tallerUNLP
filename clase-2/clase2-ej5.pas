// 5.- Implementar un módulo que realice una búsqueda dicotómica en un vector, utilizando el
// siguiente encabezado:
// Procedure busquedaDicotomica (v: vector; ini,fin: indice; dato:integer; var pos: indice);

// Nota: El parámetro “pos” debe retornar la posición del dato o -1 si el dato no se encuentra
// en el vector.

program ej5;

const

type


Procedure busquedaDicotomica (v: vector; ini,fin: indice; dato:integer; var pos: indice);
var
    elementoMedio: indice;

begin
    elementoMedio := fin DIV 2;
    if (v[elementoMedio] <> dato) then begin
        if dato > v[elementoMedio] then
            busquedaDicotomica(v, elementoMedio, fin, dato, pos);
        else
            busquedaDicotomica(v, ini, elementoMedio, dato, pos);
    end
    else 
        pos := fin DIV 2;

end;



var
begin

end.