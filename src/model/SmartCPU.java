package model;

public class SmartCPU {
    private Board board;
    private char Player;
    private char Person;
    public SmartCPU(Board board, char CurrentPlayer){
        this.board = board;
        Player = CurrentPlayer;
        if(Player == 'X'){
            Person = 'O';
        }else{
            Person = 'X';
        }
    }
    public void CPUMove(){
        if(board.getCell(2, 2) == ' '){
            board.makeMove(2, 2);
        }else{

        }
    }
    public char[][] CheckOptions(){
        int P = 0;
        char[][] option = null;
        for(int j=0; j<3; j++){
            for(int i=0; i<3; i++){
                if(board.getCell(j, i) == Person){
                    P++;
                }else{
                    option[j][i] = board.getCell(j, i);
                }
            }
            if(P == 2) break;
            else P = 0;
        }
        return option;
    }
}