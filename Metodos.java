import java.util.Queue;
import java.util.Scanner;

public class Metodos {
    private int siguienteConsecutivo = 1;

    public int GenerarConsecutivo() {
        int actual = siguienteConsecutivo;
        siguienteConsecutivo++;
        return actual;
    }
    public String TraducirCategoria(int cat) {
        if (cat == 1) {
            return "Flypass (Prioritario)";
        } else {
            return "Normal (Pago en efectivo)";
        }
    }
    public String TraducirEstado(int est) {
        if (est == 1) {
            return "En espera";
        } else if (est == 2) {
            return "Despachado";
        } else {
            return "Desconocido";
        }
    }
    // OPCIÓN 1: Registrar vehículo en la cola
    public Queue<ObjVehiculo> EncolarVehiculo(Queue<ObjVehiculo> cola, Scanner sc, Validaciones v) {
        boolean continuar = true;
        while (continuar) {
            ObjVehiculo vehiculo = new ObjVehiculo();

            System.out.println("Ingrese la placa del vehículo:");
            vehiculo.setPlaca(v.ValidarString(sc));

            System.out.println("Ingrese el tipo (Auto, Camion):");
            vehiculo.setTipo(v.ValidarString(sc));

            System.out.println("Ingrese la categoría del carril: 1) Flypass, 2) Normal");
            int categoria = v.ValidarEntero(sc);
            while (categoria < 1 || categoria > 2) {
                System.out.println("Opción no válida. Digite 1 o 2:");
                categoria = v.ValidarEntero(sc);
            }
            vehiculo.setCategoria(categoria);

            System.out.println("Ingrese el valor de la tarifa a pagar:");
            vehiculo.setValorTarifa(v.ValidarDouble(sc));

            vehiculo.setConsecutivo(GenerarConsecutivo());
            vehiculo.setEstado(1); // 1 = En espera

            cola.offer(vehiculo);

            System.out.println("Vehículo registrado con éxito.");
            System.out.println("Ticket consecutivo #" + vehiculo.getConsecutivo());
            System.out.println("¿Desea ingresar otro vehículo? 1) Sí, 2) No");
            int opt = v.ValidarEntero(sc);
            while (opt < 1 || opt > 2) {
                System.out.println("Opción no válida. Seleccione 1 o 2.");
                opt = v.ValidarEntero(sc);
            }
            if (opt == 2) {
                continuar = false;
            }
            System.out.println("------------------------------");
        }
        return cola;
    }
    // OPCIÓN 2: Despachar siguiente vehículo respetando prioridad Flypass
    public String DespacharVehiculo(Queue<ObjVehiculo> cola) {
        if (cola.isEmpty()) {
            return "No hay vehículos en la estación de peaje.";
        }
        ObjVehiculo siguiente = null;
        // 1. Prioridad: Buscar el primer vehículo Flypass pendiente
        for (ObjVehiculo veh : cola) {
            if (veh.getEstado() == 1 && veh.getCategoria() == 1) {
                siguiente = veh;
                break;
            }
        }
        // 2. Si no hay Flypass, buscar el primer Normal pendiente
        if (siguiente == null) {
            for (ObjVehiculo veh : cola) {
                if (veh.getEstado() == 1 && veh.getCategoria() == 2) {
                    siguiente = veh;
                    break;
                }
            }
        }
        if (siguiente == null) {
            return "No hay vehículos pendientes por atender.";
        }
        siguiente.setEstado(2); // Marcado como despachado

        System.out.println("-----------------------------");
        System.out.println("Vehículo despachado:");
        System.out.println("Ticket #" + siguiente.getConsecutivo());
        System.out.println("Placa: " + siguiente.getPlaca());
        System.out.println("Tipo: " + siguiente.getTipo());
        System.out.println("Carril: " + TraducirCategoria(siguiente.getCategoria()));
        System.out.println("Monto cobrado: $" + siguiente.getValorTarifa());
        System.out.println("------------------------------");
        return "El cobro se procesó correctamente.";
    }
    // OPCIÓN 3: Consultar el próximo vehículo a atender
    public String ConsultarProximo(Queue<ObjVehiculo> cola) {
        if (cola.isEmpty()) {
            return "No hay vehículos registrados en el sistema.";
        }
        ObjVehiculo proximo = null;
        for (ObjVehiculo veh : cola) {
            if (veh.getEstado() == 1 && veh.getCategoria() == 1) {
                proximo = veh;
                break;
            }
        }
        if (proximo == null) {
            for (ObjVehiculo veh : cola) {
                if (veh.getEstado() == 1 && veh.getCategoria() == 2) {
                    proximo = veh;
                    break;
                }
            }
        }
        if (proximo == null) {
            return "No hay vehículos pendientes en fila.";
        }
        System.out.println("-----------------------------");
        System.out.println("Próximo vehículo a atender:");
        System.out.println("Ticket #" + proximo.getConsecutivo());
        System.out.println("Placa: " + proximo.getPlaca());
        System.out.println("Tipo: " + proximo.getTipo());
        System.out.println("Carril: " + TraducirCategoria(proximo.getCategoria()));
        System.out.println("------------------------------");
        return "Consulta de turno completada.";
    }
    // OPCIÓN 4: Mostrar todos los vehículos en espera
    public String MostrarEnEspera(Queue<ObjVehiculo> cola) {
        if (cola.isEmpty()) {
            return "No hay vehículos en la estación de peaje.";
        }
        boolean hayEnEspera = false;
        for (ObjVehiculo veh : cola) {
            if (veh.getEstado() == 1) {
                hayEnEspera = true;
                System.out.println("-----------------------------");
                System.out.println("Ticket #" + veh.getConsecutivo());
                System.out.println("Placa: " + veh.getPlaca());
                System.out.println("Tipo: " + veh.getTipo());
                System.out.println("Carril: " + TraducirCategoria(veh.getCategoria()));
                System.out.println("Estado: " + TraducirEstado(veh.getEstado()));
                System.out.println("------------------------------");
            }
        }
        if (!hayEnEspera) {
            return "No hay vehículos esperando en este momento.";
        }
        return "Listado de vehículos en espera completado.";
    }
    // OPCIÓN 5: Reporte de recaudo y balance general
    public String ReporteRecaudo(Queue<ObjVehiculo> cola) {
        if (cola.isEmpty()) {
            return "No hay registros para balance.";
        }
        double totalDinero = 0;
        int despachados = 0;
        int pendientesFlypass = 0;
        int pendientesNormales = 0;

        for (ObjVehiculo veh : cola) {
            if (veh.getEstado() == 2) {
                totalDinero += veh.getValorTarifa();
                despachados++;
            } else if (veh.getEstado() == 1) {
                if (veh.getCategoria() == 1) {
                    pendientesFlypass++;
                } else {
                    pendientesNormales++;
                }
            }
        }
        System.out.println("-----------------------------");
        System.out.println("BALANCE DEL PEAJE:");
        System.out.println("Vehículos despachados: " + despachados);
        System.out.println("Vehículos en espera Flypass: " + pendientesFlypass);
        System.out.println("Vehículos en espera Normales: " + pendientesNormales);
        System.out.println("Total dinero recaudado: $" + totalDinero);
        System.out.println("------------------------------");
        return "Reporte financiero generado correctamente.";
    }
}