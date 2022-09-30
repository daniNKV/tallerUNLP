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

const

type
    fechaTipo = string[10];
    
    venta = record
        cod: integer;
        cantidad: integer;
    end;

    producto = record
        cod: integer;
        fecha: fechaTipo;
        cantidadVendidos: integer;
    end;

    arbolProductos = ^nodoProducto;
    arbolVentas = ^nodoVenta;
    
    nodoVenta = record
        dato: venta;
        hi: arbolVentas;
        hd: arbolVentas;
    end;

    nodoProducto = record
        dato: producto;
        hi: arbolProductos;
        hd: arbolProductos;
    end;

procedure leerVenta(var v: venta);
begin

end;

procedure generarArbol(ap: arbolProductos; av: arbolVentas);
var
    nuevaVenta: venta;
begin
    while
end;




var
    raizVentas: arbolVentas;
    raizProductos: arbolProductos;
begin
    generarArbol(raizProductos, raizVentas);
end.