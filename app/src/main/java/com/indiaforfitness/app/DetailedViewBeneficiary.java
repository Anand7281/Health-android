package com.indiaforfitness.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailedViewBeneficiary extends AppCompatActivity {

    private TextView Reg_NumberDetailedView,NameDetailedView,GenderDetailedView,AgeDetailedView,MobileNumberDetailedView,
            AddressDetailedView,AddressDistrictDetailedView,AddressStateDetailedView,AddressPinDetailedView;
    private Button editDetailedView,HomeDetailedView;
    private DatabaseReference mDbRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    String mRegNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view_beneficiary);

        NameDetailedView = findViewById(R.id.NameDetailedView);
        Reg_NumberDetailedView = findViewById(R.id.Reg_NumberDetailedView);
        GenderDetailedView = findViewById(R.id.GenderDetailedView);
        AgeDetailedView = findViewById(R.id.AgeDetailedView);
        MobileNumberDetailedView = findViewById(R.id.MobileNumberDetailedView);
        AddressDetailedView = findViewById(R.id.AddressDetailedView);
        AddressDistrictDetailedView = findViewById(R.id.AddressDistrictDetailedView);
        AddressStateDetailedView = findViewById(R.id.AddressStateDetailedView);
        AddressPinDetailedView = findViewById(R.id.AddressPinDetailedView);

        editDetailedView = findViewById(R.id.addMoreDetailedView);
        HomeDetailedView = findViewById(R.id.HomeDetailedView);


        mRegNo = getIntent().getExtras().getString("RegNo");


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDbRef = FirebaseDatabase.getInstance().getReference().child("Users_Collection_Data").child(mCurrentUser.getUid()).child(mRegNo);

        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GeneralPublic_beneficiary beneficiary = snapshot.getValue(GeneralPublic_beneficiary.class);

                Reg_NumberDetailedView.setText(beneficiary.getRegNo());
                NameDetailedView.setText(beneficiary.getName());
                GenderDetailedView.setText("Gender: " + beneficiary.getGender());
                AgeDetailedView.setText("Age: " + beneficiary.getAge());
                MobileNumberDetailedView.setText("Mobile: " + beneficiary.getMobile());
                AddressDetailedView.setText("Address: " + beneficiary.getAddress());
                AddressDistrictDetailedView.setText("District: " + beneficiary.getDistrict());
                AddressStateDetailedView.setText("State: " + beneficiary.getState());
                AddressPinDetailedView.setText("Pin: " + beneficiary.getPin());

                editDetailedView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(beneficiary.getType().toString().equals("General Public")){
                            Intent i= new Intent(DetailedViewBeneficiary.this,GeneralPublic.class);
                            i.putExtra("role", "edit");
                            i.putExtra("reg", beneficiary.getRegNo());
                            startActivity(i);
                        }
                        else if(beneficiary.getType().toString().equals("School Student")){
                            Intent i= new Intent(DetailedViewBeneficiary.this,GeneralPublic.class);
                            i.putExtra("role", "edit");
                            i.putExtra("reg", beneficiary.getRegNo());
                            startActivity(i);
                        }

                    }
                });





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}