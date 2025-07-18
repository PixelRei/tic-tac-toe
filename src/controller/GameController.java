package controller;

//mine
import model.Board;
import view.GameView;

import javax.swing.*;
import java.awt.event.*;

public class GameController{
    private Board board;
    private GameView view;
    private char[] players = {'X', 'O'};
    private int player1, player2;

    public GameController(Board board, GameView view){
        this.board = board;
        this.view = view;
        this.board.setCurrentPlayer(players[(int)(Math.random()*2)]);
        this.board.setCPU();

        initialize();
    }
    public void initialize(){
        JOptionPane.showMessageDialog(view, "Inizia " + board.getCurrentPlayer(), "Chi inizia" , 1);
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
                CheckUser();
                return;
            } else if (board.isDraw()) {
                JOptionPane.showMessageDialog(view, "Pareggio!");
                resetGame();
                CheckUser();
                return;
            }
            board.switchPlayer();
            cpuMove();
            if (board.isWin(board.getCurrentPlayer())) {
                JOptionPane.showMessageDialog(view, "Vince " + board.getCurrentPlayer() + "!");
                resetGame();
                CheckUser();
                return;
            } else if (board.isDraw()) {
                JOptionPane.showMessageDialog(view, "Pareggio!");
                resetGame();
                CheckUser();
                return;
            }
            board.switchPlayer();
        }
    }
    private void cpuMove() {
        int row, col;
        do {
            row = (int)(Math.random()*3);
            col = (int)(Math.random()*3);
        } while (!board.makeMove(row, col));

        JButton btn = view.getButton(row, col);
        btn.setText("O");
        btn.setEnabled(false);
    }
    private void CheckUser(){
        int option = JOptionPane.showConfirmDialog(view, "Vuoi fare un'altra partita?", "Info", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION) resetGame();
        else{
            view.setVisible(false);
            view.dispose();
        }
    }
    private void resetGame() {
        board.reset();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton btn = view.getButton(row, col);
                btn.setText(" ");
                btn.setEnabled(true);
            }
        }
    }
}