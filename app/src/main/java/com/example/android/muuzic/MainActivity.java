package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int OPEN_SECOND_ACTIVITY = 2;
    static final int OPEN_THIRD_ACTIVITY = 3;

    int nowPlaying = R.drawable.katyperry;
    String songName = "Katy Perry - Dark Horse";
    boolean play = true;

    ImageView smallIcon;
    TextView runningSong;
    ImageView playPause;

    ImageView man, meteora, taylorSwift, katyPerry, gym, party, rock, pop;

    boolean counter = true;
    ScrollView parentScrollView, childScrollView;

    // to catch the intent when back is pressed in 2nd Activity(genericActivity.java)
    public void onActivityResult(int requestCode, int resultCode, Intent extras) {

        super.onActivityResult(requestCode, resultCode, extras);

        //if back was pressed in second activity
        if (requestCode == OPEN_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {

                //get the song details such as song name, image associated with id(its id), being played/paused from the intent
                nowPlaying = extras.getIntExtra("NOW_PLAYING", -1);
                songName = extras.getStringExtra("SONG_NAME");
                play = extras.getBooleanExtra("PLAY", true);

                //set the CardView at the bottom of the activity which displays the current song
                smallIcon.setImageResource(nowPlaying);
                runningSong.setText(songName);
                if (play)
                    playPause.setImageResource(R.drawable.play);
                else
                    playPause.setImageResource(R.drawable.pause);
            }
        }

        //if back was pressed in third activity
        if (requestCode == OPEN_THIRD_ACTIVITY) {
            if (resultCode == RESULT_OK) {

                //get status of play/pause button and set it in the CardView at the bottom of the MainActivity
                play = extras.getBooleanExtra("PLAY", false);
                if (play)
                    playPause.setImageResource(R.drawable.play);
                else
                    playPause.setImageResource(R.drawable.pause);

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
            taylorSwift = (ImageView) findViewById(R.id.taylorswift);
            katyPerry = (ImageView) findViewById(R.id.katyperry);
            gym = (ImageView) findViewById(R.id.gym);
            party = (ImageView) findViewById(R.id.party);
            rock = (ImageView) findViewById(R.id.rock);
            pop = (ImageView) findViewById(R.id.pop);

            smallIcon = (ImageView) findViewById(R.id.song_smallicon);
            runningSong = (TextView) findViewById(R.id.runningsong);
            playPause = (ImageView) findViewById(R.id.playpause);

            parentScrollView = (ScrollView) findViewById(R.id.parent_scroll);
            childScrollView = (ScrollView) findViewById(R.id.child_scroll);

            counter = false;
        }

        //set a click listener on the Views (corresponding to 8 images - 2 each for album, artist, playlist, genre)
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

        taylorSwift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareIntent(R.drawable.taylorswift);
            }
        });

        katyPerry.setOnClickListener(new View.OnClickListener() {
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

        //see whether play/pause button was pressed at the bottom of MainActivity
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

        //see whether user tap on the name of the song at the bottom of MainActivity. If yes, then open the 3rd Activity
        runningSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CurrentSongActivity.class);

                //pass the extra data in intent
                intent.putExtra("ACTIVITY", "first_activity");//name of the activity which will open the 3rd activity
                intent.putExtra("NOW_PLAYING", nowPlaying); //contains viewId of ImageView. Set this Image in 3rd activity
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

    //create intent object and pass extraas to open the second activity
    public void prepareIntent(int id) {

        Intent intent = new Intent(MainActivity.this, genericActivity.class);

        //for ex: id = R.drawable.man. VIEW_IMAGE contains id of the image which is clicked in 1st activity and its enlarged image will be shown in 2nd activity
        intent.putExtra("VIEW_IMAGE", id);

        //these three contains details of song which is currently being played. It will be shown at the bottom of 2nd activity also in a CardView
        intent.putExtra("NOW_PLAYING", nowPlaying);
        intent.putExtra("SONG_NAME", songName);
        intent.putExtra("PLAY", play);

        startActivityForResult(intent, OPEN_SECOND_ACTIVITY);
    }

}