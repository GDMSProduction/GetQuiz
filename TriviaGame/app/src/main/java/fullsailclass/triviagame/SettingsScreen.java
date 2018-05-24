package fullsailclass.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsScreen extends AppCompatActivity {
    int progressBackMusic = MainMenu.Settings.get(0);
    SeekBar backMusic;
    TextView backmusicNum;


    int progressSoundEffect = MainMenu.Settings.get(1);
    SeekBar soundEffect;
    TextView SoundEffectNum;

    int life = MainMenu.Settings.get(2);
    EditText LifeNums;

    int time = MainMenu.Settings.get(3);
    EditText TimeNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        LifeNums = (EditText) findViewById(R.id.LifeNum);
        TimeNums = (EditText) findViewById(R.id.TimeNum);

        LifeNums.setText(Integer.toString(life));
        TimeNums.setText(Integer.toString(time));


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
    protected void onResume() {
        super.onResume();
        MainMenu.backgroundM.start();
    }

    private void configureBackMusicSeekbar() {
        backMusic = (SeekBar) findViewById(R.id.BackMusicSeekbar);
        backMusic.setMax(100);
        backMusic.setProgress(progressBackMusic);

        backmusicNum = (TextView) findViewById(R.id.BackMusicNumText);
        backmusicNum.setText("" + progressBackMusic);

        backMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBackMusic = i;
                backmusicNum.setText("" + progressBackMusic);
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
        soundEffect = (SeekBar) findViewById(R.id.SoundEffectsSeekbar);
        soundEffect.setMax(100);
        soundEffect.setProgress(progressSoundEffect);

        SoundEffectNum = (TextView) findViewById(R.id.SoundEffectsNumText);
        SoundEffectNum.setText("" + progressSoundEffect);

        soundEffect.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressSoundEffect = i;
                SoundEffectNum.setText("" + progressSoundEffect);
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
        LifeNums = (EditText) findViewById(R.id.LifeNum);
        TimeNums = (EditText) findViewById(R.id.TimeNum);
        backMusic = (SeekBar) findViewById(R.id.BackMusicSeekbar);
        soundEffect = (SeekBar) findViewById(R.id.SoundEffectsSeekbar);
        settingsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainMenu.Settings.set(0, backMusic.getProgress());
                MainMenu.Settings.set(1, soundEffect.getProgress());
                MainMenu.Settings.set(2, Integer.parseInt(LifeNums.getText().toString()));
                MainMenu.Settings.set(3, Integer.parseInt(TimeNums.getText().toString()));
                MainMenu.backgroundM.setVolume((float) MainMenu.Settings.get(0) * 0.01f, (float) MainMenu.Settings.get(0) * 0.01f);
                finish();

            }

        });
    }

    public void configureCreditsButton() {
        Button cred = (Button) findViewById(R.id.CreditsBTN);
        cred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsScreen.this, CreditsScreen.class));
            }
        });
    }


}
