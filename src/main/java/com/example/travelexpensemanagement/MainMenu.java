package com.example.travelexpensemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void CreateGroup(View view) {
        startActivity(new Intent(MainMenu.this,CreateGroup.class));
    }

    public void JoinGroup(View view) {
        startActivity(new Intent(MainMenu.this,JoinGroup.class));
    }
}