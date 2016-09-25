package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class genericActivity extends AppCompatActivity {

    ImageView i;     //to display song photo at the top of 2nd activity
    TextView txt;    // song name in the song list

    ImageView smallicon;   // these 3 are to deal with current song being played/paused. The CardView associated with this is located at the bottom of 2nd Activity
    TextView runningsong;
    ImageView playpause;

    Intent manIntent;
    int nowPlaying = -1;
    String songName = null;
    boolean play = true;

    String songInTheList;

    @Override
    public void onBackPressed() {

        manIntent = new Intent(genericActivity.this, MainActivity.class);
        manIntent.putExtra("NOW_PLAYING", nowPlaying);
        manIntent.putExtra("SONG_NAME", songName);
        manIntent.putExtra("PLAY", play);
        setResult(RESULT_OK, manIntent);

        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@ write comments aise jo spaces de rakhe hai in both java files
        i = (ImageView) findViewById(R.id.clickedImage);
        txt = (TextView) findViewById(R.id.songname);

        smallicon = (ImageView) findViewById(R.id.song_smallicon);
        runningsong = (TextView) findViewById(R.id.runningsong);
        playpause = (ImageView) findViewById(R.id.playpause);


        //catch the intent from 1st activity
        Intent extras = getIntent();

        final int viewImage = extras.getIntExtra("VIEW_IMAGE", -1);
        setTitleBar(viewImage, txt);
        i.setImageResource(viewImage);

        nowPlaying = extras.getIntExtra("NOW_PLAYING", -1);
        songName = extras.getStringExtra("SONG_NAME");
        play = extras.getBooleanExtra("PLAY", true);

        smallicon.setImageResource(nowPlaying);
        runningsong.setText(songName);
        if (play)
            playpause.setImageResource(R.drawable.play);
        else
            playpause.setImageResource(R.drawable.pause);


        // user tap on the song name in the 2nd activity
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smallicon.setImageResource(viewImage);
                runningsong.setText(songInTheList);
                playpause.setImageResource(R.drawable.pause);

                nowPlaying = viewImage;
                songName = songInTheList;
                play = false;

            }
        });

        // user tap on play or pause button
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play) {
                    play = false;
                    playpause.setImageResource(R.drawable.pause);
                } else {
                    play = true;
                    playpause.setImageResource(R.drawable.play);
                }
            }
            //@@@@@@@@@@@@@@@@@@ 3rd on click listener will be to open 3rd activity + add onActivityResult isme bhee
        });
    }

    public void setTitleBar(int id, TextView txt) {

        String[] title = {"ALBUMS/MAN",
                "ALBUMS/METEORA",
                "ARTISTS/TAYLOR SWIFT",
                "ARTISTS/KATY PERRY",
                "PLAYLIST/GYM",
                "PLAYLIST/PARTY",
                "GENRE/ROCK",
                "GENRE/POP"
        };

        String[] songs = {"Man Woods - Nuclear by Mike",
                "Meteora - Numb by L.P.",
                "Taylor Swift - Blank Space",
                "Katy Perry - Dark Horse",
                "Gym - Bring Em On",
                "Party - Party House L.M.F.A.O",
                "Rock - 7 Nation Army",
                "Pop - Bad Romance"
        };

        int drawableIds[] = {R.drawable.man,
                R.drawable.meteora,
                R.drawable.taylorswift,
                R.drawable.katyperry,
                R.drawable.gym,
                R.drawable.party,
                R.drawable.rock,
                R.drawable.pop
        };

        for (int i = 0; i < 8; i++) {
            if (id == drawableIds[i]) {

                setTitle(title[i]);
                txt.setText(songs[i]);
                songInTheList = songs[i];
                break;
            }
        }
    }

}