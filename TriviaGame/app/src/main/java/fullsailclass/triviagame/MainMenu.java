package fullsailclass.triviagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        configureMultiplayerButton();
        configureSoloButton();
        configureSettingsButton();
        setTitle("Main Menu");
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


}
