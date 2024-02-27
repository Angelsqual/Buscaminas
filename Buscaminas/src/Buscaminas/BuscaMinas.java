package Buscaminas;

import java.util.Random;

/**
 * Clase que controla la lógica del juego BuscaMinas.
 */
public class BuscaMinas {
    private int[][] tableroValores;
    private boolean[][] tableroDestapado;
    private int dimension;
    private int numeroMinas;
    private boolean finJuego;

    /**
     * Constructor para inicializar el juego con la dimensión del tablero y el
     * número de minas.
     */
    public BuscaMinas(int dimension, int numeroMinas) {
        this.dimension = dimension;
        this.numeroMinas = numeroMinas;
        this.tableroValores = new int[dimension][dimension];
        this.tableroDestapado = new boolean[dimension][dimension];
        inicializarTablero();
        distribuirMinas();
        actualizarValoresCeldas();
        this.finJuego = false;
    }

    private void inicializarTablero() {
        // Inicializa el tablero de valores y el tablero de estado
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tableroValores[i][j] = 0;
                tableroDestapado[i][j] = false;
            }
        }
    }

    private void distribuirMinas() {
        // Distribuye aleatoriamente el número de minas en el tablero
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < numeroMinas) {
            int fila = random.nextInt(dimension);
            int columna = random.nextInt(dimension);
            if (tableroValores[fila][columna] != 9) { // Si no hay ya una mina en esta posición
                tableroValores[fila][columna] = 9; // Coloca la mina
                minasColocadas++;
            }
        }
    }

    private void actualizarValoresCeldas() {
        // Actualiza los valores de las celdas que rodean a las minas
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tableroValores[i][j] == 9) { // Si hay una mina en esta celda
                    actualizarCeldasAdyacentes(i, j);
                }
            }
        }
    }

    
    /** 
     * @param fila
     * @param columna
     */
    private void actualizarCeldasAdyacentes(int fila, int columna) {
        // Actualiza los valores de las celdas adyacentes a una mina
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (fila + i >= 0 && fila + i < dimension && columna + j >= 0 && columna + j < dimension) {
                    if (tableroValores[fila + i][columna + j] != 9) {
                        tableroValores[fila + i][columna + j]++;
                    }
                }
            }
        }
    }

    /**
     * Destapa la celda seleccionada por el jugador.
     * Si la celda es 0, realiza un destapado recursivo.
     */
    public void destaparCelda(int fila, int columna) {
        if (tableroValores[fila][columna] == 0) {
            destaparRecursivo(fila, columna);
        } else {
            tableroDestapado[fila][columna] = true;
        }
    }

    private void destaparRecursivo(int fila, int columna) {
        // Destapa recursivamente las celdas adyacentes si son 0
        if (fila >= 0 && fila < dimension && columna >= 0 && columna < dimension && !tableroDestapado[fila][columna]) {
            tableroDestapado[fila][columna] = true;
            if (tableroValores[fila][columna] == 0) {
                destaparRecursivo(fila - 1, columna - 1);
                destaparRecursivo(fila - 1, columna);
                destaparRecursivo(fila - 1, columna + 1);
                destaparRecursivo(fila, columna - 1);
                destaparRecursivo(fila, columna + 1);
                destaparRecursivo(fila + 1, columna - 1);
                destaparRecursivo(fila + 1, columna);
                destaparRecursivo(fila + 1, columna + 1);
            }
        }
    }

    /**
     * Comprueba si el juego ha terminado.
     * El juego termina cuando todas las celdas excepto las minas están destapadas.
     */
    public boolean esFinJuego() {
        int celdasDestapadas = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tableroDestapado[i][j]) {
                    celdasDestapadas++;
                }
            }
        }
        if (celdasDestapadas == dimension * dimension - numeroMinas) {
            finJuego = true; // Todas las celdas excepto las minas están destapadas
        }
        return finJuego;
    }

    /**
     * Devuelve el valor de la celda en la posición especificada.
     */
    public int getValorCelda(int fila, int columna) {
        return tableroValores[fila][columna];
    }

    /**
     * Devuelve true si la celda en la posición especificada está destapada, false
     * en caso contrario.
     */
    public boolean esCeldaDestapada(int fila, int columna) {
        return tableroDestapado[fila][columna];
    }

    /**
     * Devuelve la dimensión del tablero.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Devuelve el número de minas en el tablero.
     */
    public int getNumeroMinas() {
        return numeroMinas;
    }

}
