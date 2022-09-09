// 3.Implementar un programa que contenga:
// a. Un módulo que lea información de alumnos de Taller de Programación y almacene en una
// estructura de datos sólo a aquellos alumnos que posean año de ingreso posterior al 2010. De
// cada alumno se lee legajo, DNI y año de ingreso. La estructura generada debe ser eficiente
// para la búsqueda por número de legajo.
// b. Un módulo que reciba la estructura generada en a. e informe el DNI y año de ingreso de
// aquellos alumnos cuyo legajo sea inferior a un valor ingresado como parámetro.
// c. Un módulo que reciba la estructura generada en a. e informe el DNI y año de ingreso de
// aquellos alumnos cuyo legajo esté comprendido entre dos valores que se reciben como
// parámetro.
// d. Un módulo que reciba la estructura generada en a. y retorne el DNI más grande.
// e. Un módulo que reciba la estructura generada en a. y retorne la cantidad de alumnos con
// legajo impar.

program ej3;

const 
    cotaLegajo = 20;
    cotaLegajoSuperior = 25;
    cotaLegajoInferior = 10;
type
    alumno = record
        legajo: integer;
        dni: integer;
        ingreso: integer;
    end;

    arbolAlumnos = ^nodoArbol;
    
    nodoArbol = record
        dato: alumno;
        hi: arbolAlumnos;
        hd: arbolAlumnos;
    end;

    listaAlumnos = ^nodoLista;

    nodoLista = record
        dato: alumno;
        sig: listaAlumnos;
    end;



procedure leerAlumno(var a: alumno);
begin
    write('Ingrese año de ingreso: ');
    readln(a.ingreso);
    write('Ingrese legajo: ');
    readln(a.legajo);
    if (a.ingreso > 2010) and (a.legajo <> -1) then begin
        write('Ingrese DNI: ');
        readln(a.dni);
    end;
end;

procedure insertarAlumno(var a: arbolAlumnos; al: alumno);
var
    nuevoNodo: arbolAlumnos;
begin
    if (a <> nil) then begin
        new(nuevoNodo);
        nuevoNodo^.hd := nil;
        nuevoNodo^.hi := nil;
        nuevoNodo^.dato := al;
    end
    else
        if (nuevoNodo^.dato.legajo > a^.dato.legajo) then
            insertarAlumno(a^.hd, al)
        else 
            insertarAlumno(a^.hi, al);
end;

procedure generarArbolAlumnos(var r: arbolAlumnos);
var
    a: alumno;
begin
    r := nil;
    leerAlumno(a);
    while (a.legajo <> -1) do begin
        if (a.ingreso > 2010) then
            insertarAlumno(r, a);
        leerAlumno(a);
    end;

end;

procedure imprimirAlumnosLegajoMenorA(cota: integer; a: arbolAlumnos);
begin
    if (a <> nil) then begin
        if (a^.dato.legajo < cota) then begin
            writeln('DNI del alumno: ', a^.dato.dni);
            writeln('Año de ingreso del alumno: ', a^.dato.ingreso);
        end;
        imprimirAlumnosLegajoMenorA(cota, a^.hi);
        imprimirAlumnosLegajoMenorA(cota, a^.hd);
    end;
end;

procedure imprimirAlumnosLegajoEntre(cotaInferior, cotaSuperior: integer; a: arbolAlumnos);
begin
    if (a <> nil) then begin
        if (a^.dato.legajo < cotaSuperior) and (a^.dato.legajo > cotaInferior) then begin
            writeln('DNI del alumno: ', a^.dato.dni);
            writeln('Año de ingreso del alumno: ', a^.dato.ingreso);
        end;
        imprimirAlumnosLegajoEntre(cotaInferior, cotaSuperior, a^.hi);
        imprimirAlumnosLegajoEntre(cotaInferior, cotaSuperior, a^.hd);
    end;
end;

procedure buscarMayorDNI(a: arbolAlumnos; var maxDNI: integer);
begin
    if (a <> nil) then begin
        if (a^.dato.dni > maxDNI) then
            maxDNI := a^.dato.dni;
        
        buscarMayorDNI(a^.hd, maxDNI);
    end;
end;



procedure contarDniImpares(a: arbolAlumnos; cantidad: integer);
    function esImpar(n: integer): boolean;
    var 
        d: integer;
    begin
        d := n MOD 2;
        
        esImpar := (d = 0);
    end;
begin
    if (a <> nil) then begin
        if(esImpar(a^.dato.dni)) then
            cantidad := cantidad + 1;
        
        contarDniImpares(a^.hi, cantidad);
        contarDniImpares(a^.hd, cantidad);
    end;
end;

var
    arbolAlumnosTaller: arbolAlumnos;
    maxDNI: integer;
    cantidadImpares: integer;

begin
    generarArbolAlumnos(arbolAlumnosTaller);
    imprimirAlumnosLegajoMenorA(cotaLegajo, arbolAlumnosTaller);
    if (cotaLegajoSuperior > cotaLegajoInferior) then
        imprimirAlumnosLegajoEntre(cotaLegajoInferior, cotaLegajoSuperior, arbolAlumnosTaller);
    buscarMayorDNI(arbolAlumnosTaller, maxDNI);
    cantidadImpares := 0;
    contarDniImpares(arbolAlumnosTaller, cantidadImpares);

end.