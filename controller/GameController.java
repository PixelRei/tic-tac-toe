package controller;

//mine
import model.Board;
import view.GameView;

import javax.swing.*;
import java.awt.event.*;

public class GameController{
    private Board board;
    private GameView view;

    public GameController(Board board, GameView view){
        this.board = board;
        this.view = view;
        this.board.setCurrentPlayer('X');

        initialize();
    }
    public void initialize(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int r = row, c = col; 
                view.getButton(row, col).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleMove(r, c);
                    }
                });
            }
        }
    }
    private void handleMove(int row, int col) {
        if (board.makeMove(row, col)) {
            view.getButton(row, col).setText(String.valueOf(board.getCurrentPlayer()));
            view.getButton(row, col).setEnabled(false);

            if (board.isWin(board.getCurrentPlayer())) {
                JOptionPane.showMessageDialog(view, "Vince " + board.getCurrentPlayer() + "!");
                resetGame();
                return;
            } else if (board.isDraw()) {
                JOptionPane.showMessageDialog(view, "Pareggio!");
                resetGame();
                return;
            }

            board.switchPlayer();
        }
    }
    private void resetGame() {
        board.reset();
        board.setCurrentPlayer('X');
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton btn = view.getButton(row, col);
                btn.setText("");
                btn.setEnabled(true);
            }
        }
    }
}