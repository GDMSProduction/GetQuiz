package fullsailclass.triviagame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainMenu extends AppCompatActivity {

MediaPlayer backgroundM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        configureMultiplayerButton();
        configureSoloButton();
        configureSettingsButton();
        configureCreditsButton();



        backgroundM = MediaPlayer.create(MainMenu.this, R.raw.backgroundmusic);
        backgroundM.setLooping(true);
        backgroundM.start();
        backgroundM.setVolume(1.00f , 1.00f);
    }


    //fix this i dont know how to pause the music when the user does somethings

    @Override
    protected void onPause(){
        super.onPause();
        backgroundM.release();
        finish();
    }




    public void configureSoloButton() {
        Button solo = (Button) findViewById(R.id.Solo);
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, SoloScreen.class));
            }

        });
    }

    public void configureMultiplayerButton(){
        Button multi = (Button) findViewById(R.id.Multplayer);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, MultiplayerScreen.class));
            }
        });
    }

    public void configureSettingsButton(){
        Button settings = (Button) findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, SettingsScreen.class));
            }
        });
    }

    public void configureCreditsButton(){
        Button cred = (Button) findViewById(R.id.Credits);
        cred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, CreditsScreen.class));
            }
        });
    }


}
