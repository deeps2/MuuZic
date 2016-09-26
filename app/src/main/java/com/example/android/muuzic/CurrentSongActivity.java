package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
// @@@@@@@@@@change in whole code in according to 2nd activity also
public class CurrentSongActivity extends AppCompatActivity {

    int nowPlaying;
    String songName;
    boolean playOrPause= false;

    ImageView songPhoto;
    ImageView playPause;

    String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_song);

        songPhoto = (ImageView)findViewById(R.id.songImage);
        playPause = (ImageView)findViewById(R.id.button);

        Intent extras = getIntent();

        activityName = extras.getStringExtra("ACTIVITY");
        nowPlaying = extras.getIntExtra("NOW_PLAYING", -1);
        songName = extras.getStringExtra("SONG_NAME");
        playOrPause = extras.getBooleanExtra("PLAY", false);

        songPhoto.setImageResource(nowPlaying);
        setTitle(songName);
        if (playOrPause)
            playPause.setImageResource(R.drawable.play);
        else
            playPause.setImageResource(R.drawable.pause);


        // user tap on play or pause button
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playOrPause) {
                    playOrPause = false;
                    playPause.setImageResource(R.drawable.pause);
                } else {
                    playOrPause = true;
                    playPause.setImageResource(R.drawable.play);
                }
            }
        });
    }

    @Override
   public void onBackPressed(){

        Intent intent;

        if(activityName.equals("first_activity"))
            intent = new Intent(CurrentSongActivity.this, MainActivity.class);
        else
            intent = new Intent(CurrentSongActivity.this, genericActivity.class);

        intent.putExtra("PLAY",playOrPause);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }

}
