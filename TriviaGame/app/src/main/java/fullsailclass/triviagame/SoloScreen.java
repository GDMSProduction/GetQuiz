package fullsailclass.triviagame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SoloScreen extends AppCompatActivity {

    int Category = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_screen);

        /*
        backgroundM = MediaPlayer.create(SoloScreen.this, R.raw.backgroundmusic);
        backgroundM.setLooping(true);
        backgroundM.start();
        backgroundM.setVolume(1.00f , 1.00f);
        */
        configureSGBackButton();
        configureEntertainmentButton();
        configureGamesButton();
        configureHistoryButton();
        configureMusicButton();
        configureScienceButton();
        configureSportsButton();
        configurePopcultureButton();

        setTitle("Solo Menu");

        //Broadcast
        Intent GameOver = new Intent("finish_GameOver");
        sendBroadcast(GameOver);

        Intent Questions = new Intent("finish_Questions");
        sendBroadcast(Questions);


        //Receiver
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_solo_activity")) {
                    finish();
                    // DO WHATEVER YOU WANT.
                }


            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_solo_activity"));


        BroadcastReceiver categoryReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("restart")) {
                    if (Category ==1) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "entertainment_questions.txt");
                        startActivity(enter);
                    }else if (Category ==2) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "game_questions.txt");
                        startActivity(enter);
                    }else if (Category ==3) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "history_questions.txt");
                        startActivity(enter);
                    }else if (Category ==4) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "music_questions.txt");
                        startActivity(enter);
                    }else if (Category ==5) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "science_questions.txt");
                        startActivity(enter);
                    }else if (Category ==6) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "sports_questions.txt");
                        startActivity(enter);
                    }else if (Category ==7) {
                        Intent enter = new Intent(SoloScreen.this, QuestionsScreen.class);
                        enter.putExtra("Category", "popculture_questions.txt");
                        startActivity(enter);
                    }
                    // DO WHATEVER YOU WANT.
                }


            }
        };
        registerReceiver(categoryReceiver, new IntentFilter("restart"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        MainMenu.backgroundM.pause();
    }
    @Override
    protected  void onResume(){
        super.onResume();
        MainMenu.backgroundM.start();
    }

    public void configureSGBackButton() {
        final Button back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }

    public void configureEntertainmentButton() {
        Button entertainment = (Button) findViewById(R.id.Entertainment);
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "entertainment_questions.txt");
                startActivity(intent);

                Category = 1;
            }

        });
    }

    public void configureGamesButton() {
        Button games = (Button) findViewById(R.id.Games);
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "game_questions.txt");
                startActivity(intent);

                Category = 2;

            }

        });
    }

    public void configureHistoryButton() {
        Button history = (Button) findViewById(R.id.History);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "history_questions.txt");
                startActivity(intent);

                Category = 3;
            }

        });
    }

    public void configureMusicButton() {
        Button music = (Button) findViewById(R.id.Music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "music_questions.txt");
                startActivity(intent);

                Category = 4;
            }

        });
    }

    public void configureScienceButton() {
        Button science = (Button) findViewById(R.id.Science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "science_questions.txt");
                startActivity(intent);

                Category = 5;
            }

        });
    }

    public void configureSportsButton() {
        Button sports = (Button) findViewById(R.id.Sports);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "sports_questions.txt");
                startActivity(intent);

                Category = 6;
            }

        });
    }

    public void configurePopcultureButton() {
        Button sports = (Button) findViewById(R.id.Popculture);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloScreen.this, QuestionsScreen.class);
                intent.putExtra("Category", "popculture_questions.txt");
                startActivity(intent);

                Category = 7;
            }

        });
    }

}

