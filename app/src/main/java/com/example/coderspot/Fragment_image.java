package com.example.coderspot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class Fragment_image extends  Fragment{
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    Button btnload;
    private static final int PICK_IMAGE = 100;
    ImageView imgView;
    Uri imageUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.images_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button btnload = view.findViewById(R.id.btnload);
        final ImageView imgView = view.findViewById(R.id.imgView);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
                imgView.setVisibility(View.VISIBLE);
                btnload.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ImageView imgView = getView().findViewById(R.id.imgView);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
             imageUri = data.getData();
            imgView.setImageURI(imageUri);
        }
    }
}
