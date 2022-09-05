// 1. Escribir un programa que:
// a. Implemente un módulo que lea información de socios de un club y las almacene en un árbol
// binario de búsqueda. De cada socio se lee número de socio, nombre y edad. La lectura finaliza
// con el número de socio 0 y el árbol debe quedar ordenado por número de socio.
// b. Una vez generado el árbol, realice módulos independientes que reciban el árbol como
// parámetro y que :
// i. Informe el número de socio más grande. Debe invocar a un módulo recursivo que
// retorne dicho valor.
// ii. Informe los datos del socio con el número de socio más chico. Debe invocar a un
// módulo recursivo que retorne dicho socio.
// iii. Informe el número de socio con mayor edad. Debe invocar a un módulo recursivo que
// retorne dicho valor.
// iv. Aumente en 1 la edad de todos los socios.
// v. Lea un valor entero e informe si existe o no existe un socio con ese valor. Debe invocar a
// un módulo recursivo que reciba el valor leído y retorne verdadero o falso.
// vi. Lea un nombre e informe si existe o no existe un socio con ese nombre. Debe invocar a
// un módulo recursivo que reciba el nombre leído y retorne verdadero o falso.
// vii. Informe la cantidad de socios. Debe invocar a un módulo recursivo que retorne dicha
// cantidad.
// viii. Informe el promedio de edad de los socios. Debe invocar al módulo recursivo del
// inciso vii e invocar a un módulo recursivo que retorne la suma de las edades de los socios.
// ix. Informe, a partir de dos valores que se leen, la cantidad de socios en el árbol cuyo
// número de socio se encuentra entre los dos valores ingresados. Debe invocar a un módulo
// recursivo que reciba los dos valores leídos y retorne dicha cantidad.
// x. Informe los números de socio en orden creciente.
// xi. Informe los números de socio pares en orden decreciente.


program ej1;

const 

type
    cadenaT = string[20];
    edadT = 1 .. 120;
    socio = record
        nro: integer;
        nombre: cadenaT;
        edad: edadT;
    end;

    arbol = ^nodo;
    nodo = record
        dato: socio;
        hi: arbol;
        hd: arbol;
    end;

procedure leerSocio(var s:socio);
begin
    write('Ingrese número de socio: ');
    readln(s.nro);
    if (s.nro <> 0) then begin
        write('Ingrese nombre del socio: ');
        readln(s.nombre);
        write('Ingrese edad del socio: ');
        readln(s.edad);
    end;
end;

procedure insertarNodo(var r: arbol; s: socio);
var 
    nuevoNodo: arbol;
begin
    if (r = nil) then begin
        new(nuevoNodo);
        nuevoNodo^.hi := nil;
        nuevoNodo^.hd := nil;
        nuevoNodo^.dato := s;
    end;
    else
        if(s.dato.nro > r.dato.nro) then
            insertarNodo(r^.hd, socio)
        else
            if(s.dato.nro < r.dato.nro) then
                insertarNodo(r^.hi, socio)
    

end;

procedure generarArbolSocios(var r: arbol);
var
    nuevoSocio: socio;
begin
    raiz := nil;
    leerSocio(nuevoSocio);
    while(nuevoSocio.nro <> 0) do begin
        insertarNodo(r, nuevoSocio);
        leerSocio(nuevoSocio);
    end;

end;


var
    raiz: arbol;
begin
    generarArbolSocios(raiz);

end.