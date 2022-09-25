// Redictado Taller de Programación 2020
// Parte Práctica - Imperativo
// Cree un pas con su Apellido y Nombre (Apellido_Nombre.pas). Dentro del archivo incluir: apellido y nombre.
// El ministerio de salud quiere registrar información de los infectados de COVID-19. De cada infectado conoce: DNI,
// nombre, nivel de sintomatología (1: asintomático 2: leve 3: moderado 4: grave) y código de ciudad.
// 1- Realice un módulo que permita cargar en una estructura la información de infectados leida desde teclado (hasta
// ingresar DNI 0). Esta estructura debe ser eficiente para la búsqueda por DNI.
// A partir de la estructura generada en 1)
// 2- Realice un módulo que informe DNI, nombre, nivel de sintomatologia y código de ciudad del infectado más adulto.La
// persona más adulta es aquella con menor DNI.
// - Realice un modulo que genere una lista con todos los infectados de una ciudad cuyo codigo se recibe. Luego muestre
// el contenido de la lista.
// Nota: Realice el programa principal que invoque a los módulos realizados e informe lo pedido.


program parcial;

type
    paciente = record
        codigoCiudad: integer;
        dni: integer;
        nombre: string;
        nivelSintomatologia: rangoSintomatico;
    end;

    arbolPacientes = ^nodoPaciente;

    nodoPaciente = record
        dato: paciente;
        hi: arbolPacientes;
        hd: arbolPacientes;
    end;


procedure generarArbol(var a: arbolPacientes);
    procedure insertar(var a: arbolPacientes; p: paciente);
    var
        nuevoNodo: arbolPacientes;
    begin
        if (a = nil) then begin
            new(nuevoNodo);
            nuevoNodo^.dato := p;
            nuevoNodo^.hi := nil;
            nuevoNodo^.hd := nil;

        else
            if (p.dni > a^.dato.dni) then
                insertar(a^.hd, p);
            else
                insertar(a^.hi, p);

    end;

var
    nuevoPaciente: paciente;
begin

    leerPaciente(nuevoPaciente);
    while (nuevoPaciente.dni <> 0) do begin
        insertar(a, nuevoPaciente);
        leerPaciente(nuevoPaciente);
    end;
end;



var
    pacientesInfectados: arbolPacientes;
begin
    generarArbol(pacientesInfectados);



end.