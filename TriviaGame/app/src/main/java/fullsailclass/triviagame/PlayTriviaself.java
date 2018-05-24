package fullsailclass.triviagame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlayTriviaself extends AppCompatActivity {

    private int TotalQuestions;
    private List<Integer> PlayedQuestions = new ArrayList<Integer>();
    private String Questions, answer1, answer2, answer3, answer4;
    private Boolean CorrectA1,CorrectA2,CorrectA3,CorrectA4;
    private Button A1, A2, A3, A4;
    private TextView Question;
    private String CurQuestion;
    private int nextQuestion;
    private int rand;
    private int index;
    Random r = new Random();
    MediaPlayer play, wrong;
    Boolean check = false;

    private FirebaseDatabase DatabaseData;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);
        Question = (TextView) findViewById(R.id.QuestionText);
        A1 = (Button) findViewById(R.id.Answer1BTN);
        A2 = (Button) findViewById(R.id.Answer2BTN);
        A3 = (Button) findViewById(R.id.Answer3BTN);
        A4 = (Button) findViewById(R.id.Answer4BTN);
        TextView timer = (TextView) findViewById(R.id.TimerText);
        TextView life = (TextView) findViewById(R.id.Lifetxt);
        TextView score = (TextView) findViewById(R.id.Scoretxt);
        timer.setTextColor(getResources().getColor(R.color.BlueGray));
        life.setTextColor(getResources().getColor(R.color.BlueGray));
        score.setTextColor(getResources().getColor(R.color.BlueGray));

        configureQuestionsBackButton();
        configureAnswer1btn();
        configureAnswer2btn();
        configureAnswer3btn();
        configureAnswer4btn();
        SetupSound();
        CorrectA1 = false;
        CorrectA2 = false;
        CorrectA3 = false;
        CorrectA4 = false;
        nextQuestion = 0;
        index = 0;
        DatabaseData = FirebaseDatabase.getInstance();
        myRef = DatabaseData.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TotalQuestions = (int)dataSnapshot.child("Question").getChildrenCount();

                if(index >= PlayedQuestions.size()) {
                    Collections.shuffle(PlayedQuestions);
                    index = 0;
                }

                RandomQuestions();

                CurQuestion = String.valueOf(PlayedQuestions.get(index));
                /*
                rand = abs(r.nextInt() % TotalQuestions);
                    ++rand;
                CurQuestion = String.valueOf(rand);
                */


                Questions = String.valueOf(dataSnapshot.child("Question").child(CurQuestion).child("Q").getValue());
                answer1 = String.valueOf(dataSnapshot.child("Question").child(CurQuestion).child("A1").getValue());
                answer2 = String.valueOf(dataSnapshot.child("Question").child(CurQuestion).child("A2").getValue());
                answer3 = String.valueOf(dataSnapshot.child("Question").child(CurQuestion).child("A3").getValue());
                answer4 = String.valueOf(dataSnapshot.child("Question").child(CurQuestion).child("A4").getValue());


                if(answer1.charAt(0) == '*') {
                    CorrectA1 = true;
                   answer1 = answer1.substring(1);
                }
                if(answer2.charAt(0) == '*') {
                    CorrectA2 = true;
                    answer2 = answer2.substring(1);
                }
                if(answer3.charAt(0) == '*') {
                    CorrectA3 = true;
                    answer3 = answer3.substring(1);
                }
                if(answer4.charAt(0) == '*'){
                    CorrectA4 = true;
                    answer4 = answer4.substring(1);
                }

                Question.setText(Questions);
                A1.setText(answer1);
                A2.setText(answer2);
                A3.setText(answer3);
                A4.setText(answer4);
                A1.setClickable(true);
                A2.setClickable(true);
                A3.setClickable(true);
                A4.setClickable(true);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        TriviaselfOptions.backgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TriviaselfOptions.backgroundMusic.start();
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

    public void configureAnswer1btn() {
        final Button Answer1 = (Button) findViewById(R.id.Answer1BTN);
        //Answer1.setText(answer1);
        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Answer1.setClickable(false);
                if (CorrectA1 == false) {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            wrong.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer1.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);

                }
                //timeover.release();
                SetupSound();
                //waitTimer.cancel();

            }

        });
    }

    public void configureAnswer2btn() {
        final Button Answer2 = (Button) findViewById(R.id.Answer2BTN);
        Answer2.setClickable(false);
        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Answer2.setClickable(false);
                if (CorrectA2 == false) {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            wrong.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer2.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);
                }
                //timeover.release();
                SetupSound();
                //waitTimer.cancel();


            }

        });
    }

    public void configureAnswer3btn() {
        final Button Answer3 = (Button) findViewById(R.id.Answer3BTN);
        Answer3.setClickable(false);
        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Answer3.setClickable(false);
                if (CorrectA3 == false) {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            wrong.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer3.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);
                }
                //timeover.release();
                SetupSound();
                //waitTimer.cancel();

            }

        });
    }

    public void configureAnswer4btn() {
        final Button Answer4 = (Button) findViewById(R.id.Answer4BTN);
        Answer4.setClickable(false);
        Answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Answer4.setClickable(false);
                if (CorrectA4 == false) {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Red));
                    ShowAnswer();
                    view.postDelayed(new Runnable() {
                        public void run() {
                            wrong.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);
                } else {
                    Answer4.setBackgroundColor(getResources().getColor(R.color.Green));
                    view.postDelayed(new Runnable() {
                        public void run() {
                            play.start();
                            NextQuestion();
                            ResetBTNColor();
                        }
                    }, 1000);

                }
                //timeover.release();
                SetupSound();
                //waitTimer.cancel();


            }

        });
    }

    private void NextQuestion()
    {
        myRef.child("Update").setValue(r.nextInt()%100);
        CorrectA1 = false;
        CorrectA2 = false;
        CorrectA3 = false;
        CorrectA4 = false;
        ++index;
    }

    private void ShowAnswer() {
        Button Answerbtn1 = (Button) findViewById(R.id.Answer1BTN);
        Button Answerbtn2 = (Button) findViewById(R.id.Answer2BTN);
        Button Answerbtn3 = (Button) findViewById(R.id.Answer3BTN);
        Button Answerbtn4 = (Button) findViewById(R.id.Answer4BTN);

        if (CorrectA1 == true)
            Answerbtn1.setBackgroundColor(getResources().getColor(R.color.Green));

        if (CorrectA2 == true)
            Answerbtn2.setBackgroundColor(getResources().getColor(R.color.Green));

        if(CorrectA3 == true)
            Answerbtn3.setBackgroundColor(getResources().getColor(R.color.Green));

        if(CorrectA4 == true)
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
        play = MediaPlayer.create(this.getBaseContext(), R.raw.good);
        wrong = MediaPlayer.create(this.getBaseContext(), R.raw.wrong);
        SetFXVolume();
    }

    private void SetFXVolume()
    {
        play.setVolume((float)MainMenu.Settings.get(1)*0.01f, (float)MainMenu.Settings.get(1)*0.01f);
        wrong.setVolume((float)MainMenu.Settings.get(1)*0.01f, (float)MainMenu.Settings.get(1)*0.01f);
    }

    private void RandomQuestions()
    {
        if(check == false) {
            for (int i = 1; i < TotalQuestions+1; ++i) {
                PlayedQuestions.add(i);
            }
            Collections.shuffle(PlayedQuestions);
            check = true;
        }
    }
}
