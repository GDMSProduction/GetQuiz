package fullsailclass.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by JJOP on 3/6/2018.
 */

public class MultiplayerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_screen);

        configureBackButton();
        configureFindMatchButton();
        setTitle("Multiplayer Menu");
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
    public void configureBackButton() {
        Button backMult = (Button) findViewById(R.id.multiBackBtn);
        backMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }
    public void configureFindMatchButton()
    {
        Button findMatch = (Button) findViewById(R.id.multiQuickmatchBtn);
        findMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiplayerScreen.this, Multiplayer.class);
                startActivity(intent);
            }
        });
    }
}