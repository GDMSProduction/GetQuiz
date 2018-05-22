package fullsailclass.triviagame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainMenu extends AppCompatActivity {
    static MediaPlayer backgroundM;
    //This for the settings
    //public static SharedPreferences mPreferences;
    //public static SharedPreferences.Editor mEditor;

    static List<Integer> Settings = new ArrayList<Integer>();
    //Settings
    //0 = BGMusic Value
    //1 = FX Value
    //2 = Life Value
    //3 = Time Value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        configureMultiplayerButton();
        configureSoloButton();
        configureTriviaSelfButton();
        configureSettingsButton();
        configureLoginBTN();
        setTitle("Main Menu");
        ReadSettings();

        configureCreditsButton();

       // mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mEditor = mPreferences.edit();

        //ReadSettings();
       // checkSharedPReferences();
        backgroundM = MediaPlayer.create(MainMenu.this, R.raw.backgroundmusic);
        backgroundM.setLooping(true);
        backgroundM.start();
        backgroundM.setVolume(0.20f, 0.20f);


        Intent intent = new Intent("finish_GameOver");
        sendBroadcast(intent);

        Intent Victory = new Intent("finish_Victory");
        sendBroadcast(Victory);

        Intent splash = new Intent("finish_Splash");
        sendBroadcast(splash);

        Intent questions = new Intent("finish_Questions");
        sendBroadcast(questions);

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_MainMenu")) {
                    finish();
                    // DO WHATEVER YOU WANT.
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_MainMenu"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundM.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundM.start();
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

    public void configureMultiplayerButton() {
        Button multi = (Button) findViewById(R.id.Multplayer);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, MultiplayerScreen.class));
            }
        });
    }
    public void configureTriviaSelfButton() {
        Button settings = (Button) findViewById(R.id.TriviaSelf);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, TriviaselfOptions.class));
            }
        });
    }

    public void configureSettingsButton() {
        Button settings = (Button) findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, SettingsScreen.class));
            }
        });
    }

    public void configureCreditsButton() {
        Button cred = (Button) findViewById(R.id.Credits);
        cred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, CreditsScreen.class));
            }
        });
    }

    public void configureLoginBTN() {
        Button solo = (Button) findViewById(R.id.Login);
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, LoginScreen.class));
            }

        });
    }

    /*
    private void checkSharedPReferences(){
        Integer BGSound = mPreferences.getInt("BackgroundSoundPref",100);
        Integer FXSound = mPreferences.getInt("FXSoundPref",100);
        Integer LifeTotal = mPreferences.getInt("LifeTotalPref",3);
        Integer TotalTime = mPreferences.getInt("TotalTimePref",15);
    }
    */

    //Settings
    public void ReadSettings(){

        Scanner s = null;
        String temp;
        try {
            s = new Scanner(new BufferedReader(new InputStreamReader(getAssets().open("settings.txt"), "UTF-8")));

            while (s.hasNextLine()) {
                temp = s.nextLine();
                Settings.add(Integer.parseInt(temp));
            }
            s.close();
        } catch (Exception e) {
            System.out.println("could not find file");
        }


    }
}

