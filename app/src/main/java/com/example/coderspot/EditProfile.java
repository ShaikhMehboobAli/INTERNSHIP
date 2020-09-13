package com.example.coderspot;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    private FirebaseAuth mAuth;
    String userId;
    FirebaseUser user;
    FirebaseAuth fAuth;
    FirebaseStorage fstore;
    StorageReference storageReference;


    EditText PfullName,PEmail,PPhoneNo,PTeam,PPosition;
    ImageView Editimage;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        userId=mAuth.getCurrentUser().getUid();
        user = mAuth.getCurrentUser();
        fstore = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Intent data = getIntent();
        String fullName = data.getStringExtra("fullName");
        String Email = data.getStringExtra("Email");
        String PhoneNo = data.getStringExtra("PhoneNo");
        String TeamName = data.getStringExtra("TeamName");
        String Position = data.getStringExtra("Position");



        PfullName = findViewById(R.id.PfullName);
        PEmail = findViewById(R.id.PEmail);
        PPhoneNo = findViewById(R.id.PPhoneNo);
        PTeam = findViewById(R.id.PTeam);
        PPosition = findViewById(R.id.PPosition);
        Editimage = findViewById(R.id.Editimage);
        save = findViewById(R.id.btnsave);
        PfullName.setText(fullName);
        PEmail.setText(Email);
        PPhoneNo.setText(PhoneNo);
        PTeam.setText(TeamName);
        PPosition.setText(Position);


        StorageReference profileref = storageReference.child("users/"+mAuth.getCurrentUser().getUid() +"profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(Editimage);
            }
        });


        Editimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PfullName.getText().toString().isEmpty() || PEmail.getText().toString().isEmpty() || PPhoneNo.getText().toString().isEmpty() || PTeam.getText().toString().isEmpty() || PPosition.getText().toString().isEmpty())
                {
                    Toast.makeText(EditProfile.this, "One or Many fields are Empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String email = PEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //DatabaseReference dataref = database.getReference().child("user").
                        DatabaseReference dataref = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("etmail",email);
                        edited.put("etfname",PfullName.getText().toString());
                        edited.put("etphno",PPhoneNo.getText().toString());
                        edited.put("etposition",PPosition.getText().toString());
                        edited.put("etteam",PTeam.getText().toString());
                        dataref.updateChildren(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Fragment_contact.class));
                                finish();
                            }
                        });
//                        Toast.makeText(EditProfile.this, "Email is Changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });






        Log.d(TAG, "onCreate:" + fullName +" "+ Email + " "+ PhoneNo +" "+ TeamName +" "+ Position);

//        FloatingActionButton changepassword = findViewById(R.id.changepassword);

//        changepassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth = FirebaseAuth.getInstance();
//                final EditText changepassword =  new EditText(view.getContext());
//                AlertDialog.Builder passwordRestDialog = new  AlertDialog.Builder(view.getContext());
//                passwordRestDialog.setTitle("Change Password");
//                passwordRestDialog.setMessage("Enter New Password > 6 Digits");
//                passwordRestDialog.setView(changepassword);
//
//                passwordRestDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //extract the mail and link
//                        String newPassword=changepassword.getText().toString();
//                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(EditProfile.this, "Password Change Successfully", Toast.LENGTH_LONG).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(EditProfile.this, "Password Change Failed", Toast.LENGTH_LONG).show();
//                            }
//                        });
//
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                // profileView.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        //upload images to firbase reference
        final StorageReference fileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid() +"profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(Editimage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

}