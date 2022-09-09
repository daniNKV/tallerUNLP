// 4. Un teatro tiene funciones los 7 días de la semana. Para cada día se tiene una lista con las
// entradas vendidas. Se desea procesar la información de una semana. Implementar un
// programa que:
// a. Genere 7 listas con las entradas vendidas para cada día. De cada entrada se lee día (de 1 a
// 7), código de la obra, asiento y monto. La lectura finaliza con el código de obra igual a 0. Las
// listas deben estar ordenadas por código de obra de forma ascendente.
// b. Genere una nueva lista que totalice la cantidad de entradas vendidas por obra. Esta lista
// debe estar ordenada por código de obra de forma ascendente.

program ej4;

type
    funcion = record

    end;
    vectorFunciones = array[] of funcion;