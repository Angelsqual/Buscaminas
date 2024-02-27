package principal;

import java.util.Scanner;
import Buscaminas.BuscaMinas;

/**
 * Clase que contiene el método main() para ejecutar el juego BuscaMinas.
 */
public class Interface {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al juego BuscaMinas!");
        System.out.println("Introduce la dimensión del tablero:");
        int dimension = scanner.nextInt();
        System.out.println("Introduce el número de minas:");
        int numeroMinas = scanner.nextInt();

        BuscaMinas juego = new BuscaMinas(dimension, numeroMinas);

        while (!juego.esFinJuego()) {
            System.out.println("Estado actual del tablero:");
            imprimirTablero(juego);
            System.out.println("Introduce la fila y columna de la celda a destapar (ejemplo: 1 2):");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();
            juego.destaparCelda(fila, columna);
        }

        System.out.println("¡Juego terminado!");
        scanner.close();
    }

    private static void imprimirTablero(BuscaMinas juego) {
        for (int i = 0; i < juego.getDimension(); i++) {
            for (int j = 0; j < juego.getDimension(); j++) {
                if (juego.esCeldaDestapada(i, j)) {
                    System.out.print(juego.getValorCelda(i, j) + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
