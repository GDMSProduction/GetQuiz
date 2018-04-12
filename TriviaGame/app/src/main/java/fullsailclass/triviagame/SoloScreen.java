package fullsailclass.triviagame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SoloScreen extends AppCompatActivity {
    MediaPlayer backgroundM;
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
            }

        });
    }

}

