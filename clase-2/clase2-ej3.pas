// 4.- Escribir un programa que:
// a. Implemente un módulo recursivo que genere un vector de 20 números enteros “random”
// mayores a 0 y menores a 100.
// b. Implemente un módulo recursivo que devuelva el máximo valor del vector.
// c. Implementar un módulo recursivo que devuelva la suma de los valores contenidos en el
// vector.


program ej3;
const
    dimF = 20;
    nMax = 100;
type

    rango = 0..100;
    dimVector = 0 .. dimF;
    vector = array[dimVector] of rango;

procedure generarVector(var v: vector; var dimL: dimVector);
    function generarNumero(): rango;
    begin
        generarNumero := Random(nMax);
    end;
begin
    if (dimL < dimF) then begin
        dimL := dimL + 1;
        v[dimL] := generarNumero();
        generarVector(v, dimL);
    end;
end;

procedure buscarMaximo(v: vector; dimL: dimVector; var max: rango);
begin
    if (dimL > 0) then begin
        if (v[dimL] > max) then
            max := v[dimL];
        
        buscarMaximo(v, dimL - 1, max);
    end;
end;

var
    v: vector;
    dimL: dimVector;
    max: rango;

begin
    Randomize;
    dimL := 0;
    max := 0;
    generarVector(v, dimL);
    buscarMaximo(v, dimL, max);
end.