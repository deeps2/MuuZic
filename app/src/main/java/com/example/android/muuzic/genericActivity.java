package com.example.android.muuzic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class genericActivity extends AppCompatActivity {

    public boolean play = false;
  //  public boolean saved = false;  -- was using in if else of super.onCreate and LoadPreferences...didn't worked though
              //  private final static String TAG = "TestActivity"; -- Log.<all states> vale code me



    public void SavePreferences(){                                          //not working, not calling
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
                //   editor.putBoolean("state", button.isEnabled());
        editor.commit();   // I missed to save the data to preference here,.
    }

    public void LoadPreferences(){                                          //not working, not calling
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
     //   Boolean  state = sharedPreferences.getBoolean("state", false);
      //  button.setEnabled(state);
    }



    @Override
    public void onBackPressed() {

        if (play == true) {
            Intent manIntent = new Intent(genericActivity.this, MainActivity.class);
            manIntent.putExtra("CURRENT_SONG", "man");
            setResult(RESULT_OK, manIntent);



            //startActivity(manIntent);  -- 1
            //            onPause();
    //        saved = true;

//////1            SavePreferences();
            super.onBackPressed();  //working with only this!!!!!!!!!!!!



            //finish(); -- 2  ..acc to stackoverflow 1,2 should come together @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        } else {
/////1                    SavePreferences();
                    super.onBackPressed();
        }
    }
/*
    @Override
    protected void onStop(){
        super.onStop();
        finish();

    }

                                //@Override
                               // public void onResume(){
                                //    super.onResume();
                                //}

    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
    }

*/



/*          //copied code //
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy .....");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause .....");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "On Restart .....");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume .....");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start .....");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "On Stop .....");
    }
                   // copied code end//
*/



//@Override                                 it is not called when back button is pressed acc to android doc
//public void onSaveInstanceState(Bundle SavedInstanceState) {
//       super.onSaveInstanceState(SavedInstanceState);
//                               //icicle.putLong("param", value);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


//////1                 LoadPreferences();

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_generic);
////x                            Log.i(TAG, "On Create .....");


        Intent extras = getIntent();
        String viewIdentifier = extras.getStringExtra("VIEW_NAME");

                    String nowPlaying = extras.getStringExtra("NOW_PLAYING");

        ImageView i = (ImageView) findViewById(R.id.clickedImage);
        TextView  txt = (TextView) findViewById(R.id.songname);



       // TextView songlist = (TextView)findViewById(R.id.songname);

        final ImageView smallicon = (ImageView)findViewById(R.id.song_smallicon);
        final TextView  runningsong = (TextView)findViewById(R.id.runningsong);
        final ImageView playpause = (ImageView)findViewById(R.id.playpause);

            //final ImageView trial = (ImageView)findViewById(R.id.man);   app crashed, using trial was trying to modify 1st activity

        if (viewIdentifier.equals("man")){// || viewIdentifier.equals("mannuclear")) {
            setTitle("ALBUMS/MAN");
            i.setImageResource(R.drawable.man);
            txt.setText("Man on the Rocks - Nuclear");


           // if(viewIdentifier.equals("mannuclear")){               //TMRW -- here i have to find out which song is getting played in 1st activity. 1st -> 2nd ke intent ka data check karke
            if(nowPlaying != null && nowPlaying.equals("mannuclear")){
               // Log.i("asasd","asd");
                smallicon.setImageResource(R.drawable.man);
                runningsong.setText("Man on the Rocks - Nuclear");
                playpause.setImageResource(R.drawable.pause);
            }


            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smallicon.setImageResource(R.drawable.man);
                    runningsong.setText("Man on the Rocks - Nuclear");
                    playpause.setImageResource(R.drawable.pause);

                    play = true;
                   // trial.setImageResource(R.drawable.pause);      //this crashed...man album jaha hai vaha pause laga raha tha..but crashed



                            //these 2 are working...only genericActivity vala not getContext(Mainactivity vala)
                //        View view2;
                //        LayoutInflater inflater = (LayoutInflater)genericActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //        view2 = inflater.inflate(R.layout.activity_generic, null);
                //        LinearLayout item = (LinearLayout) view2.findViewById(R.id.parent);

                //        View view3;
                //        LayoutInflater inflater = (LayoutInflater)getContext(MainActivity.this).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //        view2 = inflater.inflate(R.layout.activity_generic, null);
                //        LinearLayout item = (LinearLayout) view2.findViewById(R.id.parent);


                               //inflating the whole layout with only this//aisa nai chahiye tha
             //       LinearLayout item = (LinearLayout)findViewById(R.id.inflaterview);
             //       View child = getLayoutInflater().inflate(R.layout.activity_generic,null);
             //       item.addView(child);


                            //1st one not able to make it work//
             //       LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             //       View menuLayout = inflater.inflate(R.layout.currentsong_main,);
             //
             //       //now add menuLayout to wherever you want to add like
             //       (RelativeLayout)findViewById(R.id.relative).addView(menuLayout);
                }
            });   // I was trying to do first for ALBUMS/MAN , but the above 3 lines are not reflecting change when I go back to previous 1st activity or when I AGAIN COME to this 2nd activity from 1st activity after clicking on corresponding image


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