package com.example.lollia.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lollia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private Button signin;
    private CircleImageView pic;
    Uri uri_image;
    private EditText username_signup;
    private EditText email_signup;
    private TextInputLayout pass_signup;
    private TextInputLayout conf_pass_signup;
    private Button create_account;
    private FirebaseAuth mauth;
    private DatabaseReference mdatabase;
    private static final int GALLERY_REQUEST = 1;
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
            Toast.makeText(Signup.this,"press Back again to exit",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_signup);
        mauth = FirebaseAuth.getInstance();
        mprogress = new ProgressDialog(this);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        username_signup = (EditText)findViewById(R.id.user);
        email_signup = (EditText)findViewById(R.id.edit_signup_email);
        pass_signup = (TextInputLayout)findViewById(R.id.edit_signup_pass);
        conf_pass_signup = (TextInputLayout)findViewById(R.id.edit_signup_pass_confirm);
        create_account = (Button)findViewById(R.id.createacc);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
        signin = (Button)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        pic = (CircleImageView)findViewById(R.id.img_signup_user);

    }

    private void startRegister()
    {
        final String name = username_signup.getText().toString().trim();
        String email = email_signup.getText().toString().trim();
        String password = pass_signup.getEditText().getText().toString().trim();
        String con_password = conf_pass_signup.getEditText().getText().toString().trim();
         String img_signup = pic.getResources().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)
                ||TextUtils.isEmpty(con_password))
        {
            Toast.makeText(Signup.this,"please complete your info",Toast.LENGTH_SHORT).show();
        }
      /*  else if(isEmailValid()==true)
        {
            Toast.makeText(this, "this email is invalid or used", Toast.LENGTH_SHORT).show();

        }*/
        else if (uri_image==null)
        {
            Toast.makeText(this, "please select your profile image", Toast.LENGTH_SHORT).show();
        }
        else if (password.length()<6)
        {
            Toast.makeText(this, "Password length must br greater than 6 chars", Toast.LENGTH_SHORT).show();
        }
        else if (!con_password.equals(password))
        {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mprogress.setMessage("singing up .......");
            mprogress.show();
            mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                       // FirebaseUser user_id= mauth.getCurrentUser();


                       // DatabaseReference current_user_db= mdatabase.child(user_id);
                        Intent intent = new Intent(Signup.this,Login.class);
                        startActivity(intent);
                       // current_user_db.child("image").setValue(img_signup);
                        mprogress.dismiss();
                        sendEmailVerification();


                    }

                }
            });

        }


    }
    private boolean isEmailValid() {
        String email = email_signup.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void sendEmailVerification()
    {
        FirebaseUser user = mauth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "email verification is sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup.this, task.getException().getMessage() + "", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser curr_user = mauth.getCurrentUser();

    }

    public void insertImage(View view) {

     CropImage.activity().start(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri_image = result.getUri();
                //  Toast.makeText(this, uri_image+"", Toast.LENGTH_SHORT).show();
                Picasso.with(this).load(uri_image).into(pic);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
            }
        }


    }
}

