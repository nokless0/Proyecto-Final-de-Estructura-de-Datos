import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        int tipoDatos;
        int cantidad;

        // ==========================================
        // SELECCIONAR TIPO DE DATOS
        // ==========================================
        System.out.println("===============================================");
        System.out.println("       COMPARADOR DE ORDENAMIENTOS");
        System.out.println("===============================================");
        System.out.println();
        System.out.println("Tipo de datos:");
        System.out.println("1. Numeros aleatorios");
        System.out.println("2. Numeros aleatorios entre 1 y 5");

        while (true) {

            System.out.print("Selecciona una opcion: ");

            if (scanner.hasNextInt()) {

                tipoDatos = scanner.nextInt();

                if (tipoDatos == 1 || tipoDatos == 2) {
                    break;
                }

            } else {
                scanner.next();
            }

            System.out.println("Selecciona solamente 1 o 2.");
        }

        // ==========================================
        // PEDIR CANTIDAD
        // ==========================================
        while (true) {

            System.out.print("Cuantos elementos vas a ordenar? ");

            if (scanner.hasNextInt()) {

                cantidad = scanner.nextInt();

                if (cantidad > 0) {
                    break;
                }

            } else {
                scanner.next();
            }

            System.out.println(
                    "Ingresa un numero entero mayor que 0."
            );
        }

        // ==========================================
        // INICIO DEL EXPERIMENTO
        // ==========================================
        long inicioExperimento = System.nanoTime();

        // ==========================================
        // GENERAR UNA SOLA COLECCION ORIGINAL
        // ==========================================
        int[] originales;

        if (tipoDatos == 1) {

            originales =
                    GeneradorDatos.generarAleatorios(cantidad);

        } else {

            originales =
                    GeneradorDatos.generarRango(
                            cantidad,
                            1,
                            5
                    );
        }

        // ==========================================
        // CREAR ARRAYLIST CON LOS MISMOS DATOS
        // ==========================================
        ArrayList<Integer> originalesLista =
                new ArrayList<>(cantidad);

        for (int numero : originales) {
            originalesLista.add(numero);
        }

        // ==========================================
        // MAPA SEGURO PARA LOS RESULTADOS
        // ==========================================
        ConcurrentHashMap<String, ResultadosOrdenamiento> resultados =
                new ConcurrentHashMap<>();

        // ==========================================
        // CREAR LOS 12 HILOS
        // Cada uno recibe su propia copia
        // ==========================================
        TareaOrdenamiento[] tareas = {

            // ARREGLOS
            new TareaOrdenamiento(
                    "Burbuja",
                    originales.clone(),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Seleccion",
                    originales.clone(),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Insercion",
                    originales.clone(),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Shell",
                    originales.clone(),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Merge",
                    originales.clone(),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Quick",
                    originales.clone(),
                    resultados
            ),

            // ARRAYLIST
            new TareaOrdenamiento(
                    "Burbuja",
                    new ArrayList<>(originalesLista),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Seleccion",
                    new ArrayList<>(originalesLista),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Insercion",
                    new ArrayList<>(originalesLista),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Shell",
                    new ArrayList<>(originalesLista),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Merge",
                    new ArrayList<>(originalesLista),
                    resultados
            ),

            new TareaOrdenamiento(
                    "Quick",
                    new ArrayList<>(originalesLista),
                    resultados
            )
        };

        // ==========================================
        // MEDIR EJECUCION CONCURRENTE
        // ==========================================
        long inicioHilos = System.nanoTime();

        for (TareaOrdenamiento tarea : tareas) {
            tarea.start();
        }

        for (TareaOrdenamiento tarea : tareas) {
            tarea.join();
        }

        long finHilos = System.nanoTime();

        // ==========================================
        // ORGANIZAR RESULTADOS
        // ==========================================
        ArrayList<ResultadosOrdenamiento> listaResultados =
                new ArrayList<>(resultados.values());

        listaResultados.sort(
                Comparator.comparingDouble(
                        ResultadosOrdenamiento::getTiempoMs
                )
        );

        // ==========================================
        // FINAL DEL EXPERIMENTO
        // ==========================================
        long finExperimento = System.nanoTime();

        double tiempoConcurrenteMs =
                (finHilos - inicioHilos)
                / 1_000_000.0;

        double tiempoConcurrenteSeg =
                tiempoConcurrenteMs / 1000.0;

        double tiempoExperimentoMs =
                (finExperimento - inicioExperimento)
                / 1_000_000.0;

        double tiempoExperimentoSeg =
                tiempoExperimentoMs / 1000.0;

        // ==========================================
        // MOSTRAR TABLA
        // ==========================================
        System.out.println();
        System.out.println(
                "==============================================================="
        );

        System.out.println(
                "                 RESULTADOS DE ORDENAMIENTO"
        );

        System.out.println(
                "==============================================================="
        );

        System.out.println(
                "Elementos: " + cantidad
        );

        if (tipoDatos == 1) {

            System.out.println(
                    "Datos: Aleatorios"
            );

        } else {

            System.out.println(
                    "Datos: Aleatorios entre 1 y 5"
            );
        }

        System.out.println();

        System.out.printf(
                "%-5s %-12s %-12s %-14s %-14s %-10s%n",
                "Pos.",
                "Algoritmo",
                "Estructura",
                "Tiempo (ms)",
                "Tiempo (s)",
                "Ordeno?"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        int posicion = 1;

        double sumaTiemposMs = 0;

        for (ResultadosOrdenamiento resultado : listaResultados) {

            double tiempoSegundos =
                    resultado.getTiempoMs() / 1000.0;

            sumaTiemposMs += resultado.getTiempoMs();

            System.out.printf(
                    "%-5d %-12s %-12s %-14.3f %-14.6f %-10s%n",
                    posicion,
                    resultado.getAlgoritmo(),
                    resultado.getEstructura(),
                    resultado.getTiempoMs(),
                    tiempoSegundos,
                    resultado.isOrdenado() ? "Si" : "No"
            );

            posicion++;
        }

        // ==========================================
        // MAS RAPIDO Y MAS LENTO
        // ==========================================
        ResultadosOrdenamiento masRapido =
                listaResultados.get(0);

        ResultadosOrdenamiento masLento =
                listaResultados.get(
                        listaResultados.size() - 1
                );

        System.out.println();

        System.out.println(
                "Implementacion con menor tiempo registrado:"
        );

        System.out.printf(
                "%s (%s) - %.3f ms (%.6f s)%n",
                masRapido.getAlgoritmo(),
                masRapido.getEstructura(),
                masRapido.getTiempoMs(),
                masRapido.getTiempoMs() / 1000.0
        );

        System.out.println();

        System.out.println(
                "Implementacion con mayor tiempo registrado:"
        );

        System.out.printf(
                "%s (%s) - %.3f ms (%.6f s)%n",
                masLento.getAlgoritmo(),
                masLento.getEstructura(),
                masLento.getTiempoMs(),
                masLento.getTiempoMs() / 1000.0
        );

        // ==========================================
        // COMPARACION DE TIEMPOS TOTALES
        // ==========================================
        double sumaTiemposSeg =
                sumaTiemposMs / 1000.0;

        System.out.println();
        System.out.println(
                "==============================================================="
        );

        System.out.println(
                "                 TIEMPOS GENERALES"
        );

        System.out.println(
                "==============================================================="
        );

        System.out.printf(
                "Suma de tiempos individuales: %.3f ms (%.3f s)%n",
                sumaTiemposMs,
                sumaTiemposSeg
        );

        System.out.printf(
                "Tiempo real de los 12 hilos:   %.3f ms (%.3f s)%n",
                tiempoConcurrenteMs,
                tiempoConcurrenteSeg
        );

        System.out.printf(
                "Tiempo total del experimento:  %.3f ms (%.3f s)%n",
                tiempoExperimentoMs,
                tiempoExperimentoSeg
        );

        System.out.println();
        System.out.println(
                "Nota: la suma de los tiempos individuales puede ser"
        );

        System.out.println(
                "mayor que el tiempo real porque los hilos se ejecutan"
        );

        System.out.println(
                "de manera concurrente."
        );

        scanner.close();
    }
}
