package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    int darkC;
    int brightC;
    View[][] tiles = new View[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        darkC = r.getColor(R.color.colorAccent);
        brightC = r.getColor(R.color.colorPrimary);

        tiles[0][0] = findViewById(R.id.t00);
        tiles[0][1] = findViewById(R.id.t01);
        tiles[0][2] = findViewById(R.id.t02);
        tiles[0][3] = findViewById(R.id.t03);
        tiles[1][0] = findViewById(R.id.t10);
        tiles[1][1] = findViewById(R.id.t11);
        tiles[1][2] = findViewById(R.id.t12);
        tiles[1][3] = findViewById(R.id.t13);
        tiles[2][0] = findViewById(R.id.t20);
        tiles[2][1] = findViewById(R.id.t21);
        tiles[2][2] = findViewById(R.id.t22);
        tiles[2][3] = findViewById(R.id.t23);
        tiles[3][0] = findViewById(R.id.t30);
        tiles[3][1] = findViewById(R.id.t31);
        tiles[3][2] = findViewById(R.id.t32);
        tiles[3][3] = findViewById(R.id.t33);

    }

    public void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightC) {
            v.setBackgroundColor(darkC);
        } else {
            v.setBackgroundColor(brightC);
        }
    }

    public boolean check_win(View v) {
        boolean flagEqualColors = true;
        boolean flagD = false;
        boolean flagB = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (flagEqualColors) {
                    ColorDrawable dij = (ColorDrawable) tiles[i][j].getBackground();
                    if (j == 0 && i == 0) {
                        if (dij.getColor() == darkC) {
                            flagD = true;
                        } else {
                            flagB = true;
                        }
                    } else {
                        if (flagD) {
                            if (dij.getColor() == darkC) continue;
                            else flagEqualColors = false;
                        } else if (flagB) {
                            if (dij.getColor() == brightC) continue;
                            else flagEqualColors = false;
                        }
                    }
                }
            }
        }
        if (flagEqualColors) return true;
        else return false;
    }

    public void onClick(View v) {
        if (!check_win(v)) {
            String tag = v.getTag().toString();
            int x = Character.getNumericValue(tag.charAt(0)), y = Character.getNumericValue(tag.charAt(1));
            changeColor(v);
            for (int i = 0; i < 4; i++) {
                changeColor(tiles[x][i]);
                changeColor(tiles[i][y]);
            }
            if (check_win(v)) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Вы выиграли! Хотите сыграть еще раз? Перезайдите в игру.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
