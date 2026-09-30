import java.util.ArrayList;

public class OrdenamientosArrayList {

    // =========================
    // METODO BURBUJA
    // =========================
    public static void burbuja(ArrayList<Integer> datos) {

        for (int i = 0; i < datos.size() - 1; i++) {

            boolean intercambio = false;

            for (int j = 0; j < datos.size() - 1 - i; j++) {

                if (datos.get(j) > datos.get(j + 1)) {

                    int auxiliar = datos.get(j);

                    datos.set(j, datos.get(j + 1));
                    datos.set(j + 1, auxiliar);

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
    public static void seleccion(ArrayList<Integer> datos) {

        for (int i = 0; i < datos.size() - 1; i++) {

            int posicionMenor = i;

            for (int j = i + 1; j < datos.size(); j++) {

                if (datos.get(j) < datos.get(posicionMenor)) {
                    posicionMenor = j;
                }
            }

            int auxiliar = datos.get(i);

            datos.set(i, datos.get(posicionMenor));
            datos.set(posicionMenor, auxiliar);
        }
    }

    // =========================
    // METODO INSERCION
    // =========================
    public static void insercion(ArrayList<Integer> datos) {

        for (int i = 1; i < datos.size(); i++) {

            int actual = datos.get(i);
            int j = i - 1;

            while (j >= 0 && datos.get(j) > actual) {

                datos.set(j + 1, datos.get(j));
                j--;
            }

            datos.set(j + 1, actual);
        }
    }

    // =========================
    // METODO SHELL
    // =========================
    public static void shell(ArrayList<Integer> datos) {

        for (int salto = datos.size() / 2;
             salto > 0;
             salto /= 2) {

            for (int i = salto; i < datos.size(); i++) {

                int actual = datos.get(i);
                int j = i;

                while (j >= salto
                        && datos.get(j - salto) > actual) {

                    datos.set(j, datos.get(j - salto));
                    j -= salto;
                }

                datos.set(j, actual);
            }
        }
    }

    // =========================
    // METODO MERGE
    // =========================
    public static void merge(ArrayList<Integer> datos) {

        ArrayList<Integer> auxiliar =
                new ArrayList<>(datos);

        mergeSort(
                datos,
                auxiliar,
                0,
                datos.size() - 1
        );
    }

    private static void mergeSort(
            ArrayList<Integer> datos,
            ArrayList<Integer> auxiliar,
            int izquierda,
            int derecha) {

        if (izquierda >= derecha) {
            return;
        }

        int medio =
                izquierda + (derecha - izquierda) / 2;

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
            ArrayList<Integer> datos,
            ArrayList<Integer> auxiliar,
            int izquierda,
            int medio,
            int derecha) {

        int i = izquierda;
        int j = medio + 1;
        int k = izquierda;

        while (i <= medio && j <= derecha) {

            if (datos.get(i) <= datos.get(j)) {

                auxiliar.set(k, datos.get(i));
                i++;

            } else {

                auxiliar.set(k, datos.get(j));
                j++;
            }

            k++;
        }

        while (i <= medio) {

            auxiliar.set(k, datos.get(i));

            i++;
            k++;
        }

        while (j <= derecha) {

            auxiliar.set(k, datos.get(j));

            j++;
            k++;
        }

        for (int posicion = izquierda;
             posicion <= derecha;
             posicion++) {

            datos.set(
                    posicion,
                    auxiliar.get(posicion)
            );
        }
    }

    // =========================
    // METODO QUICK
    // =========================
    public static void quick(ArrayList<Integer> datos) {

        if (!datos.isEmpty()) {

            quickSort(
                    datos,
                    0,
                    datos.size() - 1
            );
        }
    }

    private static void quickSort(
            ArrayList<Integer> datos,
            int izquierda,
            int derecha) {

        int i = izquierda;
        int j = derecha;

        int pivote =
                datos.get(
                        izquierda
                        + (derecha - izquierda) / 2
                );

        while (i <= j) {

            while (datos.get(i) < pivote) {
                i++;
            }

            while (datos.get(j) > pivote) {
                j--;
            }

            if (i <= j) {

                int auxiliar = datos.get(i);

                datos.set(i, datos.get(j));
                datos.set(j, auxiliar);

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
