package com.example.user.firstapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class number extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ) {
                //temporary loss audio focus
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0); //used to reset the play to start playing from the beginning

            }else if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
                //gained the audio focus thus can resume playing the media
                //used to resume playing the media
                mMediaPlayer.start();

            }else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){

                //when audio focus is permanetly lost
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

       final ArrayList<Word> numbers=new ArrayList<Word>();
        numbers.add(new Word("one","moja",R.mipmap.build_pc_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Two","mbili", R.mipmap.car_dg_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Three","Tatu",R.mipmap.build_pc_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Four","Nne",R.mipmap.car_dg_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Five","Tano",R.raw.pia));
        numbers.add(new Word("Six","sita", R.mipmap.car_dg_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Seven","saba",R.mipmap.build_pc_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Eight","nane",R.mipmap.car_dg_round ,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Nine","Tisa",R.mipmap.build_pc_round,R.raw.sean_paul_like_glue));
        numbers.add(new Word("Ten","Kumi",R.mipmap.car_dg_round,R.raw.sean_paul_like_glue));

        final WordAdapter itemsAdapter=new WordAdapter(this,numbers,R.color.category_numbers);
        ListView rootView =findViewById(R.id.rootView);

        rootView.setAdapter(itemsAdapter);

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=numbers.get(position);

                //release media resources before playing a sound
                releaseMediaPlayer();

                int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(number.this, word.getmAudioResource());
                    mMediaPlayer.start();

                    //release media resources after playing a sound
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    Toast.makeText(number.this, "List item clicked", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        SearchView search=findViewById(R.id.search);
//
//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                itemsAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }

    //used to set the Media Player
    private void releaseMediaPlayer(){
        if (mMediaPlayer!=null){

            mMediaPlayer.release();

            mMediaPlayer=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}