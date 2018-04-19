package fullsailclass.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GameOverPopUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_pop_up);

        configureMainMenu();

        configureCatergory();

    };
    @Override
    protected void onPause() {
        super.onPause();
       QuestionsScreen.backgroundMusic.pause();
    }
    @Override
    protected  void onResume(){
        super.onResume();
        QuestionsScreen.backgroundMusic.start();
    }

    public void configureMainMenu() {
        Button solo = (Button) findViewById(R.id.GoMain);
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverPopUp.this, MainMenu.class));
                MainMenu.backgroundM.release();
                QuestionsScreen.backgroundMusic.release();
            }

        });

    }

    public void configureCatergory() {
        Button back = (Button) findViewById(R.id.SelectCat);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsScreen.backgroundMusic.release();
                MainMenu.backgroundM.start();
                finish();

            }

        });
    }





}
