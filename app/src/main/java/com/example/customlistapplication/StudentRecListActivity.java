package com.example.customlistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.customlistapplication.models.Model;
import com.example.customlistapplication.models.Student;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentRecListActivity extends AppCompatActivity {

    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_rec_list);

        RecyclerView list = findViewById(R.id.student_rec_list_activity_reclist);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));

        studentList = Model.instance.getStudentList();

        MyAdapter myAdapter = new MyAdapter();
        list.setAdapter(myAdapter);

        myAdapter.setListener(position -> {
            Log.d("Debug", "Clicked Row: " + position);
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView id;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.list_item_textview_name);
            id = itemView.findViewById(R.id.list_item_textview_id);
            checkBox = itemView.findViewById(R.id.list_item_checkbox);

            itemView.setOnClickListener(view -> {
                listener.onItemClick(getAdapterPosition());
            });

            checkBox.setOnClickListener(view -> {
                Student student = studentList.get(getAdapterPosition());
                student.setFlag(checkBox.isChecked());
            });
        }

        public void bind(Student student) {
            this.name.setText(student.getName());
            this.id.setText(student.getId());
            this.checkBox.setChecked(student.isFlag());
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        OnItemClickListener listener;

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view, listener);
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
}