package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;                  // @@@@@@@@@@@@@ remove code from 2nd activity ka file, if working then commit, then experiment memory ka cheeze(see new paper and notepad left), then do for other 7 songs(card reflection jaisa 1st image mike me ho raha hai)

public class MainActivity extends AppCompatActivity { //for NOW READ  BOTH CODES AND SEE BREAKPOINTS RED DOTS, THEN ABOVE ^|^

    public String nowPlaying = "darkhorse";
    public boolean counter = true;

    public ImageView smallicon1;
    public TextView runningsong1;
    public ImageView playpause1;

    public int[] samllicon1_logo = {    R.drawable.man,
                                        R.drawable.meteora,
                                        R.drawable.taylorswift,
                                        R.drawable.katyperry,
                                        R.drawable.gym,
                                        R.drawable.party,
                                        R.drawable.rock,
                                        R.drawable.pop
                                   };

    public String[] runningsong1_text = {   "Man on the Rocks - Nuclear",
                                            "Meteora - Numb",
                                            "Taylor Swift - Blank Space",
                                            "Katy Perry - Dark Horse",
                                            "Gym - Bring Em Out",
                                            "Party - LMFAO(Party Rock)",
                                            "Rock - Seven Nation Army",
                                            "Pop - Lean On"
                                        };


    ImageView man, meteora, taylorswift, katyperry, gym , party, rock, pop ;

    // to catch the intent from 2nd when back is pressed
    public void onActivityResult(int requestCode, int resultCode, Intent extras) {
        super.onActivityResult(requestCode, resultCode, extras);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){

                nowPlaying = extras.getStringExtra("NOW_PLAYING");

                for (int i = 0; i < 8; i++) {

                    if (nowPlaying.equals("mannuclear")) {

                        smallicon1.setImageResource(R.drawable.man);
                        runningsong1.setText("Man on the Rocks - Nuclear");
                        playpause1.setImageResource(R.drawable.pause);            //@@@@@ have to write separate code to check play ya pause

                    } else if (nowPlaying.equals("sdadad")) { //@@@@@@@@@@@@@@@ like that check for other 8 songs

                    } else {

                    }

                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the view for each  albums, artists, playlist, genre

        if(counter) {   //to call findViewByID() only once. although without this counter also it is called only once but when inside a f(x) then repeated. surprising
            man = (ImageView) findViewById(R.id.man);
            meteora = (ImageView) findViewById(R.id.meteora);
            taylorswift = (ImageView) findViewById(R.id.taylorswift);
            katyperry = (ImageView) findViewById(R.id.katyperry);
            gym = (ImageView) findViewById(R.id.gym);
            party = (ImageView) findViewById(R.id.party);
            rock = (ImageView) findViewById(R.id.rock);
            pop = (ImageView) findViewById(R.id.pop);

            smallicon1 = (ImageView) findViewById(R.id.song_smallicon);
            runningsong1 = (TextView) findViewById(R.id.runningsong);
            playpause1 = (ImageView) findViewById(R.id.playpause);

            counter = false;
        }

        //set a click listener on that View
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manIntent = new Intent(MainActivity.this, genericActivity.class);
                manIntent.putExtra("VIEW_NAME", "man");
                manIntent.putExtra("NOW_PLAYING", nowPlaying );

                startActivityForResult(manIntent, 1);
            }
        });

        meteora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent meteoraIntent = new Intent(MainActivity.this, genericActivity.class);
                meteoraIntent.putExtra("VIEW_NAME", "meteora");
                startActivity(meteoraIntent);
            }
        });

        taylorswift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent taylorswiftIntent = new Intent(MainActivity.this, genericActivity.class);
                taylorswiftIntent.putExtra("VIEW_NAME", "taylorswift");
                startActivity(taylorswiftIntent);
            }
        });

        katyperry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent katyperryIntent = new Intent(MainActivity.this, genericActivity.class);
                katyperryIntent.putExtra("VIEW_NAME", "katyperry");
                startActivity(katyperryIntent);
            }
        });

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gymIntent = new Intent(MainActivity.this, genericActivity.class);
                gymIntent.putExtra("VIEW_NAME", "gym");
                startActivity(gymIntent);
            }
        });

        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent partyIntent = new Intent(MainActivity.this, genericActivity.class);
                partyIntent.putExtra("VIEW_NAME", "party");
                startActivity(partyIntent);
            }
        });

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rockIntent = new Intent(MainActivity.this, genericActivity.class);
                rockIntent.putExtra("VIEW_NAME", "rock");
                startActivity(rockIntent);
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popIntent = new Intent(MainActivity.this, genericActivity.class);
                popIntent.putExtra("VIEW_NAME", "pop");
                startActivity(popIntent);
            }
        });

    }

}
