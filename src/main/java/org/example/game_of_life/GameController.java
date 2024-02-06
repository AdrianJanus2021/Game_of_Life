package org.example.game_of_life;

import java.util.Arrays;

public class GameController {
    int[][] board=new int[30][30];
    int[][] rules=new int[2][9];

    public GameController() {
        //fill rules with zeros
        for (int[] rule : rules) {
            Arrays.fill(rule, 0);
        }
        rules[0][3]=1;
        rules[1][2]=1;
        rules[1][3]=1;
        //fill board with zeros
        for (int[] ints : board) {
            Arrays.fill(ints, 0);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        for(int i=0;i<this.board.length;i++){
            this.board[i]= Arrays.copyOf(board[i],board[i].length);
        }
    }

    public int[][] getRules() {
        return rules;
    }

    public void setRules(int[][] rules) {
        for(int i=0;i<this.rules.length;i++){
            this.rules[i]= Arrays.copyOf(rules[i],rules[i].length);
        }
    }

    public void step() {
        int[][] tmp=new int[30][30];
        for(int i=0;i<getBoard().length;i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                //calculate neighbours
                int rup=i-1;
                if(rup<0){
                    rup= getBoard().length-1;
                }
                int r=i;
                int rdown=(i+1)% getBoard().length;
                int cleft=j-1;
                if(cleft<0){
                    cleft= getBoard()[i].length-1;
                }
                int c=j;
                int cright=(j+1)% getBoard()[i].length;

                int neighbours=getBoard()[rup][cleft]+getBoard()[rup][c]+getBoard()[rup][cright]+getBoard()[r][cleft]+getBoard()[r][cright]+getBoard()[rdown][cleft]+getBoard()[rdown][c]+getBoard()[rdown][cright];
                //dead or alive?
                tmp[i][j]=getRules()[getBoard()[i][j]][neighbours];
            }
        }
        setBoard(tmp);
    }

}
