package com.example.coderspot;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Fragment_contact extends Fragment {
    TextView tvemail, tvnum, tvname, tvdob, verifymsg, tvteam, tvposition;
    Button resendcode, profileimage;
    ImageView profileView;


    StorageReference storageReference;
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference fref = FirebaseDatabase.getInstance().getReference("user").child(user.getUid());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        final TextView tvemail = view.findViewById(R.id.tvemail);
        final TextView tvnum = view.findViewById(R.id.tvnumber);
        final TextView tvname = view.findViewById(R.id.tvname);
//        TextView tvndob = view.findViewById(R.id.tvdob);
        final TextView tvteam = view.findViewById(R.id.tvteam);
        final TextView tvposition = view.findViewById(R.id.tvposition);
        tvemail.setText("" + user.getEmail().toString());
        resendcode = view.findViewById(R.id.resendcode);
        verifymsg = view.findViewById(R.id.verifymsg);
        profileView = view.findViewById(R.id.profileView);
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileref = storageReference.child("users/" + mAuth.getCurrentUser().getUid() + "profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileView);
            }
        });

        profileimage = view.findViewById(R.id.profileimage);

        if (!user.isEmailVerified()) {
            verifymsg.setVisibility(View.VISIBLE);
            resendcode.setVisibility(View.VISIBLE);

            resendcode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(view.getContext(), "Verification Email has been sent", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("tag", "onFailure: Email not sent" + e.getMessage());
                        }
                    });

                }
            });
        }


        fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value1 = snapshot.child("etfname").getValue(String.class);
                String value = snapshot.child("etphno").getValue(String.class);
//                String value2 = snapshot.child("dob").getValue(String.class);
                String value3 = snapshot.child("etteam").getValue(String.class);
                String value4 = snapshot.child("etposition").getValue(String.class);

                tvname.setText(value1);
                tvnum.setText(value);
//                tvndob.setText(value2);
                tvteam.setText(value3);
                tvposition.setText(value4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent i = new Intent(view.getContext(), EditProfile.class);
                i.putExtra("fullName", tvname.getText().toString());
                i.putExtra("Email", tvemail.getText().toString());
                i.putExtra("PhoneNo", tvnum.getText().toString());
                i.putExtra("TeamName", tvteam.getText().toString());
                i.putExtra("Position", tvposition.getText().toString());

                startActivity(i);

//
            }
        });

    }


}
