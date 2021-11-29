package com.example.customlistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.customlistapplication.models.Model;
import com.example.customlistapplication.models.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    List<Student> studentList;
    RecyclerView list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_rec_list);

        list = findViewById(R.id.student_rec_list_activity_reclist);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));

        studentList = Model.instance.getStudentList();

        myAdapter = new MyAdapter();
        list.setAdapter(myAdapter);

        myAdapter.setItemClickListener(position -> {
            Intent studentDetailsActivityIntent = new Intent(this, StudentDetailsActivity.class);
            studentDetailsActivityIntent.putExtra("studentPosition", position);
            startActivity(studentDetailsActivityIntent);
        });

        myAdapter.setCheckboxClickListener((position, isChecked) -> {
            Student student = studentList.get(position);
            student.setChecked(isChecked);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentList = Model.instance.getStudentList();
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList() {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView id;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener itemClickListener, OnCheckboxClickListener checkboxClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.list_item_textview_name);
            id = itemView.findViewById(R.id.list_item_textview_id);
            checkBox = itemView.findViewById(R.id.list_item_checkbox);

            itemView.setOnClickListener(view -> {
                itemClickListener.onItemClick(getAdapterPosition());
            });

            checkBox.setOnClickListener(view -> {
                checkboxClickListener.onCheckboxClick(getAdapterPosition(), checkBox.isChecked());
            });
        }

        public void bind(Student student) {
            this.name.setText(student.getName());
            this.id.setText(student.getId());
            this.checkBox.setChecked(student.isChecked());
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    interface OnCheckboxClickListener {
        void onCheckboxClick(int position, boolean isChecked);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        OnCheckboxClickListener checkboxClickListener;
        OnItemClickListener itemClickListener;


        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void setCheckboxClickListener(OnCheckboxClickListener checkboxClickListener) {
            this.checkboxClickListener = checkboxClickListener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view, itemClickListener, checkboxClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = studentList.get(position);
            holder.bind(student);
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }
    }

    public void onCreateStudentClick(View view) {
        Intent createStudentActivityIntent = new Intent(this, CreateStudentActivity.class);
        startActivity(createStudentActivityIntent);
    }
}