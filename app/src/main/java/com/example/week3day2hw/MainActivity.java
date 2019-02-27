package com.example.week3day2hw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    DataBaseStudentHelper db;
    EditText etId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseStudentHelper(this);

        sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);

        etId = findViewById(R.id.etIdEnter);

    }
    public void saveID(@NonNull Student student){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last_id",student.getStudentID());
        editor.commit();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEnterStudent:
                Intent explicitIntent = new Intent(this,DataEntry.class);
                startActivityForResult(explicitIntent,101);
                break;
            case R.id.btnSearch:
                if (etId.getText()!=null){
                    Student student = db.getStudentById(Integer.valueOf(etId.getText().toString()));
                    writeToFile(String.valueOf(student.getStudentID()));
                    Intent intent = new Intent(this,Mod.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("student_search",student);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,102);
                }

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101){
            if(data != null) {
                //Get bundle from Intent
                Bundle resultBundle = data.getExtras();
                if(resultBundle != null){
                    Student resultStudent = resultBundle.getParcelable("student");
                    if(resultStudent != null) {
                        saveID(resultStudent);
                        saveStudentToDB(resultStudent);
                    }

                }
            }
        }
        if (resultCode == 105){
            if(data != null) {
                //Get bundle from Intent
                Bundle resultBundle = data.getExtras();
                if(resultBundle != null){
                    Student resultStudent = resultBundle.getParcelable("delete");
                    if(resultStudent != null) {
                        String studentIDToDelete =String.valueOf( resultStudent.getStudentID());
                        String[] idtoDel = new String[]{studentIDToDelete};
                        db.deleteStudentFromDBbyId(idtoDel);
                    }

                }
            }
        }
        if (resultCode == 104){
            if(data != null) {
                //Get bundle from Intent
                Bundle resultBundle = data.getExtras();
                if(resultBundle != null){
                    Student resultStudent = resultBundle.getParcelable("update");
                    if(resultStudent != null) {
                        saveID(resultStudent);
                        db.updateStudentInfoInDB(resultStudent);
                    }

                }
            }
        }
    }

    private void saveStudentToDB(Student student) {
        db.insertStudentIntoDB(student);
    }

    //Write to INTERNAL storage
    public void writeToFile(String id) {
        try {

            //Open up file to edit
            FileOutputStream fileOutputStream= openFileOutput("idfile.txt", MODE_PRIVATE);
            //Add to the file the text we want to save
            fileOutputStream.write((id + " ").getBytes());
            //close the file
            Log.d("TAG", "writeToFile: " +fileOutputStream);
            fileOutputStream.close();
            Log.d("TAG", "writeToFile: TEXT WRITTEN TO FILE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
