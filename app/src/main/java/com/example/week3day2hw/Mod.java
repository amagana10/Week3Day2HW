package com.example.week3day2hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Mod extends AppCompatActivity {
    TextView tvID;
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
        setContentView(R.layout.activity_mod);
        bindViews();
        sentIntent = getIntent();

        fillViews();
    }

    private void fillViews() {
        Bundle sentbundle = sentIntent.getExtras();
        if(sentbundle != null){
            Student student  = sentbundle.getParcelable("student_search");
            if(student != null) {
                tvID.setText("ID:"+student.getStudentID());
                etName.setText(student.getStudentName());
                etMajor.setText(student.getStudentMajor());
                etMinor.setText(student.getStudentMinor());
                etExpectedGradYear.setText(student.getStudentExpGradDate());
                etgpa.setText(student.getStudentGPA());
                etCompletedHrs.setText(student.getStudentCompletedHours());
            }

        }
    }

    public void bindViews(){
        tvID = findViewById(R.id.tvID);
        etName = findViewById(R.id.etModName);
        etMajor = findViewById(R.id.etModMajor);
        etMinor = findViewById(R.id.etModMinor);
        etExpectedGradYear = findViewById(R.id.etModExpectedGradyr);
        etgpa = findViewById(R.id.etModgpa);
        etCompletedHrs = findViewById(R.id.etModcompletedHrs);
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
        switch (view.getId()){
            case R.id.btnUpdate:
                Student studentResult = generateStudentFromInput();
                Bundle bundle = new Bundle();
                bundle.putParcelable("update",studentResult);
                sentIntent.putExtras(bundle);
                setResult(104,sentIntent);
                finish();
                break;
            case R.id.btnDelete:
                Student studentResult1 = sentIntent.getParcelableExtra("student_search");
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("delete",studentResult1);
                sentIntent.putExtras(bundle2);
                setResult(105,sentIntent);
                finish();
                break;
        }

    }
}
