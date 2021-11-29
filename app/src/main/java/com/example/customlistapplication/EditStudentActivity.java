package com.example.customlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.customlistapplication.models.Model;
import com.example.customlistapplication.models.Student;

public class EditStudentActivity extends AppCompatActivity {

    int studentPosition;
    EditText studentNameInput;
    EditText studentIdInput;
    EditText studentPhoneInput;
    EditText studentAddressInput;
    CheckBox studentCheckBoxInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Intent editStudentActivityIntent = getIntent();
        studentPosition = editStudentActivityIntent.getIntExtra("studentPosition", -1);
        studentNameInput = findViewById(R.id.activity_edit_student_nameInput);
        studentIdInput = findViewById(R.id.activity_edit_student_idInput);
        studentPhoneInput = findViewById(R.id.activity_edit_student_phoneInput);
        studentAddressInput = findViewById(R.id.activity_edit_student_addressInput);
        studentCheckBoxInput = findViewById(R.id.activity_edit_student_checked);
        fetchStudentDetails();
    }

    private void fetchStudentDetails() {
        Student student = Model.instance.getStudentList().get(studentPosition);
        studentNameInput.setText(student.getName());
        studentIdInput.setText(student.getId());
        studentPhoneInput.setText(student.getPhone());
        studentAddressInput.setText(student.getAddress());
        studentCheckBoxInput.setChecked(student.isChecked());
    }

    public void onDeleteButtonClick(View view) {
        Model.instance.getStudentList().remove(studentPosition);
        Intent studentListActivityIntent = new Intent(this, StudentListActivity.class);
        studentListActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(studentListActivityIntent);
    }

    public void onSaveButtonClick(View view) {
        Student student = Model.instance.getStudentList().get(studentPosition);
        student.setName(studentNameInput.getText().toString());
        student.setId(studentIdInput.getText().toString());
        student.setPhone(studentPhoneInput.getText().toString());
        student.setAddress(studentAddressInput.getText().toString());
        student.setChecked(studentCheckBoxInput.isChecked());
        returnToStudentDetails();
    }

    public void onCancelButtonClick(View view) {
        returnToStudentDetails();
    }

    private void returnToStudentDetails() {
        Intent studentDetailsActivityIntent = new Intent(this, StudentDetailsActivity.class);
        studentDetailsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        studentDetailsActivityIntent.putExtra("studentPosition", studentPosition);
        startActivity(studentDetailsActivityIntent);
    }
}