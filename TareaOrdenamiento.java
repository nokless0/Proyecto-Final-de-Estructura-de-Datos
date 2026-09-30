import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TareaOrdenamiento extends Thread {

    private String algoritmo;
    private String estructura;

    private int[] datosArreglo;
    private ArrayList<Integer> datosLista;

    private ConcurrentHashMap<String, ResultadosOrdenamiento> resultados;

    // ==========================================
    // CONSTRUCTOR PARA ARREGLOS
    // ==========================================
    public TareaOrdenamiento(
            String algoritmo,
            int[] datos,
            ConcurrentHashMap<String, ResultadosOrdenamiento> resultados) {

        this.algoritmo = algoritmo;
        this.estructura = "Arreglo";

        this.datosArreglo = datos;
        this.datosLista = null;

        this.resultados = resultados;
    }

    // ==========================================
    // CONSTRUCTOR PARA ARRAYLIST
    // ==========================================
    public TareaOrdenamiento(
            String algoritmo,
            ArrayList<Integer> datos,
            ConcurrentHashMap<String, ResultadosOrdenamiento> resultados) {

        this.algoritmo = algoritmo;
        this.estructura = "ArrayList";

        this.datosLista = datos;
        this.datosArreglo = null;

        this.resultados = resultados;
    }

    // ==========================================
    // CODIGO QUE EJECUTARA CADA HILO
    // ==========================================
    @Override
    public void run() {

        long inicio;
        long fin;

        boolean ordenado;

        if (datosArreglo != null) {

            inicio = System.nanoTime();

            ejecutarArreglo();

            fin = System.nanoTime();

            ordenado = estaOrdenado(datosArreglo);

        } else {

            inicio = System.nanoTime();

            ejecutarArrayList();

            fin = System.nanoTime();

            ordenado = estaOrdenado(datosLista);
        }

        double tiempoMs =
                (fin - inicio) / 1_000_000.0;

        ResultadosOrdenamiento resultado =
                new ResultadosOrdenamiento(
                        algoritmo,
                        estructura,
                        tiempoMs,
                        ordenado
                );

        String clave =
                algoritmo + "-" + estructura;

        resultados.put(clave, resultado);
    }

    // ==========================================
    // EJECUTAR ALGORITMO CON ARREGLO
    // ==========================================
    private void ejecutarArreglo() {

        switch (algoritmo) {

            case "Burbuja":
                OrdenamientosArreglo.burbuja(datosArreglo);
                break;

            case "Seleccion":
                OrdenamientosArreglo.seleccion(datosArreglo);
                break;

            case "Insercion":
                OrdenamientosArreglo.insercion(datosArreglo);
                break;

            case "Shell":
                OrdenamientosArreglo.shell(datosArreglo);
                break;

            case "Merge":
                OrdenamientosArreglo.merge(datosArreglo);
                break;

            case "Quick":
                OrdenamientosArreglo.quick(datosArreglo);
                break;

            default:
                System.out.println(
                        "Algoritmo no reconocido: "
                        + algoritmo
                );
        }
    }

    // ==========================================
    // EJECUTAR ALGORITMO CON ARRAYLIST
    // ==========================================
    private void ejecutarArrayList() {

        switch (algoritmo) {

            case "Burbuja":
                OrdenamientosArrayList.burbuja(datosLista);
                break;

            case "Seleccion":
                OrdenamientosArrayList.seleccion(datosLista);
                break;

            case "Insercion":
                OrdenamientosArrayList.insercion(datosLista);
                break;

            case "Shell":
                OrdenamientosArrayList.shell(datosLista);
                break;

            case "Merge":
                OrdenamientosArrayList.merge(datosLista);
                break;

            case "Quick":
                OrdenamientosArrayList.quick(datosLista);
                break;

            default:
                System.out.println(
                        "Algoritmo no reconocido: "
                        + algoritmo
                );
        }
    }

    // ==========================================
    // VERIFICAR ARREGLO
    // ==========================================
    private boolean estaOrdenado(int[] datos) {

        for (int i = 0; i < datos.length - 1; i++) {

            if (datos[i] > datos[i + 1]) {
                return false;
            }
        }

        return true;
    }

    // ==========================================
    // VERIFICAR ARRAYLIST
    // ==========================================
    private boolean estaOrdenado(
            ArrayList<Integer> datos) {

        for (int i = 0; i < datos.size() - 1; i++) {

            if (datos.get(i) > datos.get(i + 1)) {
                return false;
            }
        }

        return true;
    }
}
