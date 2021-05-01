package com.indiaforfitness.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class SchoolStudents extends AppCompatActivity {
    private EditText NameSchoolStudent,ParentsNameSchoolStudent,MobileNumberSchoolStudent,DobSchoolStudent,AadhaarNumberSchoolStudent,
                     AddressSchoolStudent,AddressStateSchoolStudent,AddressDistrictSchoolStudent,AddressPinSchoolStudent,SchoolNameSchoolStudent,
                     ClassSchoolStudent,SectionSchoolStudent;
    private TextView RegNumberSchoolStudent;
    private RadioGroup GenderSchoolStudent;
     String dob, Name, Mobile, Parent, Aadhar,Gender, Type,Address,State,Pin,District,School,Class,Section;
    private int age;

    String RegNumber = "JH11A",newr;
    Long temp_reg_last= Long.valueOf(0);

    private Button SubmitSchoolStudent;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_students);

        NameSchoolStudent =findViewById(R.id.NameSchoolStudent);
        ParentsNameSchoolStudent =findViewById(R.id.ParentsNameSchoolStudent);
        MobileNumberSchoolStudent = findViewById(R.id.MobileNumberSchoolStudent);
        DobSchoolStudent = findViewById(R.id.DobSchoolStudent);
        AadhaarNumberSchoolStudent = findViewById(R.id.AadhaarNumberSchoolStudent);
        AddressSchoolStudent = findViewById(R.id.AddressSchoolStudent);
        AddressStateSchoolStudent = findViewById(R.id.AddressStateSchoolStudent);
        AddressDistrictSchoolStudent = findViewById(R.id.AddressDistrictSchoolStudent);
        AddressPinSchoolStudent = findViewById(R.id.AddressPinSchoolStudent);
        SchoolNameSchoolStudent = findViewById(R.id.SchoolNameSchoolStudent);
        ClassSchoolStudent = findViewById(R.id.ClassSchoolStudent);
        SectionSchoolStudent = findViewById(R.id.SectionSchoolStudent);
        SubmitSchoolStudent =findViewById(R.id.SubmitSchoolStudent);
        RegNumberSchoolStudent = findViewById(R.id.RegNumberSchoolStudent);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();



        GenderSchoolStudent = findViewById(R.id.GenderSchoolStudent);

        mRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference regRef= mRef.child("registration_number");

        regRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                temp_reg_last = snapshot.getValue(Long.class);
                newr = RegNumber.substring(0,5) + temp_reg_last.toString();
                RegNumberSchoolStudent.setText(newr);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        SubmitSchoolStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegNumber =RegNumberSchoolStudent.getText().toString();
                dob =DobSchoolStudent.getText().toString();
                String regextra= RegNumber;
                Name=NameSchoolStudent.getText().toString();
                Parent =ParentsNameSchoolStudent.getText().toString();
                Mobile =MobileNumberSchoolStudent.getText().toString();
                Aadhar =AadhaarNumberSchoolStudent.getText().toString();
                Address =AddressSchoolStudent.getText().toString();
                State =AddressStateSchoolStudent.getText().toString();
                District =AddressDistrictSchoolStudent.getText().toString();
                Pin =AddressPinSchoolStudent.getText().toString();
                School =SchoolNameSchoolStudent.getText().toString();
                Class =ClassSchoolStudent.getText().toString();
                Section =SectionSchoolStudent.getText().toString();

                if(dob.length()<8 || Name.isEmpty() || Parent.isEmpty() || Mobile.isEmpty() ||
                        Aadhar.isEmpty() || Address.isEmpty() || State.isEmpty() || District.isEmpty() ||
                        Pin.isEmpty() || School.isEmpty() || Class.isEmpty() || Section.isEmpty() ||
                        GenderSchoolStudent.getCheckedRadioButtonId()== -1 ){
                    Toast.makeText(SchoolStudents.this,"Enter All The Required Detail",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    age = getageCalculated(dob);
                    if(GenderSchoolStudent.getCheckedRadioButtonId()== R.id.GenderMaleSchoolStudent){
                        Gender = "Male";
                    }else {
                        Gender = "Female";
                    }
                    Student_beneficery studenthelper= new Student_beneficery(RegNumber,age,Name,dob,Mobile,Parent,Aadhar,Gender,Address,State,Pin,District,School, Class, Section);

                    mRef.child("Users_Collection_Data").child(currentUser.getUid()).child(RegNumber).setValue(studenthelper);
                    mRef.child("All_Beneficiary").child("School Student").child(RegNumber).setValue(studenthelper);
                    String temp = RegNumber.substring(5);
                    int temp_reg = Integer.parseInt(temp) +1;
                    regRef.setValue(temp_reg);

                    Intent itent= new Intent(SchoolStudents.this, CongratulationActivity.class);
                    itent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    itent.putExtra("regnumber",regextra);
                    startActivity(itent);
                }

            }
        });








    }

    public  int getageCalculated(@NotNull String d){
        int year=Integer.parseInt( d.substring(4));
        return (2021-year);
    }


}