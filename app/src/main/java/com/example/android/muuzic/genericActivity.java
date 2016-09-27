package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class genericActivity extends AppCompatActivity {

    static final int OPEN_THIRD_ACTIVITY = 3;

    ImageView i;     // to display song photo at the top of 2nd activity
    TextView txt;    // song name in the song list

    // these 6 are to deal with current song being played/paused. The CardView associated with this is located at the bottom of 2nd Activity
    ImageView smallIcon;
    TextView runningSong;
    ImageView playPause;
    int nowPlaying = -1;
    String songName = null;
    boolean play = true;

    String songInTheList;

    ScrollView parentScrollView, childScrollView;

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(genericActivity.this, MainActivity.class);

        intent.putExtra("NOW_PLAYING", nowPlaying);
        intent.putExtra("SONG_NAME", songName);
        intent.putExtra("PLAY", play);
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        parentScrollView = (ScrollView) findViewById(R.id.parent_scroll);
        childScrollView = (ScrollView) findViewById(R.id.child_scroll);
        i = (ImageView) findViewById(R.id.clickedImage);
        txt = (TextView) findViewById(R.id.songname);
        smallIcon = (ImageView) findViewById(R.id.song_smallicon);
        runningSong = (TextView) findViewById(R.id.runningsong);
        playPause = (ImageView) findViewById(R.id.playpause);

        //catch the intent from 1st activity
        Intent extras = getIntent();

        final int viewImage = extras.getIntExtra("VIEW_IMAGE", -1);
        setTitleBar(viewImage, txt); // this will set Action Bar and will display the songs available
        i.setImageResource(viewImage);

        nowPlaying = extras.getIntExtra("NOW_PLAYING", -1);
        songName = extras.getStringExtra("SONG_NAME");
        play = extras.getBooleanExtra("PLAY", true);

        smallIcon.setImageResource(nowPlaying);
        runningSong.setText(songName);
        if (play)
            playPause.setImageResource(R.drawable.play);
        else
            playPause.setImageResource(R.drawable.pause);

        // user tap on the song name in the 2nd activity under the available song list
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smallIcon.setImageResource(viewImage);
                runningSong.setText(songInTheList);
                playPause.setImageResource(R.drawable.pause);

                nowPlaying = viewImage;
                songName = songInTheList;
                play = false;

            }
        });

        // user tap on play or pause button
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play) {
                    play = false;
                    playPause.setImageResource(R.drawable.pause);
                } else {
                    play = true;
                    playPause.setImageResource(R.drawable.play);
                }
            }
        });

        //if user clicks on the song name which is displayed in a CardView located at the bottom of the activity
        runningSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(genericActivity.this, CurrentSongActivity.class);

                intent.putExtra("ACTIVITY", "second_activity");
                intent.putExtra("NOW_PLAYING", nowPlaying); //contains viewId of ImageView. Set this view in 3rd activity
                intent.putExtra("SONG_NAME", songName);    //song name will come in action bar at top of 3rd activity
                intent.putExtra("PLAY", play);            //play pause button state

                startActivityForResult(intent, OPEN_THIRD_ACTIVITY);
            }
        });

        parentScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                findViewById(R.id.child_scroll).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        childScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Disallow the touch request for parent scroll on touch of  child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    // this method will set the Action Bar and will display the name of a song in the song list area
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
            if (id == drawableIds[i]) {  //find out which image was clicked in 1st activity

                setTitle(title[i]);      //set the Title, song name corresponding to that clicked image
                txt.setText(songs[i]);
                songInTheList = songs[i];
                break;
            }
        }
    }

    // when back is pressed in 3rd activity, the control will come here
    public void onActivityResult(int requestCode, int resultCode, Intent extras) {

        super.onActivityResult(requestCode, resultCode, extras);

        if (requestCode == OPEN_THIRD_ACTIVITY) {
            if (resultCode == RESULT_OK) {

                play = extras.getBooleanExtra("PLAY", false);
                if (play)
                    playPause.setImageResource(R.drawable.play);
                else
                    playPause.setImageResource(R.drawable.pause);

            }
        }
    }

}