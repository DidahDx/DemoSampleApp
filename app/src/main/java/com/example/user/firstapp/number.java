package com.example.user.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class number extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        ArrayList<String> numbers=new ArrayList<>();
        numbers.add("one");
        numbers.add("two");
        numbers.add("three");
        numbers.add("four");
        numbers.add("five");
        numbers.add("six");
        numbers.add("seven");
        numbers.add("eight");
        numbers.add("nine");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("ten");
        numbers.add("one");
        numbers.add("one");
        numbers.add("one");
        numbers.add("one");
        numbers.add("one");
        numbers.add("one");
        numbers.add("one");

        ArrayAdapter<String> itemsAdapter=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,numbers);
        ListView rootView =findViewById(R.id.rootView);

        rootView.setAdapter(itemsAdapter);
    }
}
