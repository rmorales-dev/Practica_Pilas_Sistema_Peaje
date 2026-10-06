import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Validaciones v = new Validaciones();
        Metodos m = new Metodos();
        Queue<ObjVehiculo> cola = new LinkedList<>();
        boolean continuar = true;

        while (continuar) {
            System.out.println();
            System.out.println("Bienvenido a la estacion de peaje nacho lee");
            System.out.println("Por favor ingrese la opcion que desea realizar: ");
            System.out.println("1) Registrar llegada de vehiculo");
            System.out.println("2) Despachar siguiente vehiculo");
            System.out.println("3) Consultar proximo vehiculo en turno");
            System.out.println("4) Mostrar vehiculos en espera");
            System.out.println("5) Reporte de recaudo y balance");
            System.out.println("6) Salir");

            int opt = v.ValidarEntero(sc);
            System.out.println();

            switch (opt) {
                case 1:
                    cola = m.EncolarVehiculo(cola, sc, v);
                    break;
                case 2:
                    System.out.println(m.DespacharVehiculo(cola));
                    break;
                case 3:
                    System.out.println(m.ConsultarProximo(cola));
                    break;
                case 4:
                    System.out.println(m.MostrarEnEspera(cola));
                    break;
                case 5:
                    System.out.println(m.ReporteRecaudo(cola));
                    break;
                case 6:
                    System.out.println("Gracias por utilizar el sistema de peaje Nacho Lee");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, ingrese una opcion del 1 al 6");
                    System.out.println();
                    break;
            }
        }
    }
}