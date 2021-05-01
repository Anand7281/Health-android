package com.indiaforfitness.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CongratulationActivity extends AppCompatActivity {
    private Button addMoreCongrats,HomeCongrats;
    private TextView etReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        addMoreCongrats =findViewById(R.id.addMoreCongrats);
        HomeCongrats = findViewById(R.id.HomeCongrats);
        etReg = findViewById(R.id.reg_congrats);
        String str= getIntent().getStringExtra("regnumber");
        etReg.setText(str);

        addMoreCongrats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_itent= new Intent(CongratulationActivity.this, AddBeneficiary.class);
                main_itent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main_itent);
            }
        });

        HomeCongrats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_= new Intent(CongratulationActivity.this, MainActivity.class);
                main_.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main_);
            }
        });

    }
}