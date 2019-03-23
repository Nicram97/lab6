package com.example.lab6;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class ticTacToe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        //Seting game board as adapter of gridView
        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new ticTacToeBoard(this));
        //Listner for clicking on element
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //Getting game (inRowBoard Adapter)
                GridView gv = (GridView) findViewById(R.id.gridView);
                ticTacToeBoard game = (ticTacToeBoard)gv.getAdapter();
                //Make Move
                if(game.add(arg3)!=null)
                    gv.setAdapter(game);
                else
                    System.out.print("Jeblo");
            }
        });
    }
}
