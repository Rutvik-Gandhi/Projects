package com.example.travelexpensemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateGroup extends AppCompatActivity {
    EditText etCreateName,etCreatePassword,etGroupName,etType;
    int id = 0;
    Group group;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        etCreateName = findViewById(R.id.etCreateName);
        etGroupName = findViewById(R.id.etCreateGroupName);

        reference = FirebaseDatabase.getInstance().getReference("Group");


    }

    public void Creating(View view) {
    group = new Group();
        String name = etCreateName.getText().toString().trim();
        String groupname = etGroupName.getText().toString().trim();
     group.setName(name);
        reference.child(groupname).child(name).setValue(group);
        startActivity(new Intent(CreateGroup.this,Admin.class));
    }


}