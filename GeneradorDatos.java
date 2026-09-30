import java.util.Random;

public class GeneradorDatos {

    // Genera numeros aleatorios normales
    public static int[] generarAleatorios(int cantidad) {

        Random random = new Random();
        int[] datos = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            datos[i] = random.nextInt(100000);
        }

        return datos;
    }

    // Genera numeros dentro de un rango
    // Por ejemplo: entre 1 y 5
    public static int[] generarRango(
            int cantidad,
            int minimo,
            int maximo) {

        Random random = new Random();
        int[] datos = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            datos[i] =
                    random.nextInt(
                            maximo - minimo + 1
                    )
                    + minimo;
        }

        return datos;
    }
}
