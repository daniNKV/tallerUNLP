procedure ordenarSeleccion(var v: vector; dimL: integer);
var
    aux, p, i, j, k: integer;
begin

    for i := 1 to (dimL-1) do begin // PARA TODOS LOS ELEMENTOS MENOS EL ULTIMO (porque compara con el siguiente)

        p := i;  // busca después del ultimo elemento iterado

        for j := i+1 to dimL do // BUSCO UN MENOR DESDE EL SIGUIENTE HASTA EL ULTIMO
            if v[j] < v[p] then
                p := j;
    end;

        aux := v[p]; // SWAP
        v[p] := v[i];
        v[i] := aux;

    end;
end;

procedure ordernarInsercion(var v: vector; dimL: integer);
var 
    act, i, j: integer;

begin
    for i := 2 to dimL do begin
        act := v[i];
        j := i - 1;
        while(j > 0) and (v[j] > act) do begin
            v[j + 1] := v[j];
            j := j - 1;
        end;
        v[j+1] := act;
end;
