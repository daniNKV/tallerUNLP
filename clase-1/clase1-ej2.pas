program clase1Ejercicio2;

const
    cantidadOficinas = 300;

type
    rangoOficinas = 0 .. cantidadOficinas;
    oficina = record
        codigo: integer;
        dniPropietario: integer;
        valorExpensa: real;
    end;
    vectorOficinas = array[rangoOficinas] of oficina;


procedure leerOficina(var o: oficina);
begin
    write('Ingrese codigo de Identificación de la oficina: ');
    readln(o.codigo);
    if (o.codigo <> -1) then begin
        write('Ingrese DNI del propietario: ');
        readln(o.dniPropietario);
        write('Ingrese valor de la expensa: ');
        readln(o.valorExpensa);
    end;
end;
procedure insertarOficina(var v:vectorOficinas; var dimL: rangoOficinas; o: oficina);
begin
    dimL := dimL + 1;
    v[dimL] := o;
end;

procedure ingresarOficinas(var v:vectorOficinas; var dimL: rangoOficinas);
var
    ingresoOficina: oficina;
begin
    leerOficina(ingresoOficina);
    while (ingresoOficina.codigo <> -1) do begin
        insertarOficina(v, dimL ,ingresoOficina);
        leerOficina(ingresoOficina);
    end;
end;


// VERSION PROPIA
// procedure ordenarSeleccion(var v: vectorOficinas; dimL: rangoOficinas);
// var
//     min, pos, aux, i, j, k: integer;
// begin
//     for i := 1 to (dimL-1) do begin
//         min := v[i]
//         for j := i+1 to dimL do begin
//             if v[j] < min then
//                 min := v[j];
//                 pos := j;
//         end;
//         aux := v[i];
//         v[i] := min;
//         v[pos] := aux;
//     end;
// end;


// VERSION CATEDRA;
procedure ordenarSeleccion(var v: vectorOficinas; dimL: rangoOficinas);
var
    aux, p, i, j: integer;
begin
    for i := 1 to (dimL-1) do begin
        p := i; 

        for j := i+1 to dimL do begin
            if v[j].codigo < v[p].codigo then
                p := j;
        end;

        aux := v[p].codigo;
        v[p].codigo := v[i].codigo;
        v[i].codigo := aux;

    end;
end;

procedure ordernarInsercion(var v:vectorOficinas; dimL: rangoOficinas);
var 
    act, i, j: integer;

begin
    for i := 2 to dimL do begin
        act := v[i].codigo;
        j := i - 1;
        while(j > 0) and (v[j].codigo > act) do begin
            v[j + 1].codigo := v[j].codigo;
            j := j - 1;
        end;
        v[j+1].codigo := act;
    end;
end;

var
    oficinas: vectorOficinas;
    oficinasOcupadas: rangoOficinas;

begin
    oficinasOcupadas := 0;
    ingresarOficinas(oficinas, oficinasOcupadas);

end.