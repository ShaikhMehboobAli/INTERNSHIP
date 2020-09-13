package com.example.coderspot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


@SuppressWarnings("ALL")
public class register extends AppCompatActivity {

    EditText etmail, etpass, etpass2, etfname, etteam, etphno, etposition;
    ProgressBar progressBar;
    Button btnregister;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hooks
        etmail = findViewById(R.id.etmail);
        etpass = findViewById(R.id.etpass);
        etpass2 = findViewById(R.id.etpass2);
        etfname = findViewById(R.id.etfname);
        etteam = findViewById(R.id.etteam);
        etphno = findViewById(R.id.etphno);
        etposition = findViewById(R.id.etposition);

        //progresbar hook
        progressBar = findViewById(R.id.progressBar);

        //btn hooks
        btnregister = findViewById(R.id.btnregister);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = etfname.getText().toString().trim();
                String email = etmail.getText().toString().trim();
                String phono = etphno.getText().toString().trim();
                String team = etteam.getText().toString().trim();
                String position = etposition.getText().toString().trim();
                String pass = etpass.getText().toString().trim();
                String pass2 = etpass2.getText().toString().trim();


                if (TextUtils.isEmpty(fullname)) {
                    etfname.setError("Enter this field");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etmail.setError("Enter the Valid Email");
                    return;
                }
                if (TextUtils.isEmpty(phono)) {
                    etphno.setError("Enter this field");
                    return;
                }
                if (TextUtils.isEmpty(team)) {
                    etteam.setError("Enter this field");
                    return;
                }
                if (TextUtils.isEmpty(position)) {
                    etposition.setError("Enter this field");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    etpass.setError("Enter this field");
                    return;
                }
                if (TextUtils.isEmpty(pass2)) {
                    etpass2.setError("Enter this field");
                    return;
                }

                //        //password verify and signup
                if (pass.equals(pass2)) {

                    if (pass.length() > 6) {


                        if (phono.length() == 10) {


                            progressBar.setVisibility(View.VISIBLE);
                            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {


                                                // Sign in success, update UI with the signed-in user's information
                                                Member_user reg = new Member_user(
                                                        fullname,
                                                        email,
                                                        team,
                                                        position,
                                                        phono

                                                );
                                                FirebaseDatabase.getInstance().getReference("user")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(reg).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {


                                                                        Toast.makeText(register.this, "Resister Successfull. Please check your email for Verification", Toast.LENGTH_LONG).show();
                                                                        startActivity(new Intent(register.this, tabactivity.class));

                                                                    } else {

                                                                        Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                                        //startActivity(new Intent(register.this, tabactivity.class));
                                                                        progressBar.setVisibility(View.GONE);
                                                                    }
                                                                }
                                                            });


                                                        } else {
                                                            Toast.makeText(register.this, "Resister Not Successfull", Toast.LENGTH_LONG).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
                                                startActivity(new Intent(register.this, tabactivity.class));

                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                            }

