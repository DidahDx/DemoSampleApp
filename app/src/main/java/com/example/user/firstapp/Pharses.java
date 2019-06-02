package com.example.user.firstapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pharses extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

   private AudioManager mAudioManager;

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

        final ArrayList<Word> pharses=new ArrayList<Word>();
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));
        pharses.add(new Word("Five","Tano",R.raw.sean_paul_like_glue));


        final WordAdapter itemsAdapter=new WordAdapter(this,pharses,R.color.category_pharses);

        ListView rootView =findViewById(R.id.rootView);

        rootView.setAdapter(itemsAdapter);
        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=pharses.get(position);

                //release media resources before playing a sound
                releaseMediaPlayer();


                int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(Pharses.this, word.getmAudioResource());
                    mMediaPlayer.start();

                    //release media resources after playing a sound
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });
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
