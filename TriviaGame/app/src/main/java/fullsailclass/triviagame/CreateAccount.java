package fullsailclass.triviagame;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    private static final String TAG = "CreateAccount";
    private FirebaseAuth mAuth;

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mRePasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
        mEmailField = findViewById(R.id.CreateEmail_text);
        mPasswordField = findViewById(R.id.CreatePassword_text);
        mRePasswordField = findViewById(R.id.Re_Password_text);

        setTitle("Create Account");
        configureCreateBTN();
        configureCreateBackButton();

    }

    public void configureCreateBackButton() {
        final Button back = (Button) findViewById(R.id.CreateBackBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }



    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            toastMessage("Account successfully created");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            toastMessage("Authentication failed");

                        }

                        // ...
                    }
                });


    }

    public void configureCreateBTN() {
        Button multi = (Button) findViewById(R.id.CreateBTN);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringEmail = mEmailField.getText().toString();
                String stringPassword = mPasswordField.getText().toString();
                String stringRePassword = mRePasswordField.getText().toString();

                if(stringPassword.equals(stringRePassword)){
                    createAccount(stringEmail,stringPassword);
                }else{
                    toastMessage("Passwords didn't match");
                }

            }
        });
    }
    //add a toast to show when successfully signed in

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
