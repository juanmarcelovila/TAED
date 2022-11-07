//Taller de Algoritmos y Estructura de Datos - INF389 - TP2
//Juan Marcelo Vila - VINF012235
//Plataforma de Desarrollo Visual Studio Code 1.73.0

import paquete.*;
import excepciones.HashTableFullException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {

    static Scanner scanInput = new Scanner(System.in);

    public static void main(String[] args) {
        String data;
        boolean salir = false;
        do {
            System.out.println("Seleccione el metodo deseado:\n" +
                    "1- (a)Hash H(x) = X mod 10\n" +
                    "2- (b y d)Tabla hash con manejo de colisiones con sondeo lineal\n" +
                    "3- (b y d)Tabla hash com manejo de colisiones con sondeo cuadratico\n" +
                    "4- (c y d)Tabla hash con manejo de colisiones con hashing abierto\n" +
                    "5- Salir");

            System.out.print("> ");
            data = scanInput.nextLine();
            try {
                switch (data){
                    case "1":
                        System.out.println("X Mod 10.");
                        ejecutar(XMod.class);
                        break;
                    case "2":
                        System.out.println("Caso de hash lineal.");
                        ejecutar(TablaSondeoLineal.class);
                        break;
                    case "3":
                        System.out.println("Caso de hash cuadratico.");
                        ejecutar(TablaSondeoCuadratico.class);
                        break;

                    case "4":
                        System.out.println("Caso de hash abierto.");
                        ejecutar(TablaHashingAbierto.class);
                        break;
                    case "5":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta, por favor seleccione una opcion del 1 al 5");
                        break;
                }
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } while  (!salir);

        scanInput.close();
    }

    public static int imprimirMenu(String tipoHash){
        String dato;
        System.out.println("Opciones:\n" +
                "1-" + " Insertar elemento\n" +
                "2-" + " Buscar elemento\n" +
                "3-" + " Borrar elemento\n" +
                "4-" + " Imprimir tabla hash completa\n" +
                "5-" + " Atras");

        System.out.print("> ");
        dato = scanInput.nextLine();
        return Integer.parseInt(dato);
    }

    public static String capturarConLeyenda(String leyenda){
        System.out.println(leyenda);
        System.out.print("> ");
        String valor = scanInput.nextLine();
        return valor;
    }

    public static Hashable obtenerObjetoHasheableConLeyenda(String leyenda){
        String valor = capturarConLeyenda(leyenda);
        Hashable object = new Recurso(Integer.parseInt(valor));
        return object;
    }

    public static void ejecutar(Class claseHash) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        TablaHash tabla;
        Constructor<TablaHash> constructor = null;
        constructor = claseHash.getConstructor();
        System.out.print("> ");
        tabla = constructor.newInstance();
        int opcion;
        boolean otro;
        do {
            opcion = imprimirMenu("");
            switch (opcion) {
                case 1:
                    do {
                        otro = false;
                        tabla.insertar(obtenerObjetoHasheableConLeyenda("Ingrese el valor a insertar (numerico):"));
                        System.out.println("Desea insertar otro? (s/N)");
                        System.out.print("> ");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 2:
                    do {
                        otro = false;
                        tabla.buscar(obtenerObjetoHasheableConLeyenda("Ingrese el valor que desea buscar"));
                        System.out.println("Desea buscar otro? (s/N)");
                        System.out.print("> ");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 3:
                    do {
                        otro = false;
                        tabla.eliminar(obtenerObjetoHasheableConLeyenda("Ingrese el valor que desea eliminar"));
                        System.out.println("Desea borrar otro? (s/N)");
                        System.out.print("> ");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 4:
                    tabla.imprimirTablaHash();
                    break;
            }
        } while(opcion != 5);
    }
}
