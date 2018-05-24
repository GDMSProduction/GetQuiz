package fullsailclass.triviagame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TriviaselfOptions extends AppCompatActivity {

    static MediaPlayer backgroundMusic;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviaself_screen);
        configureAddQuestionBTN();
        configureTriviaSelfBackBTN();
        BackgroundMusic();
        configurePlayTriviaself();

        setTitle("TriviaSelf Options");


    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundMusic.start();
    }

    public void configureAddQuestionBTN() {
        Button settings = (Button) findViewById(R.id.createquestionBTN);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                if (mAuth.getCurrentUser() != null) {
                    startActivity(new Intent(TriviaselfOptions.this, TriviaSelfAddQ.class));
                } else {
                    toastMessage("You Need To Sign In First");
                }
            }
        });
    }

    public void configurePlayTriviaself() {
        Button play = (Button) findViewById(R.id.playquestionsBTN);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();
                if (mAuth.getCurrentUser() != null) {
                    startActivity(new Intent(TriviaselfOptions.this, PlayTriviaself.class));
                } else {
                    toastMessage("You Need To Sign In First");
                }
            }
        });
    }

    public void configureTriviaSelfBackBTN() {
        Button back = (Button) findViewById(R.id.triviaselfBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                backgroundMusic.pause();

            }

        });
    }


    public void BackgroundMusic() {
        backgroundMusic = MediaPlayer.create(TriviaselfOptions.this, R.raw.triviaself);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        backgroundMusic.setVolume((float) MainMenu.Settings.get(0) * 0.20f, (float) MainMenu.Settings.get(0) * 0.20f);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
