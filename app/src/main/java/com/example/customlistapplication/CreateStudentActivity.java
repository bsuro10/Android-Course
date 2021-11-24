package com.example.customlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.customlistapplication.models.Model;
import com.example.customlistapplication.models.Student;

public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
    }

    public void onSaveStudentClick(View view) {
        EditText studentNameInput = findViewById(R.id.activity_create_student_nameInput);
        EditText studentIdInput = findViewById(R.id.activity_create_student_idInput);
        EditText studentPhoneInput = findViewById(R.id.activity_create_student_phoneInput);
        EditText studentAddressInput = findViewById(R.id.activity_create_student_addressInput);
        CheckBox studentCheckBoxInput = findViewById(R.id.activity_create_student_checked);

        Model.instance.addStudent(new Student(
                studentNameInput.getText().toString(),
                studentIdInput.getText().toString(),
                studentPhoneInput.getText().toString(),
                studentAddressInput.getText().toString(),
                studentCheckBoxInput.isChecked()));
        returnToStudentListActivity();
    }

    public void onCancelClick(View view) {
        returnToStudentListActivity();
    }

    public void returnToStudentListActivity() {
        Intent studentListActivityIntent = new Intent(this, StudentListActivity.class);
        startActivity(studentListActivityIntent);
    }
}