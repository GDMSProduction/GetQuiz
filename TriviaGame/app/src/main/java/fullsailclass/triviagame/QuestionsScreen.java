package fullsailclass.triviagame;


import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;

import android.media.MediaPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
    MediaPlayer play, timeover, gameover;
    CountDownTimer waitTimer = null;
    int Score = 0;
    int Life = 3;
    int globalrand = 0;
    long time = 0;
    String category;
    String question, answer1, answer2, answer3, answer4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);
        Life = 3;
        category = getIntent().getExtras().getString("Category");

        OpenFile(category);

        setTitle("Questions");
        NextQuestion(0);
        timeover = MediaPlayer.create(this.getBaseContext(), R.raw.timersdonesound);
        play = MediaPlayer.create(this.getBaseContext(), R.raw.wrong);
        gameover = MediaPlayer.create(this.getBaseContext(),R.raw.gameover);


    }

    public void configureQuestionsBackButton() {
        Button back = (Button) findViewById(R.id.QuestionsBackBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitTimer.cancel();
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
                if (list.get(_rand).getIsAnswer1() == false) {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
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
                if (list.get(_rand).getIsAnswer2() == false) {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();

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
                if (list.get(_rand).getIsAnswer3() == false) {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
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

                if (list.get(_rand).getIsAnswer4() == false) {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.wrong);
                            play.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play = MediaPlayer.create(view.getContext(), R.raw.good);
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
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
                    correct[i] = false;

                for (i = 1; i < parts.length; ++i) {
                    if (parts[i].charAt(1) == '*') {
                        parts[i] = parts[i].substring(2);
                        correct[i - 1] = true;
                    } else {
                        parts[i] = parts[i].substring(1);
                    }
                }

                question = parts[0];
                a1 = parts[1];
                a2 = parts[2];
                a3 = parts[3];
                a4 = parts[4];
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

    public int GetListSize() {
        return list.size();
    }

    public String GetListQuestion(int index) {
        return list.get(index).getQuestion();
    }

    public String GetListAnswer1(int index) {
        return list.get(index).getAnswer1();
    }

    public String GetListAnswer2(int index) {
        return list.get(index).getAnswer2();
    }

    public String GetListAnswer3(int index) {
        return list.get(index).getAnswer3();
    }

    public String GetListAnswer4(int index) {
        return list.get(index).getAnswer4();
    }

    public void NextQuestion(int _switch) {
        TextView questiontxt = (TextView) findViewById(R.id.QuestionText);
        TextView life = (TextView) findViewById(R.id.Lifetxt);
        TextView score = (TextView) findViewById(R.id.Scoretxt);
        ResetBTNColor();

        switch (_switch) {
            case 1:
                Score += time*10;
                break;
            case 2:
                Life -= 1;
                break;
            default:
                break;
        }


        int rand = Math.abs(r.nextInt()) % list.size();

        for (int i = 0; i < PlayedQuestions.size(); ++i) {
            if (PlayedQuestions.get(i) == rand)
                rand = Math.abs(r.nextInt()) % list.size();
        }

        globalrand = rand;
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
        questiontxt.setTextSize(28);
        questiontxt.setText(question);
        score.setText("Score: " + Score);
        life.setText("Life: " + Life);
        configureTimer();

        if (Life <= 0 || PlayedQuestions.size() >= 50) {
            waitTimer.cancel();
            finish();
        }
    }

    //timer
    public void configureTimer() {
        waitTimer = new CountDownTimer(16000, 1000) {
            TextView timer = (TextView) findViewById(R.id.TimerText);

            public void onTick(long millisUntilFinished) {
                timer.setTextColor(Color.BLUE);

                timer.setText("" + millisUntilFinished / 1000);
                time = (long) (Integer.parseInt(String.valueOf(millisUntilFinished)) / 1000);

                if (millisUntilFinished < 6000) {
                    timer.setTextColor(Color.RED);
                    timeover.start();
                }

            }

            public void onFinish() {
                play.start();
                ShowAnswer();
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        NextQuestion(2);
                    }
                }, 2000);

            }
        }.start();
    }

    private void ShowAnswer() {
        Button Answerbtn1 = (Button) findViewById(R.id.Answer1BTN);
        Button Answerbtn2 = (Button) findViewById(R.id.Answer2BTN);
        Button Answerbtn3 = (Button) findViewById(R.id.Answer3BTN);
        Button Answerbtn4 = (Button) findViewById(R.id.Answer4BTN);

        if (list.get(globalrand).getIsAnswer1() == true)
            Answerbtn1.setBackgroundColor(getResources().getColor(R.color.Green));

        if (list.get(globalrand).getIsAnswer2() == true)
            Answerbtn2.setBackgroundColor(getResources().getColor(R.color.Green));

        if(list.get(globalrand).getIsAnswer3() == true)
        Answerbtn3.setBackgroundColor(getResources().getColor(R.color.Green));

        if(list.get(globalrand).getIsAnswer4() == true)
        Answerbtn4.setBackgroundColor(getResources().getColor(R.color.Green));
    }

    private void ResetBTNColor() {
        Button Answerbtn1 = (Button) findViewById(R.id.Answer1BTN);
        Button Answerbtn2 = (Button) findViewById(R.id.Answer2BTN);
        Button Answerbtn3 = (Button) findViewById(R.id.Answer3BTN);
        Button Answerbtn4 = (Button) findViewById(R.id.Answer4BTN);
        Answerbtn1.setBackgroundColor(getResources().getColor(R.color.Gray));
        Answerbtn2.setBackgroundColor(getResources().getColor(R.color.Gray));
        Answerbtn3.setBackgroundColor(getResources().getColor(R.color.Gray));
        Answerbtn4.setBackgroundColor(getResources().getColor(R.color.Gray));
    }

}

