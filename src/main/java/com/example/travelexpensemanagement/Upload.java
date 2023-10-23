package com.example.travelexpensemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.UpdateLayout;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import android.widget.TextView;
import android.widget.Toast;

public class Upload extends AppCompatActivity {
private static int PICK_IMAGE_REQEST = 1;
private Button btnChooseFile;
private Button btnUpload;
private TextView txtShowUploads;
private EditText etFileName;
private ImageView imageView;
private ProgressBar progressBar;
private Uri uri;

private StorageReference mStorageRef;
private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        btnChooseFile = findViewById(R.id.btnChooseFile);
        btnUpload= findViewById(R.id.btnUpload);
        etFileName = findViewById(R.id.etFileName);
        imageView = findViewById(R.id.imageview);
        progressBar = findViewById(R.id.progressBar);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChoose();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadfile();
            }
        });
//        txtShowUploads.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
private String FileExtenstion(Uri uri)
{
    ContentResolver cr = getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cr.getType(uri));
}
    private void uploadfile() {
        if (uri != null) {
            StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()
                    +"."+FileExtenstion(uri));
            fileRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            },5000);
                            Toast.makeText(Upload.this, "Upload Successfull", Toast.LENGTH_SHORT).show();
//                            Uploading uploading = new Uploading(etFileName.getText().toString().trim(),
//                                    taskSnapshot.getDown)
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0/snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressBar.setProgress((int)progress);
                        }
                    });

        }
        else Toast.makeText(this, "No file Selected", Toast.LENGTH_SHORT).show();
    }


    private void openFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==PICK_IMAGE_REQEST&&resultCode==RESULT_OK&&data!=null)
        {
            uri = data.getData();
//            Picasso.with(this).load(uri).into(imageView);
            imageView.setImageURI(uri);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}