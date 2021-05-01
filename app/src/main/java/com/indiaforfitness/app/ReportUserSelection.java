package com.indiaforfitness.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportUserSelection extends AppCompatActivity {

    private EditText inputSearchUserReport;
    private DatabaseReference mDbRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_user_selection);
        ListView mList = findViewById(R.id.listUserSelection);
        inputSearchUserReport = findViewById(R.id.inputSearchUserSelection);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDbRef = FirebaseDatabase.getInstance().getReference().child("All_Beneficiary");
        ArrayList<String> Users_collection = new ArrayList<String>();
        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dbsnapsot : snapshot.getChildren()){
                    Users_collection.add(dbsnapsot.child("regNo").getValue(String.class));

                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Users_collection);
                    mList.setAdapter(adapter);

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent detailedView= new Intent(ReportUserSelection.this,UpdateReport.class);
                            String RegNo = Users_collection.get(position);
                            detailedView.putExtra("RegNo",RegNo);
                            startActivity(detailedView);
                        }
                    });

                    inputSearchUserReport.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                            // When user changed the Text
                            ReportUserSelection.this.adapter.getFilter().filter(cs);
                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                      int arg3) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void afterTextChanged(Editable arg0) {
                            // TODO Auto-generated method stub
                        }
                    });


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}