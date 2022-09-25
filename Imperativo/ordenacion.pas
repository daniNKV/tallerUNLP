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
        act := v[i]; // establezco actual
        j := i - 1; // posiciono en el anterior al actual

        while (v[j] > act) and (j <> 0) do begin // Recorro hacia atras buscando un menor
            v[j + 1] := v[j]; // como esta ordenado desplazo hacia la derecha todos los mayores al actual
            j := j - 1; // *
        end;
        // Escribo el actual en el lugar correcto
        v[j + 1] := act; // El ultimo minimo hizo bajar a j en 1;

end;
