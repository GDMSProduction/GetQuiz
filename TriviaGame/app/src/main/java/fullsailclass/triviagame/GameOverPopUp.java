package fullsailclass.triviagame;

import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.NotificationCompatBase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
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

    public void configureMainMenu() {
        Button solo = (Button) findViewById(R.id.GoMain);
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverPopUp.this, MainMenu.class));
            }

        });

    }

    public void configureCatergory() {
        Button back = (Button) findViewById(R.id.SelectCat);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }

        });
    }





}
