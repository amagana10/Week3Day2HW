package com.example.week3day2hw;

import android.os.Parcel;
import android.os.Parcelable;

class Student implements Parcelable {
    private int studentID;
    private String studentName;
    private String studentMajor;
    private String studentMinor;
    private String studentExpGradDate;
    private String studentGPA;
    private String studentCompletedHours;

    public Student(int studentID, String studentName, String studentMajor, String studentMinor, String studentExpGradDate, String studentGPA, String studentCompletedHours) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentMinor = studentMinor;
        this.studentExpGradDate = studentExpGradDate;
        this.studentGPA = studentGPA;
        this.studentCompletedHours = studentCompletedHours;
    }

    protected Student(Parcel in) {
        studentID = in.readInt();
        studentName = in.readString();
        studentMajor = in.readString();
        studentMinor = in.readString();
        studentExpGradDate = in.readString();
        studentGPA = in.readString();
        studentCompletedHours = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Student() {

    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentMinor() {
        return studentMinor;
    }

    public void setStudentMinor(String studentMinor) {
        this.studentMinor = studentMinor;
    }

    public String getStudentExpGradDate() {
        return studentExpGradDate;
    }

    public void setStudentExpGradDate(String studentExpGradDate) {
        this.studentExpGradDate = studentExpGradDate;
    }

    public String getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(String studentGPA) {
        this.studentGPA = studentGPA;
    }

    public String getStudentCompletedHours() {
        return studentCompletedHours;
    }

    public void setStudentCompletedHours(String studentCompletedHours) {
        this.studentCompletedHours = studentCompletedHours;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentMinor='" + studentMinor + '\'' +
                ", studentExpGradDate='" + studentExpGradDate + '\'' +
                ", studentGPA='" + studentGPA + '\'' +
                ", studentCompletedHours='" + studentCompletedHours + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(studentID);
        dest.writeString(studentName);
        dest.writeString(studentMajor);
        dest.writeString(studentMinor);
        dest.writeString(studentExpGradDate);
        dest.writeString(studentGPA);
        dest.writeString(studentCompletedHours);
    }
}
