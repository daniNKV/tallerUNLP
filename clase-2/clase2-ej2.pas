// 2.- Realizar un programa que lea números hasta leer el valor 0 e imprima, para cada número
// leído, sus dígitos en el orden en que aparecen en el número. Debe implementarse un módulo
// recursivo que reciba el número e imprima lo pedido. Ejemplo si se lee el valor 256, se debe
// imprimir 2 5 6

program ejercicio2clase2;


procedure imprimirNumeros();

    procedure imprimirDigitos(n: integer);
    begin
        if (n <> 0) then begin
            imprimirDigitos(n DIV 10);
            write(n MOD 10, ' ');
        end
        else

    end;
var n: integer;
begin
    write('Ingrese un número: '); readln(n);
    if (n <> 0) then
        imprimirDigitos(n);
        writeln;
        imprimirNumeros();
end;


begin
    imprimirNumeros()

end.