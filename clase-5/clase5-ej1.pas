// 1.- El administrador de un edificio de oficinas, cuenta en papel, con la información del
// pago de las expensas de dichas oficinas.
// Implementar un programa que:
// a) Genere un vector, sin orden, con a lo sumo las 300 oficinas que administra.
// De cada oficina se ingresa código de identificación, DNI del propietario y valor
// de la expensa. La lectura finaliza cuando llega el código de identificación -1.
// b) Ordene el vector, aplicando uno de los métodos vistos en la cursada, para
// obtener el vector ordenado por código de identificación de la oficina.
// c) Realice una búsqueda dicotómica que recibe el vector generado en b) y un
// código de identificación de oficina y retorne si dicho código está en el vector.
// En el caso de encontrarlo, se debe informar el DNI del propietario y en caso
// contrario informar que el código buscado no existe.
// d) Tenga un módulo recursivo que retorne el monto total de las expensas.

program ej1;
const
    cantidadOficinas = 300;
type
    expensa = record
        codigo: integer;
        dni: integer;
        valor: real;
    end;

    vectorExpensas = array[1..cantidadOficinas] of expensa;


procedure generarVector(var v: vectorExpensas);
    procedure leerExpensa(var e: expensa);
    begin
        write('Ingrese codigo de identificacion: ');
        readln(e.codigo);
        if (e.codigo <> -1) then begin
            write('Ingrese dni del propietario: ');
            readln(e.dni);
            write('Ingrese valor de la expensa: ');
            readln(e.valor);
        end;
    end;
var
    e: expensa;
    i: integer;
begin
    i := 0;
    leerExpensa(e);
    while (e.codigo <> -1) do begin
        i := i + 1;
        v[i] := e;
        leerExpensa(e);
    end;
end;

procedure ordenarVector(var v: vectorExpensas);
var

begin

end;

var
    expensas: vectorExpensas;
begin
    generarVector(expensas);
    ordenarVector(expensas);

end.