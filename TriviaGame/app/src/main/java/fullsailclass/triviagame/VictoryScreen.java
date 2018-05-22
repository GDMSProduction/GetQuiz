package fullsailclass.triviagame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VictoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_screen);

        configureMainMenu();
        configureCatagoryBTN();
        configureRestartBTN();

        Intent Questions = new Intent("finish_Questions");
        sendBroadcast(Questions);

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_Victory")) {
                    finish();
                    // DO WHATEVER YOU WANT.
                }


            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_Victory"));
    }
    public void configureMainMenu() {
        Button mainMenu = (Button) findViewById(R.id.GoMainVictory);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent solointent = new Intent("finish_solo_activity");
                sendBroadcast(solointent);

                Intent MainIntent = new Intent("finish_MainMenu");
                sendBroadcast(MainIntent);

                Intent gotoScreenVar = new Intent(VictoryScreen.this, MainMenu.class);



                startActivity(gotoScreenVar);
                MainMenu.backgroundM.release();
                QuestionsScreen.backgroundMusic.release();
            }

        });

    }


    public void configureCatagoryBTN() {
        Button back = (Button) findViewById(R.id.SelectCatVictory);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsScreen.backgroundMusic.release();
                MainMenu.backgroundM.start();

                Intent solo = new Intent("finish_solo_activity");
                sendBroadcast(solo);

                Intent gotoSolo = new Intent(VictoryScreen.this, SoloScreen.class);
                startActivity(gotoSolo);
            }

        });
    }


    public void configureRestartBTN() {
        Button restart = (Button) findViewById(R.id.RestartVictory);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Restart = new Intent("restart");
                sendBroadcast(Restart);
            }

        });
    }




}
