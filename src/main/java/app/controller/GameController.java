package app.controller;

import javax.swing.*;

import app.model.*;
import app.view.GameView;

import java.awt.event.*;

public class GameController{
    private Board board;
    private GameView view;
    private char[] players = {'X', 'O'};

    public GameController(Board board, GameView view){
        this.board = board;
        this.view = view;
        this.board.setCurrentPlayer(players[(int)(Math.random()*2)]);

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

            if (checkGameOver()) return;
            board.switchPlayer();
            cpuMove();
            if (checkGameOver()) return;
            board.switchPlayer();
        }
    }
    private boolean checkGameOver() {
        if (board.isWin(board.getCurrentPlayer())) {
            JOptionPane.showMessageDialog(view, "Vince " + board.getCurrentPlayer() + "!");
            CheckUser();
            return true;
        } else if (board.isDraw()) {
            JOptionPane.showMessageDialog(view, "Pareggio!");
            CheckUser();
            return true;
        }
        return false;
    }
    private void cpuMove() {
        int[] move;
        move = findWinningMove(board.getCurrentPlayer());
        if (move == null) {
            char opponent = (board.getCurrentPlayer() == 'X') ? 'O' : 'X';
            move = findWinningMove(opponent);
        }
        if (move == null) {
            if (board.isEmpty(1, 1)) {
                move = new int[]{1, 1};
            }
        }
        if (move == null) {
            do {
                move = new int[]{(int)(Math.random() * 3), (int)(Math.random() * 3)};
            } while (!board.isEmpty(move[0], move[1]));
        }

        board.makeMove(move[0], move[1]);
        JButton btn = view.getButton(move[0], move[1]);
        btn.setText(String.valueOf(board.getCurrentPlayer()));
        btn.setEnabled(false);
    }

    private int[] findWinningMove(char player) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isEmpty(row, col)) {
                    board.makeTempMove(row, col, player); 
                    boolean win = board.isWin(player);
                    board.undoMove(row, col); 
                    if (win) {
                        return new int[]{row, col};
                    }
                }
            }
        }
        return null;
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

        this.board.setCurrentPlayer(players[(int)(Math.random()*2)]);
        JOptionPane.showMessageDialog(view, "Inizia " + board.getCurrentPlayer(), "Chi inizia" , 1);
    }
}