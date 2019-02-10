package com.example.user.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int number=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void help(View view) {
            number++;
            setQuantity(number);
            price(number);

    }

    public void setQuantity(int number){
        TextView num=  findViewById(R.id.dx);
        num.setText(number+" ");
    }

    public void price(int number){
        TextView price=findViewById(R.id.lef);
        price.setText("$"+Integer.toString(number*6));
    }

    public void decrement(View view) {
        number--;
        TextView num=  findViewById(R.id.dx);
        if(number>=0){
        num.setText(number+" ");
        price(number);
        }else{
            number=0;
        }
    }
}