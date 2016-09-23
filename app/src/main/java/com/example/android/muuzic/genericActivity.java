package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class genericActivity extends AppCompatActivity {

    ImageView i;     //to store song photo at the top of 2nd activity
    TextView txt;    // song name in song list in 2nd activity

    ImageView smallicon ;   // these 3 are to deal with current song being played/paused
    TextView  runningsong ;
    ImageView playpause ;
    Intent manIntent;

    String nowPlaying;

    @Override
    public void onBackPressed() {

           manIntent = new Intent(genericActivity.this, MainActivity.class);
           //manIntent.putExtra("CURRENT_SONG", "man");
            manIntent.putExtra("NOW_PLAYING", nowPlaying);
            setResult(RESULT_OK, manIntent);

            super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        //create intent object which will be returned when back button is pressed
  //      manIntent = new Intent(genericActivity.this, MainActivity.class);

        //catch the intent from 1st activity
        Intent extras = getIntent();
        String viewIdentifier = extras.getStringExtra("VIEW_NAME");
        nowPlaying = extras.getStringExtra("NOW_PLAYING");

        ImageView i = (ImageView) findViewById(R.id.clickedImage);
        TextView  txt = (TextView) findViewById(R.id.songname);


        smallicon = (ImageView)findViewById(R.id.song_smallicon);
        runningsong = (TextView)findViewById(R.id.runningsong);
        playpause = (ImageView)findViewById(R.id.playpause);


        //find out which of the 8 images was clicked in 1st activity
        if (viewIdentifier.equals("man")){

            //1. set title bar, photo, name of the song (layout of 2nd activity)
            setTitle("ALBUMS/MAN");
            i.setImageResource(R.drawable.man);
            txt.setText("Man on the Rocks - Nuclear");

            //TMRW -- here i have to find out which song is getting played in 1st activity. 1st -> 2nd ke intent ka data check karke

            //2. set the song CardView at the bottom of 2nd Activity (depending on what song was being played in 1st activity)
            if(nowPlaying != null && nowPlaying.equals("mannuclear")){
                smallicon.setImageResource(R.drawable.man);
                runningsong.setText("Man on the Rocks - Nuclear");
                playpause.setImageResource(R.drawable.pause);
            }

            //3. did user tap on the song name in the 2nd activity
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smallicon.setImageResource(R.drawable.man);
                    runningsong.setText("Man on the Rocks - Nuclear");
                    playpause.setImageResource(R.drawable.pause);

                    nowPlaying = "mannuclear";
                }
            });

        } else if (viewIdentifier.equals("meteora")) {
            setTitle("ALBUMS/METEORA");
            i.setImageResource(R.drawable.meteora);
            txt.setText("Meteora - Numb");

        } else if (viewIdentifier.equals("taylorswift")) {
            setTitle("ARTISTS/TAYLOR SWIFT");
            i.setImageResource(R.drawable.taylorswift);
            txt.setText("Taylor Swift - Blank Space");

        } else if (viewIdentifier.equals("katyperry")) {
            setTitle("ARTISTS/KATY PERRY");
            i.setImageResource(R.drawable.katyperry);
            txt.setText("Katy Perry - Dark Horse");

        } else if (viewIdentifier.equals("gym")) {
            setTitle("PLAYLIST/GYM");
            i.setImageResource(R.drawable.gym);
            txt.setText("Gym - Bring Em Out");

        } else if (viewIdentifier.equals("party")) {
            setTitle("PLAYLIST/PARTY");
            i.setImageResource(R.drawable.party);
            txt.setText("Party - LMFAO(Party Rock)");

        } else if (viewIdentifier.equals("rock")) {
            setTitle("GENRE/ROCK");
            i.setImageResource(R.drawable.rock);
            txt.setText("Rock - Seven Nation Army");

        } else {
            setTitle("GENRE/POP");
            i.setImageResource(R.drawable.pop);
            txt.setText("Pop - Lean On");

        }

    }
}