package fullsailclass.triviagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        configureSettingBackButton();
        configureCreditsButton();
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
