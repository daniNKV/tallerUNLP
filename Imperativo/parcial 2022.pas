// Una casa de comidas para llevar necesita el procesamiento de los pedidos de combos realizados por los clentes durante
// agosto de 2022. De cada pedido se oonoce el número del cliente, dia, cantidad de combos y monto pagado
// a) Implementar un módulo que lea pedidos, genere y retorne una estructura adecuada para la bisqueda por número
// de cliente, donde para cada número de clierte se almacenen juntos todos sus pedides (dia, cantidad de combos y
// monto) que realizó. La lectura finaliza conrdit-0. Se sugiere utilizor el mddulo leerRegistro (
// b) Implementar un módulo que reciba la estructura generada en a) y un nómero de dlente, y retorne todons les
// pedidos que realizó dicho cliente.
// c) Realice un módulo recursivo que reciba la información retornada en el inciso b)y retorne el moto total abenado
// por el cliente
// NOTA: Implementar el programa principal, que invoque a los incisos a, byc

program parcial;

type
    pedido = record
        cliente: integer;
        dia: integer;
        cantidadCombos: integer;
        monto: real;
    end;


    listaPedidos = ^nodoPedido;

    nodoPedido = record
        dato: pedido;
        sig: listaPedidos;
    end;

    arbolClientes = ^nodoCliente;

    nodoCliente = record
        nroCliente: integer;
        pedidos: listaPedidos;
        hi: arbol;
        hd: arbol;
    end;

procedure generarArbolClientes(var a: arbolClientes);
    procedure leerPedido(var p: pedido);
    begin
        write('Ingrese codigo del cliente: ');
        readln(p.cliente);

        if (p.cliente <> 0) do begin
            write('Ingrese dia del pedido: ');
            readln(p.dia);
            write('Ingrese cantidad de combos pedidos: ');
            readln(p.cantidadCombos);
            write('Ingrese monto pagado: ');
            readln(p.monto);
    end;

    procedure insertar(var a: arbolClientes; p: pedido);
        function nodoCliente(a: arbolClientes; cod: integer): arbolClientes
        begin
            if (a = nil) then
                nodoCliente := a;
            else
                if (cod > a^.nroCliente) then
                    nodoCliente := nodoCliente(a^.hd, cod);
                else if (cod < a^.nroCliente) then
                    nodoCliente := nodoCliente(a^.hi, cod);

        end;

        procedure insertarPedido(var pi: listaPedidos; p: pedido);
        var
            nuevo: listaPedidos;
        begin
            if (pi = nil) then begin
                new(nuevo);
                nuevo^.dato := p;
                nuevo^.sig := nil;
                pi := nuevo;
            end
            else
                insertarPedido(pi^.sig, p);
        end;
    var
        nuevoCliente: arbolClientes;
        pCliente: arbolClientes;
    begin

        pCliente := nodoCliente(a, p.cliente);

        if (pCliente = nil) then begin
            new(nuevoCliente);
            nuevoCliente^.nroCliente := p.cliente;
            insertarPedido(nuevoCliente^.pedidos, p);
            nuevoCliente^.hi := nil;
            nuevoCliente^.hd := nil;

            pCliente := nuevoCliente;
        end
        else
            insertarPedido(pCliente, p);



    end;
var
    nuevoPedido: pedido;

begin
    leerPedido(nuevoPedido);

    while (nuevoPedido.codigo <> 0) do begin
        insertar(a, nuevoPedido);
        leerPedido(nuevoPedido);
    end;

end;


var
    arbolPedidosPorCliente: arbol;
begin
    generarArbolClientes(arbolPedidosPorCliente);

end.