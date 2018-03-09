package fullsailclass.triviagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SoloScreen extends AppCompatActivity {
    List<QandA> list = new ArrayList<QandA>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_screen);

        configureSGBackButton();
        configureEntertainmentButton();
        configureGamesButton();
        configureHistoryButton();
        configureMusicButton();
        configureScienceButton();
        configureSportsButton();
    }

    public void configureSGBackButton() {
        Button back = (Button) findViewById(R.id.Back);
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
                startActivity(new Intent(SoloScreen.this, QuestionsScreen.class));
            }

        });
    }

    public void configureGamesButton() {
        Button games = (Button) findViewById(R.id.Games);
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoloScreen.this, QuestionsScreen.class));
                ReadQuestion r = new ReadQuestion();
                OpenFile("game_questions.txt");
            }

        });
    }

    public void configureHistoryButton() {
        Button history = (Button) findViewById(R.id.History);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoloScreen.this, QuestionsScreen.class));
            }

        });
    }

    public void configureMusicButton() {
        Button music = (Button) findViewById(R.id.Music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoloScreen.this, QuestionsScreen.class));
            }

        });
    }

    public void configureScienceButton() {
        Button science = (Button) findViewById(R.id.Science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoloScreen.this, QuestionsScreen.class));
            }

        });
    }

    public void configureSportsButton() {
        Button sports = (Button) findViewById(R.id.Sports);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoloScreen.this, QuestionsScreen.class));
            }

        });
    }


    public void OpenFile(String test) {

        Scanner s = null;
        String question, a1, a2, a3, a4;
        try {
            s = new Scanner(new BufferedReader(new InputStreamReader(getAssets().open(test), "UTF-8")));

            while (s.hasNext()) {

                s.useDelimiter("-");
                question = s.next();
                a1 = s.next();
                a2 = s.next();
                a3 = s.next();
                a4 = s.next();
                if (a1.charAt(0) == '*') {
                    a1 = a1.substring(1);
                }
                if (a2.charAt(0) == '*') {
                    a2 = a2.substring(1);
                }
                if (a3.charAt(0) == '*') {
                    a3 = a3.substring(1);
                }
                if (a4.charAt(0) == '*') {
                    a4 = a4.substring(1);
                }
                QandA temp = new QandA(question, a1, a2,a3,a4);
                list.add(temp);
                //System.out.println(question + " + " + a1 + " + " + a2 + " + " + a3 + " + " + a4);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("could not find file");
        }

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getQuestion() +" - "+  list.get(i).getAnswer1() +" - "+list.get(i).getAnswer2() +" - "+ list.get(i).getAnswer3()
            +" - "+list.get(i).getAnswer4());
        }
    }
}
