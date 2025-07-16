
import model.Board;
import java.util.Scanner;

public class TicTacToeConsole {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

       
        board.setCurrentPlayer('X');
        board.reset();

        while (true) {
            printGrid(board);
            System.out.println("Giocatore " + board.getCurrentPlayer() + ", inserisci riga e colonna (0-2): ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!board.makeMove(row, col)) {
                System.out.println("Mossa non valida! Ritenta.");
                continue;
            }

            if (board.isWin()) {
                printGrid(board);
                System.out.println("Giocatore " + board.getCurrentPlayer() + " ha vinto!");
                break;
            } else if (board.isDraw()) {
                printGrid(board);
                System.out.println("Pareggio!");
                break;
            }

            board.switchPlayer();
        }

        scanner.close();
    }

    public static void printGrid(Board board) {
        System.out.println("\n  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                char cell = board.getCell(i, j);
                System.out.print(cell == ' ' ? '-' : cell);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }
}
