package fullsailclass.triviagame;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuestionsScreen extends AppCompatActivity {
    List<QandA> list = new ArrayList<QandA>();
    List<Integer> PlayedQuestions = new ArrayList<Integer>();
    MainMenu menu = new MainMenu();
    Random r = new Random();
    MediaPlayer play, timeover, gameover, wrong;
    static MediaPlayer backgroundMusic;
    CountDownTimer waitTimer = null;
    int Score = 0;
    int Life = MainMenu.Settings.get(2);
    int globalrand = 0;
    long time = 0;
    Integer curQuestion = 0;
    String category;
    String question, answer1, answer2, answer3, answer4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);

        Life = MainMenu.Settings.get(2);
        category = getIntent().getExtras().getString("Category");
        OpenFile(category);
        setTitle("Questions");
        SetupSound();
        MainMenu.backgroundM.pause();
        BackgroundMusic(category);
        PopulatePlayedQuestions();
        NextQuestion(0);

        //Broadcast
        Intent GameOver = new Intent("finish_GameOver");
        sendBroadcast(GameOver);

        Intent Victory = new Intent("finish_Victory");
        sendBroadcast(Victory);



        //Receiver
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_Questions")) {
                    waitTimer.cancel();
                    finish();
                    // DO WHATEVER YOU WANT.
                }


            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_Questions"));

    }
    @Override
    protected void onPause() {
        super.onPause();
        backgroundMusic.pause();
    }
    @Override
    protected  void onResume() {
        super.onResume();
        backgroundMusic.start();
    }
    public void configureQuestionsBackButton() {
        Button back = (Button) findViewById(R.id.QuestionsBackBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitTimer.cancel();
                timeover.release();
                backgroundMusic.pause();
                MainMenu.backgroundM.start();
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
                            wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);

                }
                timeover.release();
                SetupSound();
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
                            wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);
                }
                timeover.release();
                SetupSound();
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
                            wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);
                }
                timeover.release();
                SetupSound();
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
                            wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);

                }
                timeover.release();
                SetupSound();
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

        int rand = PlayedQuestions.get(curQuestion);

        globalrand = rand;

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
        questiontxt.setTextSize(38);
        questiontxt.setText(question);
        score.setText("Score: " + Score);
        life.setText("Life: " + Life);
        configureTimer();
        curQuestion++;
        if (Life <= 0) {
            waitTimer.cancel();
            gameover.start();
            changeToGameOver();
            finish();
        }
        if(curQuestion >= 20) {
            waitTimer.cancel();
            //gameover.start();
            changeToVictory();
            finish();
        }
    }

    public void changeToGameOver()
    {
        Intent intent = new Intent(QuestionsScreen.this, GameOverPopUp.class);
        startActivity(intent);
    }

    public void changeToVictory()
    {
        Intent intent = new Intent(QuestionsScreen.this, VictoryScreen.class);
        startActivity(intent);
    }

    //timer
    public void configureTimer() {
        waitTimer = new CountDownTimer(MainMenu.Settings.get(3)*1000, 1000) {
            TextView timer = (TextView) findViewById(R.id.TimerText);

            public void onTick(long millisUntilFinished) {
                timer.setTextColor(Color.BLUE);

                timer.setText("" + millisUntilFinished / 1000);
                time = (long) (Integer.parseInt(String.valueOf(millisUntilFinished)) / 1000);

                if (millisUntilFinished < 6000) {
                    timer.setTextColor(Color.RED);
                }
                if (millisUntilFinished < 6000 && millisUntilFinished > 5000)
                    timeover.start();


            }

            public void onFinish() {
                wrong.start();
                ShowAnswer();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        waitTimer.cancel();
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
        Answerbtn1.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
        Answerbtn2.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
        Answerbtn3.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
        Answerbtn4.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
    }

    private void SetupSound()
    {
        timeover = MediaPlayer.create(this.getBaseContext(), R.raw.timersdonesound);
        play = MediaPlayer.create(this.getBaseContext(), R.raw.good);
        wrong = MediaPlayer.create(this.getBaseContext(), R.raw.wrong);
        gameover = MediaPlayer.create(this.getBaseContext(),R.raw.gameover);
        SetFXVolume();
    }

    private void PopulatePlayedQuestions()
    {
        for (int i = 0; i < list.size(); ++i){
            PlayedQuestions.add(i);
        }
        Collections.shuffle(PlayedQuestions);
    }

    private void BackgroundMusic(String category) {
        ImageView background =(ImageView) findViewById(R.id.background);
        if(category.equals("entertainment_questions.txt"))
        {
            background.setImageResource(R.drawable.entertain1);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.entertainmentcategory);
        }
        else if(category.equals("game_questions.txt"))
        {
            background.setImageResource(R.drawable.videogames1);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.gamescategory);
        }
        else if(category.equals("history_questions.txt"))
        {
            background.setImageResource(R.drawable.history1);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.historycategory);
        }
        else if(category.equals("music_questions.txt"))
        {
            background.setImageResource(R.drawable.music3);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.musiccategory);
        }
        else if(category.equals("science_questions.txt"))
        {
            background.setImageResource(R.drawable.science2);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.sciencecategory);
        }
        else if(category.equals("sports_questions.txt"))
        {
            background.setImageResource(R.drawable.sports2);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.sportscategory);
        }
        else if(category.equals("popculture_questions.txt"))
        {
            background.setImageResource(R.drawable.popculture1);
            backgroundMusic = MediaPlayer.create(QuestionsScreen.this, R.raw.popculturecategory);
        }

        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        backgroundMusic.setVolume((float)MainMenu.Settings.get(0)*0.20f, (float)MainMenu.Settings.get(0)*0.20f);
    }

    private void SetFXVolume()
    {
        play.setVolume((float)MainMenu.Settings.get(1)*0.01f, (float)MainMenu.Settings.get(1)*0.01f);
        timeover.setVolume((float)MainMenu.Settings.get(1)*0.01f, (float)MainMenu.Settings.get(1)*0.01f);
        gameover.setVolume((float)MainMenu.Settings.get(1)*0.01f, (float)MainMenu.Settings.get(1)*0.01f);
        wrong.setVolume((float)MainMenu.Settings.get(1)*0.01f, (float)MainMenu.Settings.get(1)*0.01f);
    }

}

