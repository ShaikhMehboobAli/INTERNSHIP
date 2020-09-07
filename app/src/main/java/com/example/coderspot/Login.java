package com.example.coderspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static int time = 500;
    RelativeLayout rellayout, rellayout2;
    Handler handler = new Handler();
    private FirebaseAuth mAuth;
    Button btnlogin;
    EditText etpassword, etusername;
    TextView btnregister,forgotps;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            // User is signed in (getCurrentUser() will be null if not signed in)
            Intent intent = new Intent(Login.this, tabactivity.class);
            startActivity(intent);
            finish();
        }

        final RelativeLayout rellayout = findViewById(R.id.rellayout);
        final RelativeLayout rellayout2 = findViewById(R.id.rellayout2);
        final EditText etusername = findViewById(R.id.etusername);
        final EditText etpassword = findViewById(R.id.etpassword);
        Button btnlogin = findViewById(R.id.btnlogin);
        progressBar =findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
//        forgotps=findViewById(R.id.forgotps);

        TextView btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, register.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                final FirebaseAuth mAuth = FirebaseAuth.getInstance();

                String email = etusername.getText().toString().trim();
                String password = etpassword.getText().toString().trim();

                if (TextUtils.isEmpty(password)){
                    etpassword.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    etusername.setError("Email is required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etusername.setError("Enter a valid Email");
                    return;
                }
                if (password.length()<6) {
                    etpassword.setError("Password must v greater then 6 digits");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                Toast.makeText(Login.this, "LOADING....", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (mAuth.getCurrentUser().isEmailVerified()) {

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(Login.this, tabactivity.class));


                                    }else{

                                        Toast.makeText(Login.this, "Please Verify Your Email Address",
                                                Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);

                                }

                                // ...
                            }
                        });
            }


        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rellayout.setVisibility(View.VISIBLE);
                rellayout2.setVisibility(View.VISIBLE);
            }
        }, time);

        //forgot password reset link

//        forgotps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final EditText restMail =  new EditText(view.getContext());
//                AlertDialog.Builder passwordRestDialog = new  AlertDialog.Builder(view.getContext());
//                passwordRestDialog.setTitle("Reset Password ?");
//                passwordRestDialog.setMessage("Enter Your Email To Received Reset Link");
//                passwordRestDialog.setView(restMail);
//
//                passwordRestDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //extract the mail and link
//                        String mail=restMail.getText().toString();
//                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(Login.this, "Reset Link Sent To Your Email." ,Toast.LENGTH_LONG).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Login.this, "Error ! Reset Link Not Sent." + e.getMessage() ,Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//                });
//
//                passwordRestDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //close the dialog
//                    }
//                });
//
//                passwordRestDialog.create().show();
//
//            }
//        });

    }
}
