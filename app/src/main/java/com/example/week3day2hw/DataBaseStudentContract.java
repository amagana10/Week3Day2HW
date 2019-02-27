package com.example.week3day2hw;

import java.util.Locale;

public class DataBaseStudentContract {
    public static final String DATA_BASE_NAME = "student_db";
    public static final int DATA_BASE_VERSION =1;
    public static final String TABLE_NAME ="Students";
    public static final String COLUNM_ID = "id";
    public static final String COLUNM_NAME = "name";
    public static final String COLUNM_MAJOR = "major";
    public static final String COLUNM_MINOR = "minor";
    public static final String COLUNM_EXPECTED_GRAD_YEAR = "expected_grad_year";
    public static final String COLUNM_GPA = "gpa";
    public static final String COLUNM_COMPLETED_HRS = "completed_hours";

    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique Primary Key
        queryBuilder.append(COLUNM_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        //Add rest of the columns
        queryBuilder.append(COLUNM_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUNM_MAJOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUNM_MINOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUNM_EXPECTED_GRAD_YEAR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUNM_GPA);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUNM_COMPLETED_HRS);
        queryBuilder.append(" TEXT )");
        return queryBuilder.toString();

    }
    public static String getAllStudentsQuery(){
        return  "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneStudentByID(int id){
        return  String.format("SELECT * FROM %s WHERE %s = \"%d\"",TABLE_NAME,COLUNM_ID,id);
        //return "SELECT * FROM " + TABLE_NAME + " WHERE"
    }

    public static String getWhereClauseById(){
        return  String.format(Locale.US,
                "%s = " , COLUNM_ID);
    }

}
