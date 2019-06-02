package com.example.user.firstapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {
private int mColorResourceId;

    public WordAdapter(@NonNull Activity context, @NonNull ArrayList<Word> objects,int colorResource) {
        super(context,0, objects);
        mColorResourceId=colorResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView=convertView;
        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord=getItem(position);

        TextView defaulty=listView.findViewById(R.id.swahili_text_view);
        defaulty.setText(currentWord.getSwahili());

        TextView defaulty1=listView.findViewById(R.id.default_text_view);
        defaulty1.setText(currentWord.getDefaultLanguage());

        ImageView img34=listView.findViewById(R.id.image_1);

        if (currentWord.HasImage()) {
            img34.setVisibility(View.VISIBLE);
            img34.setImageResource(currentWord.getImageId());
        }else{

            img34.setVisibility(View.GONE);
        }

        View textContainer=listView.findViewById(R.id.text_container);

        //find the color that the resource id maps to
        int color= ContextCompat.getColor(getContext(),mColorResourceId);

        textContainer.setBackgroundColor(color);

        return listView;
    }
}
