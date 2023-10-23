package com.example.travelexpensemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private DocumentReference reference = db.collection("Users").document();
    private CollectionReference collection = db.collection("Users");

    int person =0;
    private static final String TAG = "MainActivity";
    private static final String Name = "Name";
    private static final String UserName = "Username";
    private static final String Password = "Password";
    private static final String Type = "Type";

    EditText etName,etPassword;
    RadioGroup rg;
    int rbid;
    RadioButton rbAdmin, rbMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etCredentials);
        rg = findViewById(R.id.rgInput);
        rbAdmin = findViewById(R.id.rbAdminInput);
        rbMember = findViewById(R.id.rbMemberInput);
    }

    public void Login(View view) {
        Toast.makeText(this, "Enter User Name and password \n " +
                "If already have acccount", Toast.LENGTH_SHORT).show();
//        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                   User user = documentSnapshot.toObject(User.class);
//                   String username = user.getUsername();
//                   String password = user.getPassword();
//                    Toast.makeText(MainActivity.this, username+" "+password, Toast.LENGTH_SHORT).show();
//                }
////                    else Toast.makeText(MainActivity.this, "Not such user", Toast.LENGTH_SHORT).show();
//           // }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, e.toString());
//                    }
//                });

    }

    public void Create(View view) {
        Intent intent = new Intent(MainActivity.this,Create.class);
        startActivity(intent);
    }

    public void Submit(View view) {
        rbid = rg.getCheckedRadioButtonId();
        rbMember = findViewById(rbid);
        final String giventype = rbMember.getText().toString().trim();
        final String givenUsername = etName.getText().toString().trim();
        final String givenPassword = etPassword.getText().toString().trim();

        collection.whereEqualTo("username",givenUsername)
                .whereEqualTo("password",givenPassword)
                .whereEqualTo("type",giventype)
                .get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "cvcv", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                        {
                            User user = documentSnapshot.toObject(User.class);
                            user.setDocumentId(documentSnapshot.getId());

                            String username = user.getUsername();
                            String password = user.getPassword();
                            String type= user.getType();
                            person = 1;
                            Toast.makeText(MainActivity.this, "Hello sir your \n Uname "+username+"password"+password+"type"+type, Toast.LENGTH_SHORT).show();

                        }
                    }

                });
    }

    public void SPlash(View view) {
        startActivity(new Intent(MainActivity.this,SplashScreen.class));
    }
}