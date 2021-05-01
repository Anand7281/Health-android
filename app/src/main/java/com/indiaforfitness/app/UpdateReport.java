package com.indiaforfitness.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateReport extends AppCompatActivity {

    private TextView RegNumberUpdateReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_report);
        String regNo= getIntent().getExtras().getString("RegNo");

        RegNumberUpdateReport = findViewById(R.id.RegNumberUpdateReport);

        RegNumberUpdateReport.setText(regNo);

    }
}