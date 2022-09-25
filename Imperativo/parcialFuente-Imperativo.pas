// Se lee información acerca de las ventas de productos realizadas en las 5 sucursales de una empresa.
// Cada sucursal realizó a lo sumo 180 ventas. De cada venta se conoce el código de producto, cantidad
// vendida y monto total de la venta. Las ventas de cada sucursal se leen de manera consecutiva y
// ordenadas por código de producto. La lectura por cada sucursal finaliza al completar las 180 ventas o
// cuando se lee el código de producto -1, el cual no se procesa. Implementar un programa para que a
// partir de la información leida, resuelva los siguientes ítems:
// a) Utilizando la técnica de merge o merge acumulador según corresponda, generar una lista que
// contenga el monto total vendido para cada código de producto, ordenada por código de producto.
// b) Realizar un módulo recursivo que reciba la lista generada en el punto a y retorne la cantidad de
// productos para los cuales el monto total vendido es inferior a 300.000 pesos.

program ej;

    const maxVentas = 180;
type
    venta = record
        cod, cant: integer;
        monto: real;
    end;

    ventaTotal = record
        cod: integer;
        montoTotal: real;
    end;

    ventas = record
        vector: vectorVentas;
        dimL: integer;
    end:

    vectorVentas = array[1..maxVentas] of venta;

    vectorSucursales = array[rangoSucursales] of ventas;

procedure generarReporteTotalPorProducto(v: vectorSucursales)

var
    i, j: integer;
begin
    for i := 1 to 
end;

var
    vector: vectorSucursales;
    dimL: integer;
begin
    generarVectorSucursales(vector);
    generarReporteTotalPorProducto(vector)




end.