package fullsailclass.triviagame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TriviaSelfAddQ extends AppCompatActivity {
    private EditText AddQuestion;
    private EditText AddA1;
    private EditText AddA2;
    private EditText AddA3;
    private EditText AddA4;
    private RadioButton Rad1;
    private RadioButton Rad2;
    private RadioButton Rad3;
    private RadioButton Rad4;
    private boolean AddedQuestion;
    private int TotalQuestions;


    private FirebaseDatabase DatabaseData;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviaself_create);
        TriviaselfOptions.backgroundMusic.start();
        configureTriviaselfBackButton();
        configureADDButton();
        setTitle("TriviaSelf Create");
        DatabaseData = FirebaseDatabase.getInstance();
        myRef = DatabaseData.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TotalQuestions = (int)dataSnapshot.child("Question").getChildrenCount();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        TriviaselfOptions.backgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TriviaselfOptions.backgroundMusic.start();
    }


    public void configureTriviaselfBackButton() {
        Button back = (Button) findViewById(R.id.triviaselfaddBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void configureADDButton() {
        Button add = (Button) findViewById(R.id.createquestionADDBTN);
        AddQuestion = (EditText) findViewById(R.id.questioneditText);
        AddA1 = (EditText) findViewById(R.id.answer1editText);
        AddA2 = (EditText) findViewById(R.id.answer2editText);
        AddA3 = (EditText) findViewById(R.id.answer3editText);
        AddA4 = (EditText) findViewById(R.id.answer4editText);
        Rad1 = (RadioButton) findViewById(R.id.answer1Radio);
        Rad2 = (RadioButton) findViewById(R.id.answer2Radio);
        Rad3 = (RadioButton) findViewById(R.id.answer3Radio);
        Rad4 = (RadioButton) findViewById(R.id.answer4Radio);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddedQuestion = false;
                String question = AddQuestion.getText().toString();
                String Answer1 = AddA1.getText().toString();
                String Answer2 = AddA2.getText().toString();
                String Answer3 = AddA3.getText().toString();
                String Answer4 = AddA4.getText().toString();
                if (!Answer1.equals("") && !Answer2.equals("") && !Answer3.equals("")
                        && !Answer4.equals("") && !question.equals("") && question.length() > 8
                        && (Rad1.isChecked() || Rad2.isChecked() || Rad3.isChecked() || Rad4.isChecked())) {

                    Character questionmark = question.charAt(question.length() - 1);
                    if (!questionmark.equals('?') && !questionmark.equals('.')) {
                        question += '?';
                    }

                    if (Rad1.isChecked() == true) {
                        Answer1 = '*' + Answer1;
                    }
                    else if (Rad2.isChecked() == true) {
                        Answer2 = '*' + Answer2;
                    }
                    else if (Rad3.isChecked() == true) {
                        Answer3 = '*' + Answer3;
                    }
                    else if (Rad4.isChecked() == true) {
                        Answer4 = '*' + Answer4;
                    }
                    String temp = question.substring(0, 1).toUpperCase() + question.substring(1);
                    question = temp;
                    TotalQuestions = TotalQuestions + 1;
                    String Key = String.valueOf(TotalQuestions);
                    myRef.child("Question").child(Key).child("Q").setValue(question);
                    myRef.child("Question").child(Key).child("A1").setValue(Answer1);
                    myRef.child("Question").child(Key).child("A2").setValue(Answer2);
                    myRef.child("Question").child(Key).child("A3").setValue(Answer3);
                    myRef.child("Question").child(Key).child("A4").setValue(Answer4);
                    AddedQuestion = true;
                    toastMessage("Question Added");
                } else {
                    if (question.length() < 8) {
                        toastMessage("Min Question Characters 8");
                    }
                    if(!Rad1.isChecked() || !Rad2.isChecked() || !Rad3.isChecked() || !Rad4.isChecked())
                    {
                        toastMessage("Select A Correct Answer");
                    }
                    else{
                        toastMessage("Please Add A Valid Question");
                    }
                }
                if(AddedQuestion == true) {
                    Rad1.setChecked(false);
                    Rad2.setChecked(false);
                    Rad3.setChecked(false);
                    Rad4.setChecked(false);
                    AddQuestion.setText("");
                    AddA1.setText("");
                    AddA2.setText("");
                    AddA3.setText("");
                    AddA4.setText("");
                }

            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
