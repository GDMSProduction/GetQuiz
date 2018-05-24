package fullsailclass.triviagame;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Multiplayer extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    Random r = new Random();
    static MediaPlayer backgroundMusic;
    private List<QandA> list = new ArrayList<QandA>();
    private List<Integer> PlayedQuestions = new ArrayList<Integer>();
    private String KeyNumber = "1";
    private Button A1, A2, A3, A4;
    private TextView Question;
    private Boolean playerCheck = true;
    private String Player; //0 = player 1/1 = player 2
    private int Score, Life;
    private long time;
    int curQuestion = 0;
    int globalrand = 0;
    CountDownTimer waitTimer = null;
    String Questions, answer1, answer2, answer3, answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);
        Question = (TextView) findViewById(R.id.QuestionText);
        A1 = (Button) findViewById(R.id.Answer1BTN);
        A2 = (Button) findViewById(R.id.Answer2BTN);
        A3 = (Button) findViewById(R.id.Answer3BTN);
        A4 = (Button) findViewById(R.id.Answer4BTN);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        myRef.child("CurQuestion").child("Value").setValue("0");
        OpenFile("multiplayer_questions.txt");
        PopulatePlayedQuestions();
        CountDownTimer waitTimer = null;

        NextQuestion(0);
        configureQuestionsBackButton();
        BackgroundMusic();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (playerCheck == true) {
                    Player = dataSnapshot.child("Player").child("Value").getValue().toString();
                    if (Player.equals("0")) {
                        Question.setText(String.valueOf(dataSnapshot.child("Player").child("Value").getValue()));
                        myRef.child("Player").child("Value").setValue("1");
                        Player = dataSnapshot.child("Player").child("Value").getValue().toString();
                        playerCheck = false;
                    } else if (Player.equals("1")) {
                        Question.setText(String.valueOf(dataSnapshot.child("Player").child("Value").getValue()));
                        myRef.child("Player").child("Value").setValue("0");
                        Player = dataSnapshot.child("Player").child("Value").getValue().toString();
                        playerCheck = false;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundMusic.start();
    }

    //region Old Btn Code
    /*
    public void configureAnswer1btn() {
        A1 = (Button) findViewById(R.id.Answer1BTN);
        A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child(KeyNumber).child("Q").setValue("On what video game was Mario first intoduced?");
                myRef.child(KeyNumber).child("A1").setValue("*Donkey Kong");
                myRef.child(KeyNumber).child("A2").setValue("Merio Bros.");
                myRef.child(KeyNumber).child("A3").setValue("Dr. Mario");
                myRef.child(KeyNumber).child("A4").setValue("Super Mario Bros");

            }
        });
    }
    public void configureAnswer2btn() {
        A2 = (Button) findViewById(R.id.Answer2BTN);
        A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child(KeyNumber).child("Q").setValue("What actor played Forrest Gump?");
                myRef.child(KeyNumber).child("A1").setValue("Gary Sinise");
                myRef.child(KeyNumber).child("A2").setValue("*Tom Hanks");
                myRef.child(KeyNumber).child("A3").setValue("Adam Sandler");
                myRef.child(KeyNumber).child("A4").setValue("Brad Pitt");

            }
        });
    }
    public void configureAnswer3btn() {
        A3 = (Button) findViewById(R.id.Answer3BTN);
        A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child(KeyNumber).child("Q").setValue("Who was Simba's father in The Lion King?");
                myRef.child(KeyNumber).child("A1").setValue("Sarabi");
                myRef.child(KeyNumber).child("A2").setValue("Scar");
                myRef.child(KeyNumber).child("A3").setValue("*Mufasa");
                myRef.child(KeyNumber).child("A4").setValue("Timon");

            }
        });
    }

    public void configureAnswer4btn() {
        A4 = (Button) findViewById(R.id.Answer4BTN);
        A4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child(KeyNumber).child("Q").setValue("Witch band lead singer is Matt Shadows?");
                myRef.child(KeyNumber).child("A1").setValue("Metallica");
                myRef.child(KeyNumber).child("A2").setValue("Korn");
                myRef.child(KeyNumber).child("A3").setValue("*Avenged Sevenfold");
                myRef.child(KeyNumber).child("A4").setValue("Pantera");

            }
        });
    }
    */

    //endregion

    public void configureAnswer1btn(final int _rand) {
        final Button Answer1 = (Button) findViewById(R.id.Answer1BTN);
        //Answer1.setText(answer1);
        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (list.get(_rand).getIsAnswer1() == false) {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Red));
                    //ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);

                }
                //timeover.release();
                //SetupSound();
                //waitTimer.cancel();

            }

        });
    }

    public void configureAnswer2btn(final int _rand) {
        final Button Answer2 = (Button) findViewById(R.id.Answer2BTN);
        //Answer2.setText(answer2);
        //myRef.child(KeyNumber).child("A2").setValue(answer2);
        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (list.get(_rand).getIsAnswer2() == false) {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Red));
                    //ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);
                }
                //timeover.release();
                //SetupSound();
                //waitTimer.cancel();


            }

        });
    }

    public void configureAnswer3btn(final int _rand) {
        final Button Answer3 = (Button) findViewById(R.id.Answer3BTN);
        //Answer3.setText(answer3);
        //myRef.child(KeyNumber).child("A3").setValue(answer3);
        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (list.get(_rand).getIsAnswer3() == false) {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Red));
                    //ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);
                }
                //timeover.release();
                //SetupSound();
                //waitTimer.cancel();

            }

        });
    }

    public void configureAnswer4btn(final int _rand) {
        final Button Answer4 = (Button) findViewById(R.id.Answer4BTN);
        //Answer4.setText(answer4);
        //myRef.child(KeyNumber).child("A4").setValue(answer4);
        Answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (list.get(_rand).getIsAnswer4() == false) {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Red));
                    //ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //wrong.start();
                            NextQuestion(2);
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            //play.start();
                            NextQuestion(1);
                            ResetBTNColor();
                        }
                    }, 1000);

                }
                //timeover.release();
                //SetupSound();
                //waitTimer.cancel();


            }

        });
    }

    public void configureQuestionsBackButton() {
        Button back = (Button) findViewById(R.id.QuestionsBackBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundMusic.pause();
                MainMenu.backgroundM.start();
                finish();
            }

        });
    }

    public void BackgroundMusic() {
        backgroundMusic = MediaPlayer.create(Multiplayer.this, R.raw.multiplayer);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        backgroundMusic.setVolume((float) MainMenu.Settings.get(0) * 0.20f, (float) MainMenu.Settings.get(0) * 0.20f);
    }

    public void OpenFile(String test) {

        Scanner s = null;
        String question, a1, a2, a3, a4;
        int i = 0;
        boolean[] correct = new boolean[4];
        try {
            s = new Scanner(new BufferedReader(new InputStreamReader(getAssets().open(test), "UTF-8")));

            while (s.hasNextLine()) {

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


    public void NextQuestion(int _switch) {
        TextView questiontxt = (TextView) findViewById(R.id.QuestionText);
        TextView life = (TextView) findViewById(R.id.Lifetxt);
        TextView score = (TextView) findViewById(R.id.Scoretxt);

        switch (_switch) {
            case 1:
                Score += time * 10;
                break;
            case 2:
                Life -= 1;
                break;
            default:
                break;
        }
        int rand = PlayedQuestions.get(curQuestion);
        globalrand = rand;
        Questions = list.get(rand).getQuestion();
        answer1 = list.get(rand).getAnswer1();
        answer2 = list.get(rand).getAnswer2();
        answer3 = list.get(rand).getAnswer3();
        answer4 = list.get(rand).getAnswer4();

        configureQuestionsBackButton();
        configureAnswer1btn(rand);
        configureAnswer2btn(rand);
        configureAnswer3btn(rand);
        configureAnswer4btn(rand);
        //questiontxt.setTextSize(38);
        //questiontxt.setText(Questions);
        myRef.child(KeyNumber).child("Q").setValue(Questions);
        myRef.child(KeyNumber).child("A1").setValue(answer1);
        myRef.child(KeyNumber).child("A2").setValue(answer2);
        myRef.child(KeyNumber).child("A3").setValue(answer3);
        myRef.child(KeyNumber).child("A4").setValue(answer4);

        //curQuestion = curQuestion + 1;
        myRef.child("CurQuestion").child("Value").setValue(String.valueOf(curQuestion));

        score.setText("Score: " + Score);
        life.setText("Life: " + Life);
        configureTimer();

        if (Life <= 0 || curQuestion >= 20) {
            waitTimer.cancel();
            finish();
        }
    }

        private void PopulatePlayedQuestions () {
            for (int i = 0; i < list.size(); ++i) {
                PlayedQuestions.add(i);
            }
            Collections.shuffle(PlayedQuestions);
        }
        private void ResetBTNColor () {
            Button Answerbtn1 = (Button) findViewById(R.id.Answer1BTN);
            Button Answerbtn2 = (Button) findViewById(R.id.Answer2BTN);
            Button Answerbtn3 = (Button) findViewById(R.id.Answer3BTN);
            Button Answerbtn4 = (Button) findViewById(R.id.Answer4BTN);
            Answerbtn1.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
            Answerbtn2.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
            Answerbtn3.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
            Answerbtn4.setBackgroundColor(getResources().getColor(R.color.DarkBlueGray));
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

    public void configureTimer () {
        waitTimer = new CountDownTimer(MainMenu.Settings.get(3) * 1000, 1000) {
            TextView timer = (TextView) findViewById(R.id.TimerText);

            public void onTick(long millisUntilFinished) {
                timer.setTextColor(Color.BLUE);

                timer.setText("" + millisUntilFinished / 1000);
                time = (long) (Integer.parseInt(String.valueOf(millisUntilFinished)) / 1000);

                if (millisUntilFinished < 6000) {
                    timer.setTextColor(Color.RED);
                }
                //if (millisUntilFinished < 6000 && millisUntilFinished > 5000)
                    //timeover.start();


            }

            public void onFinish() {
                //wrong.start();
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

    }

