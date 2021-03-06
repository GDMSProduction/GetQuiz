package fullsailclass.triviagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreditsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_screen);

        configureCreditBackButton();
        setTitle("Credits");
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
    public void configureCreditBackButton() {
        Button creditsBack = (Button) findViewById(R.id.CreditsBackBTN);
        creditsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }

        });
    }


}
