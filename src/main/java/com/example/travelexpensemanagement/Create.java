package com.example.travelexpensemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Create extends AppCompatActivity {
    EditText etName,etUserName,etPassword;
    int selectedid;
    RadioGroup rg;
    RadioButton rbMember, rbAdmin;
    private static final String TAG = "Create";
    private static final String Name = "Name";
    private static final String UserName = "Username";
    private static final String Password = "Password";
    private static final String Type = "Type";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etUserName = findViewById(R.id.etUsername);
        rg = findViewById(R.id.rg);
        rbAdmin =findViewById(R.id.rbAdmin);
        rbMember = findViewById(R.id.rbMember);

    }

    public void Submit(View view) {
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String username = etUserName.getText().toString().trim();
        selectedid = rg.getCheckedRadioButtonId();
        rbMember = findViewById(selectedid);
        String type = rbMember.getText().toString().trim();

        User submit = new User(name,username,password,type);

        db.collection("Users").document().set(submit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Create.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Create.this, "Not Saved Sorryyy ", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

    }

    public void Login(View view) {//bottom navigation login
        Intent intent = new Intent(Create.this,MainActivity.class);
        startActivity(intent);
    }

    public void Create(View view) {//bottomnavigation create
        Toast.makeText(this, "Login to account \n " +
                "If already have acccount", Toast.LENGTH_SHORT).show();
    }

    public void splash(View view) {
        startActivity(new Intent(Create.this,SplashScreen.class));
    }
}