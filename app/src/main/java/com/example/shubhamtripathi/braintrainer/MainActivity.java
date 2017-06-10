package com.example.shubhamtripathi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView status;
    String correct;
    TextView question;
    String incorrect;
    Button button1;
    Button button2;
    Button button3;
    RelativeLayout GoScreen;
    Button button4;
    TextView timer;
    TextView pointsTextView;
    Button playAgain;
    int score = 0;
    int total = 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int loc_correct ;


    public  void playAgain(final View view){


        status.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        score = 0;
        total = 0;
        pointsTextView.setText("0/0");
        timer.setText("30s");
        answers.clear();
        generateQuestion();

        final String sec = "0s";
        final String message = " Although you are stupid :- ";

        new CountDownTimer(60000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/ 1000) + "S");
            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);
                pointsTextView.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                timer.setText(sec);
                status.setText(message + Integer.toString(score) + " out of " + total);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);


            }
        }.start();




    }

    public  void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        TextView sumTextView =(TextView) findViewById(R.id.sumTextView);

        sumTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b)) ;

        loc_correct = rand.nextInt(4);

        int incorrectAnswer;

        answers.clear();

        for(int i=0;i<4;i++){

            if(i==loc_correct)
            {
                answers.add(a+b);

            }else{

                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a+b)
                {

                    incorrectAnswer = rand.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }


        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void StartGame(View view){

        startButton.setVisibility(View.INVISIBLE);
        GoScreen.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));

    }

    public void change (View view){
        if(view.getTag().toString().equals(Integer.toString(loc_correct)))
        {   correct = "CORRECT !!";
            status =(TextView) findViewById(R.id.status);
            status.setText(correct);
            score++;

        }
        else{
            incorrect ="Wrong!!";
            status.setText(incorrect);


        } total++;
        pointsTextView.setText(score + "/" + total);
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        button1 =(Button) findViewById(R.id.button1) ;
        button2 =(Button) findViewById(R.id.button2) ;
        button3 =(Button) findViewById(R.id.button3) ;
        button4 =(Button) findViewById(R.id.button4) ;
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timer = (TextView) findViewById(R.id.timerTextView);
        playAgain = (Button) findViewById(R.id.playAgain);
        status = (TextView) findViewById(R.id.status);
        question =(TextView) findViewById(R.id.sumTextView);
        GoScreen =(RelativeLayout)findViewById(R.id.gameRelativeLayout);



    }

}
