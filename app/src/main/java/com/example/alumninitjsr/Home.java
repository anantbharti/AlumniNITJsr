package com.example.alumninitjsr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alumninitjsr.fragments.AlumniProfile;

public class Home extends AppCompatActivity {

    Button myProBtn,alumniProBtn,logOutBtn;
//    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpViews();

        alumniProBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.add(R.id.home,new AlumniProfile());
                ft.commit();
            }
        });
        myProBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this,"Not ready!",Toast.LENGTH_SHORT).show();
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,MainActivity.class));
                finish();
            }
        });
    }

    private void setUpViews(){
        myProBtn=findViewById(R.id.open_my_profile);
        alumniProBtn=findViewById(R.id.open_alumni_profile);
        logOutBtn=findViewById(R.id.log_out);
    }
}