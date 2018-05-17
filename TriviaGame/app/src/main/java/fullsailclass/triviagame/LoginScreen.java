package fullsailclass.triviagame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginScreen extends AppCompatActivity {

    private static final String TAG = "LoginScreen";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button btnSignIn, btnSignOut;
    private EditText mCreateEmailField;
    private EditText mCreatePasswordField;
    private EditText mRePasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mEmailField = findViewById(R.id.Email_text);
        mPasswordField = findViewById(R.id.Password_text);
        btnSignIn = findViewById(R.id.Email_signin_BTN);
        btnSignOut = findViewById(R.id.Email_signout_BTN);
        mCreateEmailField = findViewById(R.id.CreateEmail_text);
        mCreatePasswordField = findViewById(R.id.CreatePassword_text);
        mRePasswordField = findViewById(R.id.Re_Password_text);

        setTitle("Login/SignUp");

        configureLoginBackButton();
        configureCreateBTN();


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //User signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    //User signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out");
                }
            }
        };


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                String pass = mPasswordField.getText().toString();

                if (!email.equals("") && !pass.equals("")) {
                    loginAccount(email, pass);


                } else {
                    toastMessage("You didn't fill in all the fields.");
                }

            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Signing Out");
            }
        });


//stupid

    }

    @Override
    public void onStart() {
        super.onStart();


        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void configureLoginBackButton() {
        final Button back = (Button) findViewById(R.id.LoginBackBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }

    public void loginAccount(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information



                        } else {
                            // If sign in fails, display a message to the user.

                            toastMessage("Authentication failed");

                        }

                        // ...
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
                String stringEmail = mCreateEmailField.getText().toString();
                String stringPassword = mCreatePasswordField.getText().toString();
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

