package com.example.lab6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ticTacToeBoard extends BaseAdapter {
    private Context context;
    private int player; //Current player (for move)
    private int[][] board = new int[3][3]; //game board

    //Constructor
    public ticTacToeBoard(Context cont) {
        context = cont;

        //Filing empty board by moves history
        int mvs = 0;
//        for (String move : moves.split("(?!^)")) {
//            if (move != "") {
//                try {
//                    this.move(Integer.parseInt(move), mvs++ % 2);
//                } catch (Exception e) {
//                }
//            }
//        }
        //Setting Current Player
        player = mvs % 2;
    }

    //Method make move
    private boolean move(int col, int player)
    {
        int row = 0;
        try {
            //when fild is taken, go upstairs
            while (board[row][col] != 0)
                row++;
            board[row][col]=player+1;
        }catch(Exception ex){
            /*if all cols are fils*/
            return false;
        }
        return true;
    }

    //Public method making move for playing user
    public ticTacToeBoard add(long col){
        //If change `player++%2` to `player` there is no switching between players
        if(this.move((int)col, player++%2))
            return this;
        return null;
        //return only when moves are correct
    }

    @Override //Must be in adapter
    public int getCount() {
        return 3*3;
    }
    @Override //Must be in adapter
    public Object getItem(int position) {
        return position%3;
    }
    @Override //Must be in adapter
    public long getItemId(int position) {
        return position%3;
    }

    //Method for generate view of singe element in greed
    @Override //Must be in adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create element - ImageView
        ImageView iv = new ImageView(context);
        //Calculate position of element into board array
        int col = position%3;
        int row = 2-position/3;

        //Set appropriate image
        switch (board[row][col]){
            case 0:
                iv.setImageResource(R.drawable.circle);
                break;
            case 1:
                iv.setImageResource(R.drawable.cross);
                break;
            case 2:
                iv.setImageResource(R.drawable.player1);
                break;
            case 3:
                iv.setImageResource(R.drawable.player2);
                break;
        }

        //Seting size of image - 120x120 px
        iv.setLayoutParams(new LinearLayout.LayoutParams(120,120));
        return iv;
    }

//    public int checkWin(){
//        int check = 0;
//
//        //Check rows
//        for(int row=0; row<3; row++, check=0)
//            for(int col=0;col<3;col++)
//                if(board[row][col]==board[row][col+1]) {
//                    check++;
//                    if(check==2 && board[row][col]!=0)
//                        return board[row][col];
//                }else
//                    check=0;
//
//        //check cols
//        for(int col=0;col<3;col++, check=0)
//            for(int row=0;row<3;row++)
//                if(board[row][col]==board[row+1][col]){
//                    check++;
//                    if(check==2 && board[row][col]!=0)
//                        return board[row][col];
//                }else
//                    check=0;
//
//
//        //Chceck rising horizontal
//        for(int posx=3; posx<6;posx++)
//            for(int posy=0;posy<4;posy++) {
//                check = 0;
//                for (int x=posx, y=posy; x >0 && y < 6; x--, y++)
//                    if (board[x][y] == board[x - 1][y + 1]) {
//                        check++;
//                        if (check == 3 && board[x][y] != 0)
//                            return board[x][y];
//                    } else
//                        check = 0;
//            }
//
//        //Chceck falling horizontal
//        for(int posx=0; posx<3;posx++)
//            for(int posy=0;posy<4;posy++) {
//                check = 0;
//                for (int x=posx, y=posy; x < 5 && y < 6; x++, y++)
//                    if (board[x][y] == board[x + 1][y + 1]) {
//                        check++;
//                        if (check == 3 && board[x][y] != 0)
//                            return board[x][y];
//                    } else
//                        check = 0;
//            }
//        return 0;
//    }

}
