package com.mercurg.thiketsdiamonts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamAct extends AppCompatActivity {

    int[] diamonts = {
            R.drawable.bonus1,
            R.drawable.bonus2,
            R.drawable.bonus3,
            R.drawable.bonus4,
            R.drawable.bonus5,
            R.drawable.bonus6
    };




    int widthofbl, numberOfBl = 8, witchofScr;

    ArrayList<ImageView> diamont = new ArrayList<>();

    int diamontToBeDragged, diamontToBeReplaced;
    int notDiamont = R.drawable.transparent;

    Handler mHandler;
    int interval = 100;

    TextView scoreResu;
    TextView txt_krokus;
    ImageView starImage;

    public int scores = 0;
    public int krokuse = 30;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreResu = findViewById(R.id.score);
        txt_krokus = findViewById(R.id.krokus);

        starImage = findViewById(R.id.star_first);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        witchofScr = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthofbl = witchofScr / numberOfBl;
        createBoard();

        txt_krokus.setText(String.valueOf(krokuse));



        for (ImageView imageView : diamont) {
            imageView.setOnTouchListener(new OnSwipe(this) {

                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    diamontToBeDragged = imageView.getId();
                    diamontToBeReplaced = diamontToBeDragged - 1;
                    candyInterchange();
                    krokuse--;
                    txt_krokus.setText(String.valueOf(krokuse));


                    if (krokuse <1){


                        Intent intent = new Intent(GamAct.this, GameOv.class);
                        intent.putExtra("score", scores);
                        startActivity(intent);
                        finish();
                    }


                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    diamontToBeDragged = imageView.getId();
                    diamontToBeReplaced = diamontToBeDragged + 1;
                    candyInterchange();
                    krokuse--;
                    txt_krokus.setText(String.valueOf(krokuse));


                    if (krokuse <1){


                        Intent intent = new Intent(GamAct.this, GameOv.class);
                        intent.putExtra("score", scores);
                        startActivity(intent);
                        finish();
                    }


                }

                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    diamontToBeDragged = imageView.getId();
                    diamontToBeReplaced = diamontToBeDragged - numberOfBl;
                    candyInterchange();
                    krokuse--;
                    txt_krokus.setText(String.valueOf(krokuse));


                    if (krokuse <1){

                        Intent intent = new Intent(GamAct.this, GameOv.class);
                        intent.putExtra("score", scores);
                        startActivity(intent);
                        finish();
                    }

                }

                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    diamontToBeDragged = imageView.getId();
                    diamontToBeReplaced = diamontToBeDragged + numberOfBl;
                    candyInterchange();
                    krokuse--;
                    txt_krokus.setText(String.valueOf(krokuse));


                    if (krokuse <1){


                        Intent intent = new Intent(GamAct.this, GameOv.class);
                        intent.putExtra("score", scores);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        }





        mHandler = new Handler();
        startRepeat();
    }

    private void kinez() {


        /*if (kroku<1){
            txt_kroku.setText("finish");
            //Toast.makeText(GameActivity.this, "finish", Toast.LENGTH_SHORT).show();
        }*/

        if (scores <80){
            starImage.setImageResource(R.drawable.q);
        } else if (scores <120){
            starImage.setImageResource(R.drawable.q1);
        } else if (scores <=150){
            starImage.setImageResource(R.drawable.q2);
        } else if (scores >150){
            starImage.setImageResource(R.drawable.q3);
        }


    }

    private void checkRowForTree() {
        for (int i = 0; i < 62; i++) {
            int chosedCandy = (int) diamont.get(i).getTag();
            boolean isBlank = (int) diamont.get(i).getTag() == notDiamont;
            Integer[] notValid = {6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55};
            List<Integer> list = Arrays.asList(notValid);

            if (!list.contains(i)) {
                int x = i;
                if ((int) diamont.get(x++).getTag() == chosedCandy && !isBlank
                        && (int) diamont.get(x++).getTag() == chosedCandy
                        && (int) diamont.get(x++).getTag() == chosedCandy
                        && (int) diamont.get(x++).getTag() == chosedCandy
                        && (int) diamont.get(x).getTag() == chosedCandy) {
                    scores = scores + 12;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    scoreResu.setText(String.valueOf(scores));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);

                }

            }

            if (!list.contains(i)) {
                int x = i;
                if ((int) diamont.get(x++).getTag() == chosedCandy && !isBlank
                        && (int) diamont.get(x++).getTag() == chosedCandy
                        && (int) diamont.get(x++).getTag() == chosedCandy
                        && (int) diamont.get(x).getTag() == chosedCandy) {
                    scores = scores + 6;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    scoreResu.setText(String.valueOf(scores));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);

                }

            }


            if (!list.contains(i)) {
                int x = i;
                if ((int) diamont.get(x++).getTag() == chosedCandy && !isBlank
                        && (int) diamont.get(x++).getTag() == chosedCandy
                        && (int) diamont.get(x).getTag() == chosedCandy) {
                    scores = scores + 3;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    scoreResu.setText(String.valueOf(scores));


                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);
                    x--;
                    YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                    diamont.get(x).setImageResource(notDiamont);
                    diamont.get(x).setTag(notDiamont);

                }

            }


        }

        moveDownCandies();
    }

    private void checkColumnForTree() {
        for (int i = 0; i < 47; i++) {
            int chosedCandy = (int) diamont.get(i).getTag();
            boolean isBlank = (int) diamont.get(i).getTag() == notDiamont;
            int x = i;

            if ((int) diamont.get(x).getTag() == chosedCandy && !isBlank
                    && (int) diamont.get(x + numberOfBl).getTag() == chosedCandy
                    && (int) diamont.get(x + 2 * numberOfBl).getTag() == chosedCandy) {

                scores = scores + 3;
                scoreResu.setText(String.valueOf(scores));
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);


                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

            }

        }

        moveDownCandies();


    }

    private void checkColumnForFour() {
        for (int i = 0; i < 39; i++) {
            int chosedCandy = (int) diamont.get(i).getTag();
            boolean isBlank = (int) diamont.get(i).getTag() == notDiamont;
            int x = i;

            if ((int) diamont.get(x).getTag() == chosedCandy && !isBlank
                    && (int) diamont.get(x + numberOfBl).getTag() == chosedCandy
                    && (int) diamont.get(x + 2 * numberOfBl).getTag() == chosedCandy
                    && (int) diamont.get(x + 3 * numberOfBl).getTag() == chosedCandy) {

                scores = scores + 6;
                scoreResu.setText(String.valueOf(scores));
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);


                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

            }

        }

        moveDownCandies();


    }

    private void checkColumnForFive() {
        for (int i = 0; i < 31; i++) {
            int chosedCandy = (int) diamont.get(i).getTag();
            boolean isBlank = (int) diamont.get(i).getTag() == notDiamont;
            int x = i;

            if ((int) diamont.get(x).getTag() == chosedCandy && !isBlank
                    && (int) diamont.get(x + numberOfBl).getTag() == chosedCandy
                    && (int) diamont.get(x + 2 * numberOfBl).getTag() == chosedCandy
                    && (int) diamont.get(x + 3 * numberOfBl).getTag() == chosedCandy
                    && (int) diamont.get(x + 4 * numberOfBl).getTag() == chosedCandy) {

                scores = scores + 12;
                scoreResu.setText(String.valueOf(scores));
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);


                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

                x = x + numberOfBl;
                YoYo.with(Techniques.Bounce).duration(500).repeat(1).playOn(diamont.get(x));
                diamont.get(x).setImageResource(notDiamont);
                diamont.get(x).setTag(notDiamont);

            }
        }

        moveDownCandies();


    }

    Runnable repeatCheker = new Runnable() {
        @Override
        public void run() {
            try {
                checkRowForTree();
                checkColumnForFive();
                checkColumnForFour();
                checkColumnForTree();
                moveDownCandies();
                kinez();





            } finally {
                mHandler.postDelayed(repeatCheker, interval);


            }

        }
    };

    void startRepeat() {
        repeatCheker.run();

    }

    private void candyInterchange() {
        int background = (int) diamont.get(diamontToBeReplaced).getTag();
        int background1 = (int) diamont.get(diamontToBeDragged).getTag();
        diamont.get(diamontToBeDragged).setImageResource(background);
        diamont.get(diamontToBeReplaced).setImageResource(background1);
        diamont.get(diamontToBeDragged).setTag(background);
        diamont.get(diamontToBeReplaced).setTag(background1);


    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.board);
        gridLayout.setRowCount(numberOfBl);
        gridLayout.setColumnCount(numberOfBl);
        //square
        gridLayout.getLayoutParams().width = witchofScr;
        gridLayout.getLayoutParams().height = witchofScr;

        for (int i = 0; i < numberOfBl * numberOfBl; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthofbl, widthofbl));
            imageView.setMaxHeight(widthofbl);
            imageView.setMaxWidth(widthofbl);

            int randomCandy = (int) Math.floor(Math.random() * diamonts.length);//this generate randomValue from array

            imageView.setImageResource(diamonts[randomCandy]);
            imageView.setTag(diamonts[randomCandy]);

            diamont.add(imageView);

            gridLayout.addView(imageView);

        }

    }

    private void moveDownCandies() {
        Integer[] firstRow = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> list = Arrays.asList(firstRow);
        for (int i = 55; i >= 0; i--) {
            if ((int) diamont.get(i + numberOfBl).getTag() == notDiamont) {
                diamont.get(i + numberOfBl).setImageResource((int) diamont.get(i).getTag());
                diamont.get(i + numberOfBl).setTag(diamont.get(i).getTag());
                diamont.get(i).setImageResource(notDiamont);
                diamont.get(i).setTag(notDiamont);

                if (list.contains(i) && (int) diamont.get(i).getTag() == notDiamont) {
                    int randomColor = (int) Math.floor(Math.random() * diamonts.length);
                    diamont.get(i).setImageResource(diamonts[randomColor]);
                    diamont.get(i).setTag(diamonts[randomColor]);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            if ((int) diamont.get(i).getTag() == notDiamont) {
                int randomColor = (int) Math.floor(Math.random() * diamonts.length);
                diamont.get(i).setImageResource(diamonts[randomColor]);
                diamont.get(i).setTag(diamonts[randomColor]);

            }
        }
    }

    private void kinezfinish(){

        if (krokuse <1){

            //Toast.makeText(GameActivity.this, "finish", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(GamAct.this, GameOv.class);
            intent.putExtra("score", scores);
            startActivity(intent);
            finish();
        }
    }




}