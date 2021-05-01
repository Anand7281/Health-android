package com.indiaforfitness.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class GeneralPublic extends AppCompatActivity {

    private EditText NameGeneralPublic,MobileNumberGeneralPublic,DobGeneralPublic,AadhaarNumberGeneralPublic,
            AddressGeneralPublic,AddressStateGeneralPublic,AddressDistrictGeneralPublic,AddressPinGeneralPublic;
    private TextView RegNumberGeneralPublic;
    private RadioGroup GenderGeneralPublic;
    String dob, Name, Mobile, Parent, Aadhar,Gender, Type,Address,State,Pin,District,School,Class,Section,serum, edta,role;
    private int age;

    String RegNumber = "JH11D",newr;
    Long temp_reg_last= Long.valueOf(0);

    private Button SubmitGeneralPublic;
    private DatabaseReference mRef;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_public);


        NameGeneralPublic =findViewById(R.id.NameGeneralPublic);
        MobileNumberGeneralPublic = findViewById(R.id.MobileGeneralPublic);
        DobGeneralPublic = findViewById(R.id.DobGeneralPublic);
        AadhaarNumberGeneralPublic = findViewById(R.id.AadhaarNumberGeneralPublic);
        AddressGeneralPublic = findViewById(R.id.AddressGeneralPublic);
        AddressStateGeneralPublic = findViewById(R.id.AddressStateGeneralPublic);
        AddressDistrictGeneralPublic = findViewById(R.id.AddressDistrictGeneralPublic);
        AddressPinGeneralPublic = findViewById(R.id.AddressPinGeneralPublic);
        SubmitGeneralPublic =findViewById(R.id.SubmitGeneralPublic);
        RegNumberGeneralPublic = findViewById(R.id.RegNumberGeneralPublic);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();



        GenderGeneralPublic = findViewById(R.id.GenderGeneralPublic);

        mRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference regRef= mRef.child("registration_number");

        role = getIntent().getExtras().getString("role");

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(role.equals("new")){
            regRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    temp_reg_last = snapshot.getValue(Long.class);
                    newr = RegNumber.substring(0,5) + temp_reg_last.toString();
                    RegNumberGeneralPublic.setText(newr);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        else  if(role.equals("edit")){
            String reg_temp = getIntent().getExtras().getString("reg");
            RegNumberGeneralPublic.setText(reg_temp);

            DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference().child("Users_Collection_Data").child(mCurrentUser.getUid()).child(reg_temp);

            mDbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    GeneralPublic_beneficiary beneficiary = snapshot.getValue(GeneralPublic_beneficiary.class);

                    NameGeneralPublic.setText(beneficiary.getName());
                    DobGeneralPublic.setText(beneficiary.getDob());
                    MobileNumberGeneralPublic.setText(beneficiary.getMobile());
                    AddressGeneralPublic.setText(beneficiary.getAddress());
                    AddressDistrictGeneralPublic.setText(beneficiary.getDistrict());
                    AddressStateGeneralPublic.setText(beneficiary.getState());
                    AddressPinGeneralPublic.setText(beneficiary.getPin());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        SubmitGeneralPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegNumber =RegNumberGeneralPublic.getText().toString();
                dob =DobGeneralPublic.getText().toString();
                String regextra= RegNumber;
                Name=NameGeneralPublic.getText().toString();
                Mobile =MobileNumberGeneralPublic.getText().toString();
                Aadhar =AadhaarNumberGeneralPublic.getText().toString();
                Address =AddressGeneralPublic.getText().toString();
                State =AddressStateGeneralPublic.getText().toString();
                District =AddressDistrictGeneralPublic.getText().toString();
                Pin =AddressPinGeneralPublic.getText().toString();

                if(dob.length()<8 || Name.isEmpty() || Mobile.length()<10 || Address.isEmpty() || State.isEmpty()
                        || District.isEmpty() || Pin.isEmpty() ||
                        GenderGeneralPublic.getCheckedRadioButtonId()== -1 ){
                    Toast.makeText(GeneralPublic.this,"Enter All The Required Detail",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    age = getageCalculated(dob);
                    if(GenderGeneralPublic.getCheckedRadioButtonId()== R.id.GenderMaleGeneralPublic){
                        Gender = "Male";
                    }else {
                        Gender = "Female";
                    }
                    GeneralPublic_beneficiary generalpublichelper= new GeneralPublic_beneficiary( RegNumber,age,Name,dob,Mobile,Aadhar,Gender,Address,State,Pin,District);

                    mRef.child("Users_Collection_Data").child(currentUser.getUid()).child(RegNumber).setValue(generalpublichelper);
                    mRef.child("All_Beneficiary").child(RegNumber).setValue(generalpublichelper);
                    if(role.equals("new")){
                        String temp = RegNumber.substring(5);
                        int temp_reg = Integer.parseInt(temp) +1;
                        regRef.setValue(temp_reg);
                    }


                    Intent itent= new Intent(GeneralPublic.this, CongratulationActivity.class);
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