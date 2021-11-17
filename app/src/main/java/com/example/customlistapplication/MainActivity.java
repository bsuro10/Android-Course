package com.example.customlistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.customlistapplication.models.Model;
import com.example.customlistapplication.models.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> studentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = Model.instance.getStudentList();

        ListView list = findViewById(R.id.activity_main_listview);
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("Debug", "Clicked Row: " + position);
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_item, null);
                convertView.findViewById(R.id.list_item_checkbox);
                CheckBox checkBox = convertView.findViewById(R.id.list_item_checkbox);
                checkBox.setOnClickListener(view -> {
                    int pos = Integer.parseInt(checkBox.getTag().toString());
                    Student student = studentList.get(pos);
                    student.setFlag(checkBox.isChecked());
                });
            }

            CheckBox checkBox = convertView.findViewById(R.id.list_item_checkbox);
            TextView name = convertView.findViewById(R.id.list_item_textview_name);
            TextView id = convertView.findViewById(R.id.list_item_textview_id);
            Student student = studentList.get(position);
            name.setText(student.getName());
            id.setText(student.getId());
            checkBox.setTag(position);
            checkBox.setChecked(student.isFlag());

            return convertView;
        }
    }
}