package com.example.week3day2hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataEntry extends AppCompatActivity {
    EditText etName;
    EditText etMajor;
    EditText etMinor;
    EditText etExpectedGradYear;
    EditText etgpa;
    EditText etCompletedHrs;
    Intent sentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        bindViews();
        sentIntent = getIntent();
    }
     public void bindViews(){
        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etMinor = findViewById(R.id.etMinor);
        etExpectedGradYear = findViewById(R.id.etExpectedGradyr);
        etgpa = findViewById(R.id.etgpa);
        etCompletedHrs = findViewById(R.id.etcompletedHrs);
     }
     public Student generateStudentFromInput(){
        Student returnStudent =  new Student();

        returnStudent.setStudentName(etName.getText()!=null?etName.getText().toString():"");
        returnStudent.setStudentMajor(etMajor.getText()!=null?etMajor.getText().toString():"");
        returnStudent.setStudentMinor(etMinor.getText()!=null?etMinor.getText().toString():"");
        returnStudent.setStudentExpGradDate(etExpectedGradYear.getText()!=null?etExpectedGradYear.getText().toString():"");
        returnStudent.setStudentGPA(etgpa.getText()!=null?etgpa.getText().toString():"");
        returnStudent.setStudentCompletedHours(etCompletedHrs.getText()!=null?etCompletedHrs.getText().toString():"");
        return returnStudent;
    }

    public void onClick(View view) {
        Student studentResult = generateStudentFromInput();
        Bundle bundle = new Bundle();
        bundle.putParcelable("student",studentResult);
        sentIntent.putExtras(bundle);
        setResult(100,sentIntent);
        finish();
    }
}
