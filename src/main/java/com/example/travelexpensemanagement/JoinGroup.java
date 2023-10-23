package com.example.travelexpensemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinGroup extends AppCompatActivity {
    EditText etJoinName,etJoinGroupID,etJoinAdmin;
    DatabaseReference reference;
    int selectedid;
    RadioGroup joinRG;
    RadioButton JoinrbMember, JoinrbAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        etJoinName  = findViewById(R.id.etJoinName);
        etJoinAdmin = findViewById(R.id.etJoinAdmin);
        etJoinGroupID = findViewById(R.id.etJoinGroupID);
    }

    public void Join(View view) {
        String name = etJoinName.getText().toString().trim();
        String GroupName = etJoinGroupID.getText().toString().trim();
                selectedid = joinRG.getCheckedRadioButtonId();
        String admin = etJoinAdmin.getText().toString().trim();
        String type = JoinrbMember.getText().toString().trim();
        reference = FirebaseDatabase.getInstance().getReference("Group");
        reference.child(GroupName).child(admin).child("Requests").setValue(name);
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Group").child(name);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren())
//                    Toast.makeText(JoinGroup.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
    }
}