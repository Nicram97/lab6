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
    public ticTacToeBoard(Context cont, String moves) {
        context = cont;

        //Filing empty board by moves history
        int mvs = 0;
        for (String move : moves.split("(?!^)")) {
            if (move != "") {
                try {
                    int parsedMove = Integer.parseInt(move) - 1;
                    int row = parsedMove / 3;
                    int col = parsedMove % 3;
                    this.move(col, row, mvs++ % 2);
                } catch (Exception e) {
                }
            }
        }
        //Setting Current Player
        player = mvs % 2;
    }

    //Method make move
    private boolean move(int col, int row, int player) {
//        int row = 0;
//        try {
        //when field is taken, go upstairs
//            while (board[row][col] != 0)
//                row++;
//            board[row][col]=player+1;
//        }catch(Exception ex){
        /*if all cols are fils*/
//            return false;
//        }
//        return true;
        if (board[row][col] == 0) {
            board[row][col] = player + 1;
            return true;
        } else {
            return false;
        }
    }

    //Public method making move for playing user
    public ticTacToeBoard add(int count) {
//        count = count - 1;
        int row = count / 3;
        int col = count % 3;
        //If change `player++%2` to `player` there is no switching between players
        if (this.move(col, row, player++ % 2))
            return this;
        return null;
        //return only when moves are correct
    }

    @Override //Must be in adapter
    public int getCount() {
        return 3 * 3;
    }

    @Override //Must be in adapter
    public Object getItem(int position) {
        return position + 1;
    }

    @Override //Must be in adapter
    public long getItemId(int position) {
        return position + 1;
    }

    //Method for generate view of singe element in greed
    @Override //Must be in adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create element - ImageView
        ImageView iv = new ImageView(context);
        //Calculate position of element into board array
        int col = position % 3;
        int row = position / 3;

        //Set appropriate image
        switch (board[row][col]) {
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
        iv.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
        return iv;
    }

    public int checkWin() {
        int check = 0;
        //Check rows
        for (int row = 0; row < 2; row++, check = 0) {
            check = 0;
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == board[row + 1][col]) {
                    check++;
                    if (check == 3 && board[row][col] != 0)
                        return board[row][col];
                } else {
                    check = 0;
//                    break;
                }
            }
        }
        check = 0;
        //check cols
        for (int col = 0; col < 2; col++, check = 0) {
            check = 0;
            for (int row = 0; row < 3; row++) {
                if (board[row][col] == board[row][col + 1]) {
                    check++;
                    if (check == 3 && board[row][col] != 0)
                        return board[row][col];
                } else {
                    check = 0;
//                    break;
                }
            }
        }

        //Chceck rising horizontal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
//        for (int col = 0; col < 2; col++) {
//            for (int row = 2; row > 0; row--) {
//                if (board[row][col] == board[row - 1][col + 1]) {
//                    check++;
//                    if (check == 3 && board[col][row] != 0)
//                        return board[row][col];
//                } else {
//                    check = 0;
//                }
//            }
//        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        //Chceck falling horizontal
//        for (int col = 2; col > 0; col--) {
//            for (int row = 0; row < 2; row++) {
//                if (board[row][col] == board[row + 1][col - 1]) {
//                    check++;
//                    if (check == 3 && board[row][col] != 0)
//                        return board[row][col];
//                } else {
//                    check = 0;
//                }
//            }
//        }
        return 0;
    }

}
