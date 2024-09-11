package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner sc;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {
        sc = new Scanner(System.in);


    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\n----Menu Principal:----");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = sc.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    sc.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = sc.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];


    }
    
    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario los datos sobre la cantidad y los precios de cada referencia vendida
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void solicitarDatos(){
        System.out.println("Por favor, ingrese PRIMERO los precios de cada referencia y POSTERIORMENTE la cantidad.");
        for(int i = 0; i < precios.length; i++){
            System.out.println("Ingrese el precio de cada referencia "+(i+1)+":");
            precios[i] = sc.nextDouble();
        }
        for(int i = 0; i < unidades.length; i++){    
            System.out.println("Ahora ingrese la cantidad vendida de cada referencia "+(i+1)+":");
            unidades[i] = sc.nextInt();
        }

     
    }

    /**
     * Descripcion: Este metodo se encarga de calcular el total de unidades vendidas
     * 
     * @return
     */
    public static int calcularTotalUnidadesVendidas(){
        int totalUnidades = 0;
        for (int i = 0; i < unidades.length; i++){
            totalUnidades += unidades[i];
        }
        System.out.println("El total de unidades vendidas es:"+totalUnidades);
        return 0;

    }

    /**
     * Descripcion: Este metodo se encarga de calcular el promedio de los precios de las referencias vendidas
     * pre: Las variables totalPrecio y totalReferencias deben estar declaradas
     * pos: Genera y muestra la variable precioPromedio
     * @return
     */
    public static double calcularPrecioPromedio(){
        double totalPrecio = 0;
        int totalReferencias = precios.length;

        for (int i = 0; i < totalReferencias; i++) {
            totalPrecio += precios[i];
        }

        double precioPromedio = totalPrecio / totalReferencias;

        return precioPromedio;

    }

    public static double calcularVentasTotales(){
        
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
        
            totalVentas += precios[i] * unidades[i];
        }

        return totalVentas; 

    }

    public static int consultarReferenciasSobreLimite(double limite){
        int contador = 0;

        for (int i = 0; i < precios.length; i++) {
            double ventasReferencia = precios[i] * unidades[i];
            if (ventasReferencia > limite) {
                contador++;
            }

        }
        return contador;

    }
}
