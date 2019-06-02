package com.example.user.firstapp;

public class Word {

    private String defaultLanguage,swahili;
    private int imageId=NO_IMAGE_PROVIDED;

    private int mAudioResource;

    private static final int NO_IMAGE_PROVIDED=-1;

    public Word(String def, String swa,int audioResource){
        defaultLanguage=def;
        swahili=swa;
        mAudioResource=audioResource;
    }

    public Word(String def, String swa, int imageId,int audioResource){
       defaultLanguage=def;
       swahili=swa;
       this.imageId = imageId;
       mAudioResource=audioResource;
   }


    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public String getSwahili() {
        return swahili;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean HasImage(){
        return imageId!=NO_IMAGE_PROVIDED;
    }

    public int getmAudioResource() {
        return mAudioResource;
    }
}
