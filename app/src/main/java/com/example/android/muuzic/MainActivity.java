package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

// @@@@@@@@@@@@@
// implement loop, if else, switch in both java files
// implement onClickListener case wise see chrome tab
// then experiment memory related stuffs (see new paper and notepad left)

public class MainActivity extends AppCompatActivity {

    int nowPlaying = R.drawable.katyperry;
    String songName = "Katy Perry - Dark Horse";
    boolean play = true;

    ImageView smallIcon;
    TextView runningSong;
    ImageView playPause;

    ImageView man, meteora, taylorswift, katyperry, gym, party, rock, pop;

    boolean counter = true;
    ScrollView parentScrollView, childScrollView;

    static final int OPEN_SECOND_ACTIVITY = 1;
    static final int OPEN_THIRD_ACTIVITY = 2;

    // to catch the intent when back is pressed in 2nd Activity(genericActivity.java)

    public void onActivityResult(int requestCode, int resultCode, Intent extras) {

        super.onActivityResult(requestCode, resultCode, extras);

        if (requestCode == OPEN_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {

                nowPlaying = extras.getIntExtra("NOW_PLAYING", -1);
                songName = extras.getStringExtra("SONG_NAME");
                play = extras.getBooleanExtra("PLAY", true);

                smallIcon.setImageResource(nowPlaying);
                runningSong.setText(songName);
                if (play)
                    playPause.setImageResource(R.drawable.play);
                else
                    playPause.setImageResource(R.drawable.pause);
            }
        }

        if(resultCode == OPEN_THIRD_ACTIVITY){
            if (resultCode == RESULT_OK) {

                Log.i("hello","asdsad");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the view for each  albums, artists, playlist, genre
        if (counter) {   //to call findViewByID() only once.
            man = (ImageView) findViewById(R.id.man);
            meteora = (ImageView) findViewById(R.id.meteora);
            taylorswift = (ImageView) findViewById(R.id.taylorswift);
            katyperry = (ImageView) findViewById(R.id.katyperry);
            gym = (ImageView) findViewById(R.id.gym);
            party = (ImageView) findViewById(R.id.party);
            rock = (ImageView) findViewById(R.id.rock);
            pop = (ImageView) findViewById(R.id.pop);

            smallIcon = (ImageView) findViewById(R.id.song_smallicon);
            runningSong = (TextView) findViewById(R.id.runningsong);
            playPause = (ImageView) findViewById(R.id.playpause);

            counter = false;
            parentScrollView = (ScrollView)findViewById(R.id.parent_scroll);
            childScrollView = (ScrollView)findViewById(R.id.child_scroll);
        }

        //set a click listener on the Views
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.man);
            }
        });

        meteora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.meteora);
            }
        });

        taylorswift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.taylorswift);
            }
        });

        katyperry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.katyperry);
            }
        });

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.gym);
            }
        });

        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.party);
            }
        });

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.rock);
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.pop);
            }
        });

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

        //@@@@@@@@@@@@@ ek on click listener yaha bhee lagega as 3rd activity yaha se bhee to open ho sakti hai na
        runningSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CurrentSongActivity.class);

                intent.putExtra("NOW_PLAYING", nowPlaying);
                intent.putExtra("SONG_NAME", songName);
                intent.putExtra("PLAY", play);

                startActivityForResult(intent, OPEN_THIRD_ACTIVITY);
            }
        });

        parentScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                findViewById(R.id.child_scroll).getParent()
                        .requestDisallowInterceptTouchEvent(false);
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

    public void prepareIntent(int id) {

        Intent intent = new Intent(MainActivity.this, genericActivity.class);

        intent.putExtra("VIEW_IMAGE", id);   //id = R.drawable.man. VIEW_IMAGE contains id of the image which is clicked in 1st activity and its enlarged image will be shown in 2nd activity

        intent.putExtra("NOW_PLAYING", nowPlaying);
        intent.putExtra("SONG_NAME", songName);
        intent.putExtra("PLAY", play);

        startActivityForResult(intent, OPEN_SECOND_ACTIVITY);
    }

}