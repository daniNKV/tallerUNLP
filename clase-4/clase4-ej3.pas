// 3. Implementar un programa que procese la información de las ventas de productos de una
// librería que tiene 4 sucursales. De cada venta se lee fecha de venta, código del producto
// vendido, código de sucursal y cantidad vendida. El ingreso de las ventas finaliza cuando se lee
// el código de sucursal 0. Implementar un programa que:
// a. Almacene las ventas ordenadas por código de producto y agrupados por sucursal, en una
// estructura de datos adecuada.
// b. Contenga un módulo que reciba la estructura generada en el punto a y retorne una
// estructura donde esté acumulada la cantidad total vendida para cada código de producto.

program ej3;
const
    cantidadSucursales = 4;
type
    rangoSucursales 0 .. cantidadSucursales;

    venta = record
        fecha: string;
        codigoProducto: integer;
        codigoSucursal: integer;
        cantidadVendida: integer;
    end;

    listaVentas = ^nodoVenta;
    
    nodoVenta = record
        dato: venta;
        sig: listaVentas;
    end;

    vectorVentas = array[rangoSucursales] of listaVentas;
    
    ventasTotales = record
        codigoProducto: integer;
        cantidadVendida: integer;
    end;

    listaVentasTotales = ^nodoTotal;

    nodoTotal = record
        dato: ventasTotales;
        sig: listaVentasTotales;

    end;


procedure generarListasDeVentas(var v: vectorVentas);
    procedure iniciarListas(var v: vectorVentas);
    var i: integer;
    begin
        for i := 0 to cantidadSucursales do 
            v[i] := nil;
    end;
    procedure leerVenta(var v: venta);
    begin
        write('Ingrese sucursal donde se vendió el producto: ');
        readln(v.codigoSucursal);
        if(v.codigo <> 0) then begin
            write('Ingrese fecha de venta: ');
            readln(v.fecha);
            write('Ingrese codigo del producto vendido:  ');
            readln(v.codigoProducto);
            write('Ingrese cantidad vendida: ');
            readln(v.cantidadVendida);
    end;
    procedure insertarOrdenado(pi: listaVentas; v: venta);
    var
        nuevoNodo: listaVentas;
        ant, act: listaVentas;
    begin
        new(nuevoNodo);
        nuevoNodo^.dato := v;
        nuevoNodo^.sig := nil;
        act := pi;

        while (act <> nil) and (v.codigoProducto > act^.dato.codigoProducto) do begin
            ant := act;
            act := act^.sig;
        end;

        if (act = nil) then begin
            pi := nuevoNodo;
        else 
            ant^.sig := nuevoNodo;
            nuevoNodo^.sig := act;
        end;
    end;

var
    nuevaVenta: venta;
begin
    iniciarListas(v);
    leerVenta(nuevaVenta);
    while(nuevaVenta.codigoSucursal <> -1) do begin
        insertarOrdenado(v[nuevaVenta.codigoSucursal], nuevaVenta);
        leerVenta(nuevaVenta);
    end;
end;

procedure generarListasDeVentasTotales(v: vectorVentas; var pi: listaVentasTotales);
    procedure insertarAtras(var pi, pf: listaVentasTotales; cod, total: integer);
    var
        nuevo: nodoTotal;
        act: listaVentasTotales;
    begin
        new(nuevo);
        nuevo^.dato.codigoProducto := cod;
        nuevo^.dato.cantidadVendida := total;
        nuevo^.sig := nil;
        act := pi;
        if (pi = nil) then
            pi := nuevo;
        else
            pf^.sig := nuevo;
            nuevo := pf;
    end;

    procedure buscarMinimo(var v: vectorVentas; var codMin, cantVentas: integer);
    var 
        i: integer;
        indiceMin: integer;
    begin
        codMin := 9999;
        for i := 1 to cantidadSucursales do begin
            if (v[i] <> nil) then
                if (v[i]^.dato.codigoProducto < codMin) then begin
                    cantVentas := v[i]^.dato.cantidadVendida;
                    codMin := v[i]^.dato.codigoProducto;
                    indiceMin := i;
                end;
        end;

        if (codMin <> 9999) then begin
            v[indiceMin] := v[indiceMin]^.sig;
        end;
    end; 
var
    pf: listaVentasTotales;
    ventaCodMin: venta;
    codActual: integer;
    total: integer;
    codMin: integer,
    cantVentasMin: integer;
begin
    pf := nil;
    pi := nil;

    buscarMinimo(v, codMin, cantVentasMin);

    
    while (codMin <> 9999) do begin
        codActual := codMin;
        total := 0;
        while (codMin <> 9999) and (codMin = codActual) do begin
            total := total + cantVentasMin;
            buscarMinimo(v, codMin, cantVentasMin);
        end;
        insertarAtras(pi, pf, codActual, total);
    end;

end;
var
    listasDeVentas: vectorVentas;
    totalVentas: listaVentasTotales;
begin
    generarListasDeVentas(listasDeVentas);
    generarListasDeVentasTotales(totalVentas);
end.