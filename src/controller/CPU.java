package controller;

import model.Board;
import view.GameView;

import javax.swing.*;
import java.util.Random;

public class CPU {
    private Board board;
    private GameView view;
    private Random random = new Random();

    public CPU(Board board, GameView view) {
        this.board = board;
        this.view = view;
        board.setCurrentPlayer('X'); // utente è sempre X
        initialize();
    }
    private void initialize() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int r = row, c = col;
                view.getButton(r, c).addActionListener(e -> handlePlayerMove(r, c));
            }
        }
    }
    private void handlePlayerMove(int row, int col) {
        if (!board.makeMove(row, col)) return;

        view.getButton(row, col).setText("X");
        view.getButton(row, col).setEnabled(false);

        if (checkEnd('X')) return;

        // turno della CPU
        board.setCurrentPlayer('O');
        cpuMove();

        if (checkEnd('O')) return;

        board.setCurrentPlayer('X');
    }
    private void cpuMove() {
        // trova una mossa vuota a caso
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.makeMove(row, col));

        JButton btn = view.getButton(row, col);
        btn.setText("O");
        btn.setEnabled(false);
    }
    private boolean checkEnd(char player) {
        if (board.isWin(player)) {
            JOptionPane.showMessageDialog(view, "Vince " + player + "!");
            resetGame();
            return true;
        } else if (board.isDraw()) {
            JOptionPane.showMessageDialog(view, "Pareggio!");
            resetGame();
            return true;
        }
        return false;
    }
    private void resetGame() {
        board.reset();
        board.setCurrentPlayer('X');
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton btn = view.getButton(r, c);
                btn.setText("");
                btn.setEnabled(true);
            }
        }
    }
}
