package fullsailclass.triviagame;


import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;

import android.media.MediaPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import junit.framework.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionsScreen extends AppCompatActivity {
    List<QandA> list = new ArrayList<QandA>();
    List<Integer> PlayedQuestions = new ArrayList<Integer>();
    MainMenu menu = new MainMenu();
    Random r = new Random();
    MediaPlayer play;
    CountDownTimer waitTimer = null;
    int Score = 0;
    int Life = 3;
    String category;
    String question, answer1, answer2,answer3, answer4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);
        Life = 3;
       category = getIntent().getExtras().getString("Category");

        OpenFile(category);
        //OpenFile("game_questions.txt");

        setTitle("Questions");
        NextQuestion(0);




    }

    public void configureQuestionsBackButton() {
        Button back = (Button) findViewById(R.id.QuestionsBackBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }

        });
    }

    public void configureAnswer1btn(final int _rand) {
       final Button Answer1 = (Button) findViewById(R.id.Answer1BTN);
        Answer1.setText(answer1);
        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(list.get(_rand).getIsAnswer1() == false) {
                    Answer1.setTextColor(getResources().getColor(R.color.Red));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            Answer1.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);
                }
                else
                {
                    Answer1.setTextColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            Answer1.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);

                }

                waitTimer.cancel();

            }

        });
    }

    public void configureAnswer2btn(final int _rand) {
        final Button Answer2 = (Button) findViewById(R.id.Answer2BTN);
        Answer2.setText(answer2);
        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if(list.get(_rand).getIsAnswer2() == false) {
                    Answer2.setTextColor(getResources().getColor(R.color.Red));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            Answer2.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);
                }
                else
                {
                    Answer2.setTextColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            Answer2.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);
                }
                waitTimer.cancel();


            }

        });
    }

    public void configureAnswer3btn(final int _rand) {
       final Button Answer3 = (Button) findViewById(R.id.Answer3BTN);
        Answer3.setText(answer3);
        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if(list.get(_rand).getIsAnswer3() == false) {
                    Answer3.setTextColor(getResources().getColor(R.color.Red));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            Answer3.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);
                }

                else
                {
                    Answer3.setTextColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            Answer3.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);
                }
                waitTimer.cancel();

            }

        });
    }

    public void configureAnswer4btn(final int _rand) {
        final Button Answer4 = (Button) findViewById(R.id.Answer4BTN);
        Answer4.setText(answer4);
        Answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if(list.get(_rand).getIsAnswer4() == false) {
                    Answer4.setTextColor(getResources().getColor(R.color.Red));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            Answer4.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);
                }

                else
                {
                    Answer4.setTextColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            Answer4.setTextColor(getResources().getColor(R.color.Black));
                        }
                    }, 1000);

                }
                waitTimer.cancel();


            }

        });
    }

    public void OpenFile(String test) {

        Scanner s = null;
        String question, a1, a2, a3, a4;
        int i = 0;
        boolean[] correct = new boolean[4];
        try {
            s = new Scanner(new BufferedReader(new InputStreamReader(getAssets().open(test), "UTF-8")));

            while (s.hasNextLine()) {

                //s.useDelimiter("\n");
                String redex = s.nextLine();
                String[] parts = redex.split("((?=\\-)(?=\\-))");
                for (i = 0; i < correct.length; ++i)
                    correct[i]=false;

                for(i = 1; i < parts.length; ++i) {
                    if(parts[i].charAt(1) == '*') {
                        parts[i] = parts[i].substring(2);
                        correct[i-1] = true;
                    }
                    else {
                        parts[i] = parts[i].substring(1);
                    }
                }

                question = parts[0];
                a1=parts[1];
                a2=parts[2];
                a3=parts[3];
                a4=parts[4];
                QandA temp = new QandA(question, a1, a2, a3, a4);
                temp.setIsAnswer1(correct[0]);
                temp.setIsAnswer2(correct[1]);
                temp.setIsAnswer3(correct[2]);
                temp.setIsAnswer4(correct[3]);
                list.add(temp);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("could not find file");
        }

    }


    public List<QandA> getList() {
        return list;
    }

    public int GetListSize()
    {
        return list.size();
    }

    public String GetListQuestion(int index)
    {
        return list.get(index).getQuestion();
    }
    public String GetListAnswer1(int index)
    {
        return list.get(index).getAnswer1();
    }
    public String GetListAnswer2(int index)
    {
        return list.get(index).getAnswer2();
    }
    public String GetListAnswer3(int index)
    {
        return list.get(index).getAnswer3();
    }
    public String GetListAnswer4(int index)
    {
        return list.get(index).getAnswer4();
    }

    public void NextQuestion(int _switch)
    {
        TextView questiontxt = (TextView) findViewById(R.id.QuestionText);
        TextView life = (TextView) findViewById(R.id.Lifetxt);
        TextView score = (TextView) findViewById(R.id.Scoretxt);

        switch (_switch)
        {
            case 1:
                Score += 100;

                //Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Life -= 1;
                //Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


        int rand = Math.abs(r.nextInt()) % list.size();

        for (int i = 0; i < PlayedQuestions.size(); ++i) {
            if (PlayedQuestions.get(i) == rand)
                rand = Math.abs(r.nextInt()) % list.size();
        }

        PlayedQuestions.add(rand);

        question = list.get(rand).getQuestion();
        answer1 = list.get(rand).getAnswer1();
        answer2 = list.get(rand).getAnswer2();
        answer3 = list.get(rand).getAnswer3();
        answer4 = list.get(rand).getAnswer4();

        configureQuestionsBackButton();
        configureAnswer1btn(rand);
        configureAnswer2btn(rand);
        configureAnswer3btn(rand);
        configureAnswer4btn(rand);
        questiontxt.setTextSize(34);
        questiontxt.setText(question);
        score.setText("Score: " + Score);
        life.setText("Life: " + Life);

        configureTimer();
        if(Life <= 0 || PlayedQuestions.size() >= 50 )
        {

            waitTimer.cancel();;
            changeActivity();
            finish();
        }
    }

    //Game over Pop up

    public void changeActivity(){
        Intent intent = new Intent(QuestionsScreen.this, GameOverPopUp.class);
        startActivity(intent);

    }

    //timer
    public void configureTimer(){
       waitTimer = new CountDownTimer(16000, 1000) {
            TextView timer = (TextView) findViewById(R.id.TimerText);

            public void onTick(long millisUntilFinished) {
                timer.setTextColor(Color.BLUE);

                timer.setText(""+millisUntilFinished / 1000);

                if (millisUntilFinished<6000){
                    timer.setTextColor(Color.RED);

                }

            }


            public void onFinish() {
                timer.setText("0");
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        NextQuestion(2);

                    }
                }, 3000);

            }

        }.start();

    }


}

