package com.example.lollia.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lollia.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    private LoginButton login_facebook;
    private Button signup;
    private Button login;
    private EditText email_signin;
    private TextInputLayout password_signin;
    GoogleSignInClient googleSignInClient;
    private CallbackManager callbackManager;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthlistener;

    private boolean exit = false;

    private ProgressDialog mprogress;

    @Override
    public void onBackPressed()
    {
        if (exit)
        {
            finish();
        }
        else {
            Toast.makeText(Login.this,"press Back again to exit",Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit=false;
                }
            },3 * 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth= FirebaseAuth.getInstance();
        mauthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if (firebaseAuth.getCurrentUser()!=null)
                {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        };
        login_facebook = (LoginButton) findViewById(R.id.face);
        login_facebook.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
       // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Login.this,"Login success",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });

        mprogress = new ProgressDialog(this);

        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        email_signin = (EditText)findViewById(R.id.email);
        password_signin = (TextInputLayout)findViewById(R.id.signin_pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startlogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        mauth.addAuthStateListener(mauthlistener);
    }

    private void startlogin()
    {
        String email = email_signin.getText().toString();
        String password = password_signin.getEditText().getText().toString();
        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(Login.this, "please insert your email and password", Toast.LENGTH_LONG).show();
        }
        else {
            mprogress.setMessage("signing in .....");
            mprogress.show();

            mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "sign in problem", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Login.this, "sign in success", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Login.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        mprogress.dismiss();
                    }

                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);


    }
    public boolean checklogin(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        return isLoggedIn;
    }
}