                                            // ...
                                        }
                                    });
                        } else {
                            etphno.setError("Enter a valid Phone no");
//                            Toast.makeText(register.this, "Enter a valid Phone no", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        etpass2.setError("Enter this fieldPasswords length must be greather then 6 digits");
                        etpass.setError("Passwords length must be greather then 6 digits");
                        //Toast.makeText(register.this, "Passwords length must be greather then 6 digits", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    etpass2.setError("Passwords do not match");
                    etpass.setError("Passwords do not match");
                    //  Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


//                firebaseAuth.createUserWithEmailAndPassword(email, pass)
//                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    Member_user reg = new Member_user(
//                                            fullname,
//                                            email,
//                                            team,
//                                            position,
//                                            phono
//                                    );
//
//                                    FirebaseDatabase.getInstance().getReference("Member")
//                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                            .setValue(reg).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            Toast.makeText(register.this, "Resister Successfull. Please check your email for Verification", Toast.LENGTH_LONG).show();
//                                            startActivity(new Intent(getApplicationContext(), tabactivity.class));
//                                        }
//                                    });
//
//                                } else {
//
//                                }
//
//                                // ...
//                            }
//                        });
//
//
//            }
//        });
//    }
//}


//strt here prev versn

//    private static final String TAG = "register";
//    private TextView mDisplayDate;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//    public static boolean isAlpha(String s) {
//        if (s == null) {
//            return false;
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private FirebaseAuth firebaseAuth;
//
//    private EditText etmail,etpass,etpass2,etfname,etteam,etphno,etposition;
//    private TextView etdob;
//    private ProgressBar progressBar;
//
//    Button btnregister;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//
//
//        //dob selection
//
//        setContentView(R.layout.activity_register);
//        mDisplayDate = (TextView) findViewById(R.id.etdob);
//
//        mDisplayDate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Calendar dob = Calendar.getInstance();
//                int year = dob.get(Calendar.YEAR);
//                int month = dob.get(Calendar.MONTH);
//                int day = dob.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog db = new DatePickerDialog(
//                        register.this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
//                        mDateSetListener,
//                        year, month, day);
//                db.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                db.show();
//
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
//
//                String date = month + "/" + day + "/" + year;
//                mDisplayDate.setText(date);
//            }
//        };
//
//        //registration form
//
//        etmail =  findViewById(R.id.etmail);
//        etpass =  findViewById(R.id.etpass);
//        etfname =  findViewById(R.id.etfname);
//        etteam =  findViewById(R.id.etteam);
//        etposition = findViewById(R.id.etposition);
//        etdob = (TextView) findViewById(R.id.etdob);
//        etphno =  findViewById(R.id.etphno);
//        etpass2 =  findViewById(R.id.etpass2);
//        progressBar = findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);
//
//        btnregister =  findViewById(R.id.btnregister);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        btnregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (btnregister.getId()){
//                    case R.id.btnregister:
//                        registerUser();
//                        break;
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (firebaseAuth.getCurrentUser() != null) {
//            startActivity(new Intent(register.this, tabactivity.class));
//        }
//    }
//    //  btnregister.setOnClickListener(new View.OnClickListener() {
//    //  @Override
//    // public void onClick(View v) {
//
//    private void registerUser(){
//
//
//        //getting all values frm txt field
//
//        // final String lname = etlname.getText().toString().trim();
//        final String fname = etfname.getText().toString().trim();
//        final String email = etmail.getText().toString().trim();
//        final String phno = etphno.getText().toString().trim();
//        final String dob = etdob.getText().toString().trim();
//        final String team = etteam.getText().toString().trim();
//        final String position = etposition.getText().toString().trim();
//        String password = etpass.getText().toString().trim();
//        String password2 = etpass2.getText().toString().trim();
//
//        if (TextUtils.isEmpty(fname)){
//            etfname.setError("Enter this field");
//            return;
//        }
//              /*  if (TextUtils.isEmpty(lname)){
//
//                    Toast.makeText(register.this, "This Field Cannot Be Empty" ,Toast.LENGTH_SHORT).show();
//                    return;
//                }*/
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            etmail.setError("Enter the Valid Email");
//            return;
//        }
//        if (TextUtils.isEmpty(phno)){
//            etphno.setError("Enter this field");
//            return;
//        }
//        if (TextUtils.isEmpty(dob)){
//            etdob.setError("Enter this field");
//            return;
//        }
//        if (TextUtils.isEmpty(password)){
//            etpass.setError("Enter this field");
//            return;
//        }
//        if (TextUtils.isEmpty(password2)){
//            etpass2.setError("Enter this field");
//            return;
//        }
//
////                if(password.length()<6){
////                    Toast.makeText(register.this, "Password Too Short" , Toast.LENGTH_SHORT).show();
////                }
////                if (phno.length() != 10){
////
////                    Toast.makeText(register.this, "Phone No must be 0f 10 digit" , Toast.LENGTH_SHORT).show();
////                }
//
//
//
//
//        //password verify and signup
//        if(password.equals(password2)) {
//
//            if(password.length()>6) {
//
//
//                if (phno.length() == 10) {
//
//
//                    progressBar.setVisibility(View.VISIBLE);
//                    firebaseAuth.createUserWithEmailAndPassword(email, password)
//                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    progressBar.setVisibility(View.GONE);
//                                    if (task.isSuccessful()) {
//
//
//                                        // Sign in success, update UI with the signed-in user's information
//                                        user userreg = new user(
//                                                fname,
//                                                email,
//                                                phno,
//                                                dob,
//                                                team,
//                                                position
//
//                                        );
//                                        FirebaseDatabase.getInstance().getReference("user")
//                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                .setValue(userreg).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                if (task.isSuccessful()) {
//                                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                        @Override
//                                                        public void onComplete(@NonNull Task<Void> task) {
//                                                            if(task.isSuccessful()) {
//
//
//                                                                Toast.makeText(register.this, "Resister Successfull. Please check your email for Verification", Toast.LENGTH_LONG).show();
//                                                                startActivity(new Intent(register.this, tabactivity.class));
//
//                                                            }else{
//
//                                                                Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                                                                //startActivity(new Intent(register.this, tabactivity.class));
//                                                                progressBar.setVisibility(View.GONE);
//                                                            }
//                                                        }
//                                                    });
//
//
//
//                                                } else {
//                                                    Toast.makeText(register.this, "Resister Not Successfull", Toast.LENGTH_LONG).show();
//                                                    progressBar.setVisibility(View.GONE);
//                                                }
//                                            }
//                                        });
//
//                                    } else {
//                                        // If sign in fails, display a message to the user.
//                                        Toast.makeText(register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    // ...
//                                }
//                            });
//                }
//                else
//                {
//                    etphno.setError("Enter a valid Phone no");
////                            Toast.makeText(register.this, "Enter a valid Phone no", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//            else
//            {
//                etpass2.setError("Enter this fieldPasswords length must be greather then 6 digits");
//                etpass.setError("Passwords length must be greather then 6 digits");
//                //Toast.makeText(register.this, "Passwords length must be greather then 6 digits", Toast.LENGTH_SHORT).show();
//
//            }
//
//        }
//        else
//        {
//            etpass2.setError("Passwords do not match");
//            etpass.setError("Passwords do not match");
//            //  Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
//

