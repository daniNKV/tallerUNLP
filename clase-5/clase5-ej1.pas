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
    const ini = 1;
type
    indice = -1 .. cantidadOficinas;
    expensa = record
        codigo: integer;
        dni: integer;
        valor: real;
    end;

    vectorExpensas = array[1..cantidadOficinas] of expensa;


procedure generarVector(var v: vectorExpensas; var dimL: integer);
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

    dimL := i;
    
end;

procedure ordenarVector(var v: vectorExpensas; dimL: integer);
var
    i, j, min: integer;
    aux: expensa;
begin

    for i := 1 to (dimL - 1) do begin
        min := i;
        for j := (i + 1) to dimL do 
            if (v[i].codigo < v[j].codigo) then
                min := j;
        
        aux := v[min];
        v[min] := v[i];
        v[i] := aux;
    end;

end;

procedure buscarCodigo(var expensas: vectorExpensas, cantExpensas: indice); // expensas por ref por rendimiento
    function indiceBuscado(var v: vectorExpensas; cod: integer; ini, fin: indice): indice;
    begin
        itemMedio := fin DIV 2;
        if (v[itemMedio].codigo <> cod) then
            if (cod < v[itemMedio].codigo) then
                indiceBuscado := indiceBuscado(v, cod, ini, fin DIV 2)
            else
                indiceBuscado := indiceBuscado(v, cod, itemMedio, fin)
        else
            if (v[itemMedio].codigo = cod) then
                indiceBuscado := v[itemMedio];
            else 
                indiceBuscado := -1;
    end;

var
    codigoBuscado: integer;
    indiceBuscado: integer;
    expensaBuscada: expensa;
begin
    write('Ingrese el código que desea buscar: ');
    readln(codigoBuscado);

    indiceBuscado := indiceCodigoBuscado(expensas, codigoBuscado, ini, cantExpensas) );
    if indiceBuscado = -1 then
        writeln('No existe registro del código ingresado')
    else 
        writeln('El dni del codigo buscado es: ', expensas[indiceBuscado].dni);
end;

procedure informarMontoTotal(var e: vectorExpensas; dimL: indice);
    function montoTotal(var e: expensas; dimL: indice): real;
    begin
        if (dimL <> 0) do
            montoTotal := e[dimL].valor + montoTotal(e, (dimL-1))
        else
            montoTotal := 0;
    end;
var
    montoRecaudado: real;
begin
    writeln('El monto recaudado es: ', montoTotal(e, dimL));
end;

var
    expensas: vectorExpensas;
    cantExpensas: integer;
begin
    generarVector(expensas, cantExpensas);
    ordenarVector(expensas, cantExpensas);
    buscarCodigo(expensas, cantExpensas);
    calcularMontoTotal(expensas, cantExpensas);

end.