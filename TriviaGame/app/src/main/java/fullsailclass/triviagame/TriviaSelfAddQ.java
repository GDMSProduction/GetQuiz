package fullsailclass.triviagame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TriviaSelfAddQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviaself_create);
        TriviaselfOptions.backgroundMusic.start();
        configureSportsBackButton();
        setTitle("TriviaSelf Create");
    }
    @Override
    protected void onPause() {
        super.onPause();
        TriviaselfOptions.backgroundMusic.pause();
    }
    @Override
    protected  void onResume() {
        super.onResume();
        TriviaselfOptions.backgroundMusic.start();
    }


    public void configureSportsBackButton() {
        Button back = (Button) findViewById(R.id.triviaselfaddBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }

        });
    }


}
