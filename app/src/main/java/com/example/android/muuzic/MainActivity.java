package com.example.android.muuzic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String viewIdentifier = null;//"kakas";   with null it was failing first, now passing with ( 1 && 2)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //    Intent extras = getIntent();                 //when trying to get intent from 2nd activity, crashing, then used onActivityResult(). see bottom
    //    String viewIdentifier = "qwe123";
    //    if(extras != null) {
    //        viewIdentifier = extras.getStringExtra("CURRENT_SONG");
    //        if (viewIdentifier.equals("man")) {

    //            ImageView smallicon1 = (ImageView) findViewById(R.id.song_smallicon);
    //            TextView runningsong1 = (TextView) findViewById(R.id.runningsong);
    //            ImageView playpause1 = (ImageView) findViewById(R.id.playpause);

     //           smallicon1.setImageResource(R.drawable.man);
     //           runningsong1.setText("Man on the Rocks - Nuclear");
     //           playpause1.setImageResource(R.drawable.pause);
     //       }
     //   }



        //find the view for each  albums, artists, playlist, genre
        ImageView man = (ImageView) findViewById(R.id.man);
        ImageView meteora = (ImageView) findViewById(R.id.meteora);
        final ImageView taylorswift = (ImageView) findViewById(R.id.taylorswift);
        ImageView katyperry = (ImageView) findViewById(R.id.katyperry);
        ImageView gym = (ImageView) findViewById(R.id.gym);
        ImageView party = (ImageView) findViewById(R.id.party);
        ImageView rock = (ImageView) findViewById(R.id.rock);
        ImageView pop = (ImageView) findViewById(R.id.pop);

        //set a click listener on that View
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manIntent = new Intent(MainActivity.this, genericActivity.class);
                manIntent.putExtra("VIEW_NAME", "man");

    //            Log.i("MyActivity", "1");

   //             if (viewIdentifier != null) {
    //                Log.i("MyActivity", "2");
                    if (viewIdentifier != null && viewIdentifier.equals("man")) {  // tmrw here i have to find out which song is being played in Act1. see bottom also for help//
     //                   Log.i("MyActivity", "3");
                        manIntent.putExtra("NOW_PLAYING", "mannuclear");
                    }
       //         }

                startActivityForResult(manIntent, 1); // this is different
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


    // to get the intent from 2nd to 1st Activity

    public void onActivityResult(int requestCode, int resultCode, Intent extras) {
        super.onActivityResult(requestCode, resultCode, extras);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                // String stredittext=data.getStringExtra("edittextvalue");

                viewIdentifier = extras.getStringExtra("CURRENT_SONG");
                if (viewIdentifier.equals("man")) {

                    ImageView smallicon1 = (ImageView) findViewById(R.id.song_smallicon);
                    TextView runningsong1 = (TextView) findViewById(R.id.runningsong);
                    ImageView playpause1 = (ImageView) findViewById(R.id.playpause);

                    smallicon1.setImageResource(R.drawable.man);
                    runningsong1.setText("Man on the Rocks - Nuclear");
                    playpause1.setImageResource(R.drawable.pause);


                }

            }
        }
    }
}
