package com.example.customlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.customlistapplication.models.Model;
import com.example.customlistapplication.models.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    int studentPosition;
    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Intent studentDetailsActivityIntent = getIntent();
        studentPosition = studentDetailsActivityIntent.getIntExtra("studentPosition", -1);
        name = findViewById(R.id.activity_student_details_name_output);
        id = findViewById(R.id.activity_student_details_id_output);
        phone = findViewById(R.id.activity_student_details_phone_output);
        address = findViewById(R.id.activity_student_details_address_output);
        checked = findViewById(R.id.activity_student_details_checked);
        fetchStudentDetails();
    }

    private void fetchStudentDetails() {
        Student student = Model.instance.getStudentList().get(studentPosition);
        name.setText(student.getName());
        id.setText(student.getId());
        phone.setText(student.getPhone());
        address.setText(student.getAddress());
        checked.setChecked(student.isChecked());
    }

    public void onEditButtonClick(View view) {
        Intent editStudentActivityIntent = new Intent(this, EditStudentActivity.class);
        editStudentActivityIntent.putExtra("studentPosition", studentPosition);
        startActivity(editStudentActivityIntent);
    }
}