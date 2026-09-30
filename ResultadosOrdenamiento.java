public class ResultadosOrdenamiento {

    private String algoritmo;
    private String estructura;
    private double tiempoMs;
    private boolean ordenado;

    public ResultadosOrdenamiento(
            String algoritmo,
            String estructura,
            double tiempoMs,
            boolean ordenado) {

        this.algoritmo = algoritmo;
        this.estructura = estructura;
        this.tiempoMs = tiempoMs;
        this.ordenado = ordenado;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public String getEstructura() {
        return estructura;
    }

    public double getTiempoMs() {
        return tiempoMs;
    }

    public boolean isOrdenado() {
        return ordenado;
    }
}
