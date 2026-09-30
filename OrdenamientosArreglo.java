public class OrdenamientosArreglo {

    // =========================
    // METODO BURBUJA
    // =========================
    public static void burbuja(int[] datos) {

        for (int i = 0; i < datos.length - 1; i++) {

            boolean intercambio = false;

            for (int j = 0; j < datos.length - 1 - i; j++) {

                if (datos[j] > datos[j + 1]) {

                    int auxiliar = datos[j];
                    datos[j] = datos[j + 1];
                    datos[j + 1] = auxiliar;

                    intercambio = true;
                }
            }

            if (!intercambio) {
                break;
            }
        }
    }

    // =========================
    // METODO SELECCION
    // =========================
    public static void seleccion(int[] datos) {

        for (int i = 0; i < datos.length - 1; i++) {

            int posicionMenor = i;

            for (int j = i + 1; j < datos.length; j++) {

                if (datos[j] < datos[posicionMenor]) {
                    posicionMenor = j;
                }
            }

            int auxiliar = datos[i];
            datos[i] = datos[posicionMenor];
            datos[posicionMenor] = auxiliar;
        }
    }

    // =========================
    // METODO INSERCION
    // =========================
    public static void insercion(int[] datos) {

        for (int i = 1; i < datos.length; i++) {

            int actual = datos[i];
            int j = i - 1;

            while (j >= 0 && datos[j] > actual) {

                datos[j + 1] = datos[j];
                j--;
            }

            datos[j + 1] = actual;
        }
    }

    // =========================
    // METODO SHELL
    // =========================
    public static void shell(int[] datos) {

        for (int salto = datos.length / 2;
             salto > 0;
             salto /= 2) {

            for (int i = salto; i < datos.length; i++) {

                int actual = datos[i];
                int j = i;

                while (j >= salto
                        && datos[j - salto] > actual) {

                    datos[j] = datos[j - salto];
                    j -= salto;
                }

                datos[j] = actual;
            }
        }
    }

    // =========================
    // METODO MERGE
    // =========================
    public static void merge(int[] datos) {

        int[] auxiliar = new int[datos.length];

        mergeSort(
                datos,
                auxiliar,
                0,
                datos.length - 1
        );
    }

    private static void mergeSort(
            int[] datos,
            int[] auxiliar,
            int izquierda,
            int derecha) {

        if (izquierda >= derecha) {
            return;
        }

        int medio = izquierda + (derecha - izquierda) / 2;

        mergeSort(
                datos,
                auxiliar,
                izquierda,
                medio
        );

        mergeSort(
                datos,
                auxiliar,
                medio + 1,
                derecha
        );

        mezclar(
                datos,
                auxiliar,
                izquierda,
                medio,
                derecha
        );
    }

    private static void mezclar(
            int[] datos,
            int[] auxiliar,
            int izquierda,
            int medio,
            int derecha) {

        int i = izquierda;
        int j = medio + 1;
        int k = izquierda;

        while (i <= medio && j <= derecha) {

            if (datos[i] <= datos[j]) {

                auxiliar[k] = datos[i];
                i++;

            } else {

                auxiliar[k] = datos[j];
                j++;
            }

            k++;
        }

        while (i <= medio) {

            auxiliar[k] = datos[i];

            i++;
            k++;
        }

        while (j <= derecha) {

            auxiliar[k] = datos[j];

            j++;
            k++;
        }

        for (int posicion = izquierda;
             posicion <= derecha;
             posicion++) {

            datos[posicion] = auxiliar[posicion];
        }
    }

    // =========================
    // METODO QUICK
    // =========================
    public static void quick(int[] datos) {

        if (datos.length > 0) {

            quickSort(
                    datos,
                    0,
                    datos.length - 1
            );
        }
    }

    private static void quickSort(
            int[] datos,
            int izquierda,
            int derecha) {

        int i = izquierda;
        int j = derecha;

        int pivote = datos[
                izquierda + (derecha - izquierda) / 2
        ];

        while (i <= j) {

            while (datos[i] < pivote) {
                i++;
            }

            while (datos[j] > pivote) {
                j--;
            }

            if (i <= j) {

                int auxiliar = datos[i];
                datos[i] = datos[j];
                datos[j] = auxiliar;

                i++;
                j--;
            }
        }

        if (izquierda < j) {

            quickSort(
                    datos,
                    izquierda,
                    j
            );
        }

        if (i < derecha) {

            quickSort(
                    datos,
                    i,
                    derecha
            );
        }
    }
}
