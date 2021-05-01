package com.indiaforfitness.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class AddBeneficiary extends AppCompatActivity {


    private LinearLayout SchoolStudent,ArmyAspirant,AcademyAspirant,GeneralPublic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beneficiery);

        SchoolStudent = findViewById(R.id.SchoolStudent);
        ArmyAspirant = findViewById(R.id.ArmyAspirant);
        AcademyAspirant  = findViewById(R.id.AcademyAspirant);
        GeneralPublic = findViewById(R.id.GeneralPublic);

        SchoolStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBeneficiary.this,SchoolStudents.class));
            }
        });
        ArmyAspirant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddBeneficiary.this,"We will be open soon for Army Aspirants too",Toast.LENGTH_SHORT).show();
            }
        });
        AcademyAspirant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddBeneficiary.this,"We will be open soon for Academy Aspirants too",Toast.LENGTH_SHORT).show();
            }
        });
        GeneralPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddBeneficiary.this,GeneralPublic.class);
                i.putExtra("role", "new");
                startActivity(i);
            }
        });





    }



}