
// 2.- Una agencia dedicada a la venta de autos ha organizado su stock y, dispone en papel
// de la información de los autos en venta.
// Implementar un programa que:
// a) Genere un árbol binario de búsqueda ordenado por patente identificatoria del
// auto en venta. Cada nodo del árbol debe contener patente, año de
// fabricación (2010..2018), la marca y el modelo.
// b) Invoque a un módulo que reciba el árbol generado en a) y una marca y
// retorne la cantidad de autos de dicha marca que posee la agencia. Mostrar
// el resultado.
// c) Invoque a un módulo que reciba el árbol generado en a) y retorne una
// estructura con la información de los autos agrupados por año de fabricación.
// d) Contenga un módulo que reciba el árbol generado en a) y una patente y
// devuelva el año de fabricación del auto con dicha patente. Mostrar el
// resultado.

program ej2;

type
    rangoAntiguedad = 2010 .. 2018;

    arbolStock = ^nodoArbol;
    listaStockPorAnio = ^nodoLista;

    tipoAuto = record
        patente: integer;
        fabricacion: integer;
        marca: string;
        modelo: string
    end;

    nodoArbol = record
        dato: tipoAuto;
        hi: arbolStock;
        hd: arbolStock;
    end;

    nodoLista = record
        dato: tipoAuto;
        sig: listaStockPorAnio;
    end;

    vectorStockPorAnio = array[rangoAntiguedad] of listaStockPorAnio;



procedure generarArbol(var a: arbolStock);
    procedure leerAuto(var a: tipoAuto);
    begin
        write('Ingrese patente identificatoria: ');
        readln(a.patente);
        if (a.patente <> -1) then begin
            write('Ingrese año de fabricacion: ');
            readln(a.fabricacion);
            write('Ingrese marca del auto: ');
            readln(a.marca);
            write('Ingrese modelo del auto: ');
            readln(a.modelo);
        end;
    end;

    procedure insertar(var a: arbolStock; auto: tipoAuto);
    var
        nuevo: arbolStock;
    begin
        if (a = nil) then begin
            new(nuevo);
            nuevo^.dato := auto;
            nuevo^.hi := nil;
            nuevo^.hd := nil;

            a := nuevo;
        end
        else
            if (auto.patente > a^.dato.patente) then
                insertar(a^.hd, auto)
            else
                insertar(a^.hi, auto)
    end;

var
    auto: tipoAuto;
begin
    a := nil;

    leerAuto(auto);

    while (auto.patente <> -1) do begin
        insertar(a, auto);
        leerAuto(auto);
    end;
end;

procedure calcularUnidadesMarca(a: arbolStock);
    function cantidadAutos(var a: arbolStock; marca: string):integer;
    begin
        if (a = nil) then
            cantidadAutos := 0
        else
            if (marca = a^.dato.marca) then
                cantidadAutos := 1 + cantidadAutos(a^.hd, marca) + cantidadAutos(a^.hi, marca)
            else
                cantidadAutos := cantidadAutos(a^.hd, marca) + cantidadAutos(a^.hi, marca);
    end;
var
    marcaBuscada: string;
begin
    write('Ingrese la marca que desea buscar: ');
    readln(marcaBuscada);

    writeln('La cantidad de autos de la marca ', marcaBuscada, ' es de: ', cantidadAutos(a, marcaBuscada));
    
end;

procedure buscarFabricacion(a: arbolStock);
    function autoConPatente(var a: arbolStock; p: integer): arbolStock;
    begin
        if (a = nil) then
            autoConPatente := nil
        else
            if (p = a^.dato.patente) then
                autoConPatente := a
            else
                if (p > a^.dato.patente) then
                    autoConPatente := autoConPatente(a^.hd, p)
                else
                    autoConPatente := autoConPatente(a^.hi, p);
    end;
var
    nodoBuscado: arbolStock;
    id: integer;
begin
    write('Ingrese la patente para la cual desea saber el año de fabricación: ');
    readln(id);

    nodoBuscado := autoConPatente(a, id);

    if nodoBuscado <> nil then begin
        writeln;
        writeln('El año de fabricación para el auto con patente, ', id, ' es el año: ', nodoBuscado^.dato.fabricacion);
        writeln;
    end
    else
        writeln;
        writeln('El auto con la patente ingresada no existe');
        writeln;
end;

var
    arbolDeStock: arbolStock;

begin
    generarArbol(arbolDeStock);
    calcularUnidadesMarca(arbolDeStock);
    buscarFabricacion(arbolDeStock);
end.