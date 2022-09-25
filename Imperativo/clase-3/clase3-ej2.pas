// 2. Escribir un programa que:
// a. Implemente un módulo que lea información de ventas de un comercio. De cada venta se lee
// código de producto, fecha y cantidad de unidades vendidas. La lectura finaliza con el código de
// producto 0. Un producto puede estar en más de una venta. Se pide:
// i. Generar y retornar un árbol binario de búsqueda de ventas ordenado por código de
// producto.
// ii. Generar y retornar otro árbol binario de búsqueda de productos vendidos ordenado por
// código de producto. Cada nodo del árbol debe contener el código de producto y la
// cantidad total vendida.
// Nota: El módulo debe retornar los dos árboles.
// b. Implemente un módulo que reciba el árbol generado en i. y un código de producto y retorne
// la cantidad total de unidades vendidas de ese producto.
// c. Implemente un módulo que reciba el árbol generado en ii. y un código de producto y retorne
// la cantidad total de unidades vendidas de ese producto.

program ej2;

type

    arbolVentas = ^nodoVenta;
    arbolProductosVendidos = ^nodoProducto;

    venta = record
        codigo: integer;
        fecha: string;
        cantidad: integer;
    end;

    productosVendidos = record
        codigo: integer;
        cantidadVendida: integer;
    end;   

    nodoVenta = record
        dato: venta;
        hi: arbolVentas;
        hd: arbolVentas;
    end;

    nodoProducto = record
        dato: productosVendidos;
        hi: arbolProductosVendidos;
        hd: arbolProductosVendidos;
    end;


procedure generarArboles(var aVentas: arbolVentas; var aProductos: arbolProductosVendidos);
    procedure leerVenta(var v: venta);
    begin
        write('Ingrese codigo del producto vendido: ');
        readln(v.codigo);
        if(v.codigo <> 0) then begin
            write('Ingrese fecha de venta: ');
            readln(v.fecha);
            write('Ingrese cantidades vendidas: ');
            readln(v.cantidad)
        end;
    end;
    
    procedure insertarEnArbolVentas(var AV: arbolVentas; v: venta);
    var
        nuevoNodo: arbolVentas;
    begin
        if (AV = nil) then begin
            new(nuevoNodo);
            nuevoNodo^.hd := nil;
            nuevoNodo^.hi := nil;
            nuevoNodo^.dato := v;
            AV := nuevoNodo;
        end
        else
            if (v.codigo < AV^.dato.codigo) then begin
                insertarEnArbolVentas(AV^.hi, v);
            end
            else
                insertarEnArbolVentas(AV^.hd, v);
    end;

    procedure insertarEnArbolProductosVendidos(var AP: arbolProductosVendidos; v: venta);
        function buscarProducto(AP: arbolProductosVendidos; codigo: integer): arbolProductosVendidos;
        begin
            if (AP = nil) then
                buscarProducto := nil
            else if (codigo = AP^.dato.codigo) then 
                    buscarProducto := AP
                else if (codigo < AP^.dato.codigo) then
                        buscarProducto := buscarProducto(AP^.hi, codigo)
                    else
                        buscarProducto := buscarProducto(AP^.hd, codigo);
        end;

        procedure insertarNuevoNodo(var AP: arbolProductosVendidos; v: venta);
        var
            nuevoNodo: arbolProductosVendidos;
        begin
            if (AP = nil) then begin
                new(nuevoNodo);
                nuevoNodo^.hd := nil;
                nuevoNodo^.hi := nil;
                nuevoNodo^.dato.codigo := v.codigo;
                nuevoNodo^.dato.cantidadVendida := v.cantidad;
            end
            else
                if (v.codigo < AP^.dato.codigo) then
                    insertarNuevoNodo(AP^.hi, v)
                else
                    insertarNuevoNodo(AP^.hd, v);
        end;

    var
        nodoProductoExistente: arbolProductosVendidos;
        
    begin
        aVentas := nil;
        aProductos := nil;

        nodoProductoExistente := buscarProducto(AP, v.codigo);

        if (nodoProductoExistente <> nil) then
            nodoProductoExistente^.dato.cantidadVendida := nodoProductoExistente^.dato.cantidadVendida + v.cantidad
        else
            insertarNuevoNodo(AP, v);

    end;

var
    nuevaVenta: venta;
begin
    
    leerVenta(nuevaVenta);
    while(nuevaVenta.codigo <> 0) do begin
        insertarEnArbolVentas(aVentas, nuevaVenta);
        insertarEnArbolProductosVendidos(aProductos, nuevaVenta);
        leerVenta(nuevaVenta);
    end;

end;

procedure contarCantidadVendidos(a: arbolVentas);
    function cantidadVendidos(a: arbolVentas; codigo: integer): integer;
    begin
        if (a = nil) then
            cantidadVendidos := 0
        else
            if (a^.dato.codigo = codigo) then
                cantidadVendidos := cantidadVendidos(a^.hd, codigo) + a^.dato.cantidad
            else
                if (a^.dato.codigo > codigo) then
                    cantidadVendidos := cantidadVendidos(a^.hd, codigo)
                else
                    cantidadVendidos := cantidadVendidos(a^.hi, codigo);
    end;
var
    codigoABuscar: integer;
    cantidadVendida: integer;
begin
    write('Ingrese el producto del cual quiere saber la cantidad total vendida: ');
    readln(codigoABuscar);
    cantidadVendida := cantidadVendidos(a, codigoABuscar);
    write('La cantidad vendida del producto ', codigoABuscar, ' es de: ', cantidadVendida);

end;

procedure buscarCantidadVendidos(AP: arbolProductosVendidos);
    function buscarCantidadVendida(AP: arbolProductosVendidos; codigo: integer): integer;
    begin
        if (AP = nil) then
            buscarCantidadVendida := 0
        else
            if (AP^.dato.codigo = codigo) then
                buscarCantidadVendida := AP^.dato.cantidadVendida
            else    
                if (AP^.dato.codigo < codigo) then
                    buscarCantidadVendida := buscarCantidadVendida(AP^.hi, codigo)
                else
                    buscarCantidadVendida := buscarCantidadVendida(AP^.hd, codigo);
    end;
var
    codigoABuscar: integer;

begin
    write('Ingrese el codigo del cual quiere saber la cantidad vendida: ');
    readln(codigoABuscar);
    write('La cantidad vendida del producto ', codigoABuscar, ' es de: ', buscarCantidadVendida(AP, codigoABuscar))


end;

var 
    aVentas: arbolVentas;
    aProductos: arbolProductosVendidos;

begin
    generarArboles(aVentas, aProductos);
    contarCantidadVendidos(aVentas);
    buscarCantidadVendidos(aProductos);
end.