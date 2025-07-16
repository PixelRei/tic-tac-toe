package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GameView extends JFrame{
    JButton[][] buttons;
    public GameView(){
        super("Tic Tac Toe");
        setSize(600, 750);
        setResizable(false);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JLabel title = new JLabel("Welcome to Tic-Tac-Toe");
        title.setFont(new Font("Arial", Font.BOLD, 28));

        buttons = new JButton[3][3];

        JPanel header = new JPanel();
        JPanel board = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        board.setLayout(new GridLayout(3,3));
        //board.setMargin(new Insets(10, 10, 10, 10)); probably work only with buttons
        header.add(title);
        //adding buttons
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                board.add(buttons[i][j] = new JButton());
            }
        }
        add(header);
        add(board);

        setVisible(true);
    }
    public static void main(String[] args){
        new GameView();
    }
}
