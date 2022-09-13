
// 3.- Un supermercado requiere el procesamiento de sus productos. De cada producto se
// conoce código, rubro (1..10), stock y precio unitario. Se pide:
// a) Generar una estructura adecuada que permita agrupar los productos por
// rubro. A su vez, para cada rubro, se requiere que la búsqueda de un producto
// por código sea lo más eficiente posible. La lectura finaliza con el código de
// producto igual a -1..
// b) Implementar un módulo que reciba la estructura generada en a), un rubro y
// un código de producto y retorne si dicho código existe o no para ese rubro.
// c) Implementar un módulo que reciba la estructura generada en a), y retorne,
// para cada rubro, el código y stock del producto con mayor código.
// d) Implementar un módulo que reciba la estructura generada en a), dos códigos
// y retorne, para cada rubro, la cantidad de productos con códigos entre los
// dos valores ingresados.

program ej3;
const
    cantidadRubros = 10;
type
    rangoRubros = 0 .. cantidadRubros;

    arbolProductos = ^nodoProducto;

    producto = record
        codigo: integer;
        rubro: rangoRubros;
        stock: integer;
        precio: real;
    end;

    nodoProducto = record
        dato: producto;
        hi: arbolProductos;
        hd: arbolProductos;
    end;

    vectorRubros = array[rangoRubros] of arbolProductos;

procedure generarArboles(var v: vectorRubros);
    procedure iniciarArboles(var v: vectorRubros);
    var i: integer;
    begin
        for i := 1 to cantidadRubros do
            v[i] := nil
    end;
    
    procedure leerProducto(var p: producto);
    begin
        with p do begin
            write('Ingrese codigo de producto: ');
            readln(codigo);
            if (codigo <> -1) then begin
                write('Ingrese código de rubro: ');
                readln(rubro);
                write('Ingrese stock existente: ');
                readln(stock);
                write('Ingrese precio por unidad: ');
                readln(precio);
            end;
        end;
    end;
    procedure insertar(var a: arbolProductos; p: producto);
    var
        nuevo: arbolProductos;
    begin

        if (a = nil) then begin
            new(nuevo);
            nuevo^.dato := p;
            nuevo^.hi := nil;
            nuevo^.hd := nil;
            a := nuevo;
        end
        else
            if (p.codigo > a^.dato.codigo) then
                insertar(a^.hd, p)
            else
                insertar(a^.hi, p);
    end;
var
    p: producto;
begin
    iniciarArboles(v);
    leerProducto(p);

    while (p.codigo <> -1) do begin
        insertar(v[p.rubro], p);
        leerProducto(p);
    end;
end;

procedure informarExistenciaProducto(v: vectorRubros);
    function existeProducto(a: arbolProductos; codigo: integer):boolean;
    begin
        if (a = nil) then
            existeProducto := false
        else
            if (codigo > a^.dato.codigo) then
                existeProducto := existeProducto(a^.hd, codigo)
            else 
                if (codigo < a^.dato.codigo) then
                    existeProducto := existeProducto(a^.hi, codigo)
                else
                    existeProducto := true;
    end;

var
    codigoBuscado: integer;
    rubroBuscado: integer;

begin
    write('Ingrese el codigo de rubro en el que desea buscar: ');
    readln(rubroBuscado);
    write('Ingrese código del producto buscado: ');
    readln(codigoBuscado);

    if (existeProducto(v[rubroBuscado], codigoBuscado)) then
        writeln('El producto existe')
    else 
        writeln('El producto no existe');
    

end;

procedure informarStockMaximoPorRubro(var v: vectorRubros);
    function buscarMayor(a: arbolProductos): arbolProductos;
    begin
        if (a = nil) then
            buscarMayor := nil
        else
            if (a^.hd = nil) then
                buscarMayor := a
            else
                buscarMayor(a^.hd);
    end;    
    
var
    i: rangoRubros;
    max: producto;
begin
    for i := 1 to cantidadRubros do begin
        max := buscarMayor(v[i])^.dato;
        writeln('---------------------- -------------------------------- ---------------------');
        writeln;
        writeln('El codigo máximo del rubro ', i, 'es: ', max.codigo, ',  stock: ', max.stock );
        writeln;
        writeln('---------------------- -------------------------------- ---------------------');
    end;
end;

procedure contarProductosConCodigoAcotado(v: vectorRubros);
    function cantidadCodigosEntre(max, min: integer; a: arbolProductos): integer;
    begin
        if (a = nil) then
            cantidadCodigosEntre := 0
        else
            if (a^.dato.codigo > min) and (a^.dato.codigo < max) then
                cantidadCodigosEntre := 1 + cantidadCodigosEntre(min, max, a^.hd) + cantidadCodigosEntre(min, max, a^.hi)
            else if (a^.dato.codigo > min) then
                cantidadCodigosEntre := cantidadCodigosEntre(min, max, a^.hd)
            else
                cantidadCodigosEntre := cantidadCodigosEntre(min, max, a^.hi)
    end;
var
    i: integer;
    cotaSup, cotaInf: integer;
begin
    writeln('---------------------------------');
    writeln;
    write('Ingrese el código superior: ');
    readln(cotaSup);
    write('Ingrese el código inferior: ');
    readln(cotaInf);
    writeln;
    writeln('---------------------------------');

    for i := 1 to cantidadRubros do begin
        writeln;
        writeln('La cantidad de productos con códigos intermedios a las cotas es de: ');
        writeln(cantidadCodigosEntre(cotaSup, cotaInf, v[i]), ' codigos');
        writeln;

    end;
end;

var
    vectorArbolesDeProductos: vectorRubros;
begin
    generarArboles(vectorArbolesDeProductos);
    informarExistenciaProducto(vectorArbolesDeProductos);
    informarStockMaximoPorRubro(vectorArbolesDeProductos);
    contarProductosConCodigoAcotado(vectorArbolesDeProductos);
end.