
// 4.- Una librería requiere el procesamiento de la información de sus productos. De cada producto
// se conoce el código del producto, código de rubro (del 1 al 8) y precio.
// Implementar un programa modularizado que:
// a. Lea los datos de los productos y los almacene ordenados por código de producto y agrupados
// por rubro, en una estructura de datos adecuada. El ingreso de los productos finaliza cuando se
// lee el precio 0.
// b. Una vez almacenados, muestre los códigos de los productos pertenecientes a cada rubro.
// c. Genere un vector (de a lo sumo 30 elementos) con los productos del rubro 3. Considerar que
// puede haber más o menos de 30 productos del rubro 3. Si la cantidad de productos del rubro 3
// es mayor a 30, almacenar los primeros 30 que están en la lista e ignore el resto.
// d. Ordene, por precio, los elementos del vector generado en b) utilizando alguno de los dos
// métodos vistos en la teoría.
// e. Muestre los precios del vector ordenado.


program clase1Ejercicio4;
const
    cantidadRubros = 8;
    dimFisicaRubroTres = 30;
type
    rubrosTipo = 1 .. cantidadRubros;
    vectorTresTipo = 0 .. dimFisicaRubroTres;
    producto = record
        codigoProducto: integer;
        codigoRubro: rubrosTipo;
        precio: real;
    end;

    listaProductos = ^nodo;
    nodo = record
        dato: producto;
        sig: listaProductos;                                                        
    end;

    vectorProductosRubro = array[rubrosTipo] of listaProductos;

    vectorRubroTres = array[1..dimFisicaRubroTres] of producto;

procedure iniciarListas(var v: vectorProductosRubro);
var 
    i: rubrosTipo;
begin
    for i := 1 to cantidadRubros do
        v[i] := nil;
end;

procedure insertarOrdenado(pi: listaProductos; p: producto);
var
    nuevoProducto: listaProductos;
begin
    new(nuevoProducto);
    nuevoProducto^.dato := p;

    while(p.codigoProducto < pi^.dato.codigoProducto) do
        pi := pi^.sig;

    nuevoProducto^.sig := pi^.sig;
    pi^.sig := nuevoProducto;

end;


procedure leerProducto(var p: producto);
begin
    write('Ingrese precio del producto: ');
    readln(p.precio);
    if p.precio <> 0 then begin
        write('Ingrese código de producto: ');
        readln(p.codigoProducto);
        write('Ingrese código de rubro: ');
        readln(p.codigoRubro);
	end;
end;

procedure leerProductos(var v: vectorProductosRubro);
var
    productoLeido: producto;
    rubro: rubrosTipo;

begin
    leerProducto(productoLeido);
    rubro := productoLeido.codigoRubro;
    while(productoLeido.precio <> 0) do begin
        insertarOrdenado(v[rubro], productoLeido);
        leerProducto(productoLeido);
    end;
end;

procedure mostrarCodigos(pi: listaProductos);
var
	rubroActual: rubrosTipo;
begin
    while (pi^.sig <> nil) do begin
        rubroActual := pi^.dato.codigoRubro;
        write('En el rubro ', rubroActual, ' existen los siguientes productos: ');
        while(rubroActual = pi^.dato.codigoRubro) and (pi^.sig <> nil) do
            write(' ', pi^.dato.codigoRubro, ', ');
    end;
end;

procedure mostrarCodigosPorRubro(v: vectorProductosRubro);
var
	i: rubrosTipo;
begin
	for i := 1 to cantidadRubros do
		mostrarCodigos(v[i]);
end;

procedure generarVectorRubroTres(var v:vectorRubroTres; var dimL: vectorTresTipo;  pi: listaProductos);
begin
	dimL := 0;
    while(pi^.sig <> nil) and (dimL < dimFisicaRubroTres) do begin
        dimL := dimL + 1;
		v[dimL] := pi^.dato;
		pi := pi^.sig;
	end;
end;

procedure ordenarPorInsercion(var v:vectorRubroTres; dimL: vectorTresTipo);
var
	i, j: vectorTresTipo;
	precioActual: real;
	
begin
	for i:= 2 to dimL do begin
		precioActual := v[i].precio;
		j := i - 1;
		while(j > 0) and (v[j].precio > precioActual) do begin
			v[j+1] := v[j];
			j := j - 1;
		end;
		v[j+1] := v[i];
	end;
end;

procedure mostrarVectorOrdenado(v: vectorRubroTres; dimL: vectorTresTipo);
var	
	i: vectorTresTipo;
begin
	writeln('Los precios en órden del rubro 3 son: ');
	for	i := 1 to dimL do
		writeln(v[i].precio);
		
end;


var
    vectorProductosPorRubro: vectorProductosRubro;
	vRubroTres: vectorRubroTres;
	dimLogicaRubroTres: vectorTresTipo;

begin
    iniciarListas(vectorProductosPorRubro);
    leerProductos(vectorProductosPorRubro);
	mostrarCodigosPorRubro(vectorProductosPorRubro);
	generarVectorRubroTres(vRubroTres, dimLogicaRubroTres, vectorProductosPorRubro[3]);
	ordenarPorInsercion(vRubroTres, dimLogicaRubroTres);
	mostrarVectorOrdenado(vRubroTres, dimLogicaRubroTres);
end.