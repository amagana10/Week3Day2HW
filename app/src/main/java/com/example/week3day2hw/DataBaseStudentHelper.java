package com.example.week3day2hw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week3day2hw.DataBaseStudentContract.*;


public class DataBaseStudentHelper extends SQLiteOpenHelper {
    public DataBaseStudentHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public long insertStudentIntoDB(@NonNull Student student){
        SQLiteDatabase writeableDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNM_NAME,student.getStudentName());
        contentValues.put(COLUNM_MAJOR,student.getStudentMajor());
        contentValues.put(COLUNM_MINOR,student.getStudentMinor());
        contentValues.put(COLUNM_EXPECTED_GRAD_YEAR,student.getStudentExpGradDate());
        contentValues.put(COLUNM_GPA,student.getStudentGPA());
        contentValues.put(COLUNM_COMPLETED_HRS,student.getStudentName());

        return writeableDB.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<Student> getAllStudentsFromDB(){
        ArrayList<Student> returnStudentList = new ArrayList<>();
        SQLiteDatabase readableDB = this.getReadableDatabase();

        Cursor cursor = readableDB.rawQuery(getAllStudentsQuery(),null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUNM_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUNM_NAME));
                String major = cursor.getString(cursor.getColumnIndex(COLUNM_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(COLUNM_MINOR));
                String expGradDate = cursor.getString(cursor.getColumnIndex(COLUNM_EXPECTED_GRAD_YEAR));
                String gpa = cursor.getString(cursor.getColumnIndex(COLUNM_GPA));
                String completedHrs = cursor.getString(cursor.getColumnIndex(COLUNM_COMPLETED_HRS));

                returnStudentList.add(new Student(id,name,major,minor,expGradDate,gpa,completedHrs));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return returnStudentList;

    }

    public Student getStudentById(int id){
        SQLiteDatabase readableDB =this.getReadableDatabase();

        Student returnStudent = new Student();

        Cursor cursor = readableDB.rawQuery(getOneStudentByID(id),null);
        if (cursor.moveToFirst()){

                id = cursor.getInt(cursor.getColumnIndex(COLUNM_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUNM_NAME));
                String major = cursor.getString(cursor.getColumnIndex(COLUNM_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(COLUNM_MINOR));
                String expGradDate = cursor.getString(cursor.getColumnIndex(COLUNM_EXPECTED_GRAD_YEAR));
                String gpa = cursor.getString(cursor.getColumnIndex(COLUNM_GPA));
                String completedHrs = cursor.getString(cursor.getColumnIndex(COLUNM_COMPLETED_HRS));

                returnStudent = new Student(id,name,major,minor,expGradDate,gpa,completedHrs);

        }
        cursor.close();
        return returnStudent;
    }

    public long updateStudentInfoInDB(@NonNull Student student){
        SQLiteDatabase writeableDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUNM_NAME,student.getStudentName());
        contentValues.put(COLUNM_MAJOR,student.getStudentMajor());
        contentValues.put(COLUNM_MINOR,student.getStudentMinor());
        contentValues.put(COLUNM_EXPECTED_GRAD_YEAR,student.getStudentExpGradDate());
        contentValues.put(COLUNM_GPA,student.getStudentGPA());
        contentValues.put(COLUNM_COMPLETED_HRS,student.getStudentName());

        return writeableDB.update(TABLE_NAME,
                contentValues,
                getWhereClauseById() + (new String[]{String.valueOf(student.getStudentID())})[0]
                ,null);

    }

    public long deleteStudentFromDBbyId(String[] id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,getWhereClauseById() + id[0],null);
    }

}
