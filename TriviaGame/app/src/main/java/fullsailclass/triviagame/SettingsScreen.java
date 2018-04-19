package fullsailclass.triviagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsScreen extends AppCompatActivity {
    int progressBackMusic=100;
    SeekBar backMusic;
    TextView backmusicNum;

    int progressSoundEffect=100;
    SeekBar soundEffect;
    TextView SoundEffectNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        configureSettingBackButton();
        configureCreditsButton();
        configureBackMusicSeekbar();
        configureSoundEffectSeekbar();


        setTitle("Settings");
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

    private void configureBackMusicSeekbar() {
        backMusic= (SeekBar) findViewById(R.id.BackMusicSeekbar);
        backMusic.setMax(100);
        backMusic.setProgress(progressBackMusic);

        backmusicNum = (TextView) findViewById(R.id.BackMusicNumText);
        backmusicNum.setText(""+progressBackMusic);

        backMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBackMusic =i;
                backmusicNum.setText(""+progressBackMusic);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    private void configureSoundEffectSeekbar() {
        soundEffect= (SeekBar) findViewById(R.id.SoundEffectsSeekbar);
        soundEffect.setMax(100);
        soundEffect.setProgress(progressSoundEffect);

        SoundEffectNum = (TextView) findViewById(R.id.SoundEffectsNumText);
        SoundEffectNum.setText(""+progressSoundEffect);

        soundEffect.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressSoundEffect =i;
                SoundEffectNum.setText(""+progressSoundEffect);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void configureSettingBackButton() {
        Button settingsBack = (Button) findViewById(R.id.SettingsBackBTN);
        settingsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }

        });
    }

    public void configureCreditsButton(){
        Button cred = (Button) findViewById(R.id.CreditsBTN);
        cred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsScreen.this, CreditsScreen.class));
            }
        });
    }


}
