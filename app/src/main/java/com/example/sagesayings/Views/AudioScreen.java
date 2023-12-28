package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sagesayings.R;

public class AudioScreen extends AppCompatActivity {

    private static final int[] chapters = {
            R.raw.chapter_one, R.raw.chapter_two, R.raw.chapter_three,
            R.raw.chapter_four, R.raw.chapter_five, R.raw.chapter_six,
            R.raw.chapter_seven, R.raw.chapter_eight, R.raw.chapter_nine,
            R.raw.chapter_ten, R.raw.chapter_eleven, R.raw.chapter_twelve,
            R.raw.chapter_thirteen, R.raw.chapter_fourteen, R.raw.chapter_fifteen,
            R.raw.chapter_sixteen, R.raw.chapter_seventeen, R.raw.chapter_eighteen,
            R.raw.chapter_nineteen, R.raw.chapter_twenty, R.raw.chapter_twenty_one,
            R.raw.chapter_twenty_two, R.raw.chapter_twenty_three, R.raw.chapter_twenty_four,
            R.raw.chapter_twenty_five, R.raw.chapter_twenty_six, R.raw.chapter_twenty_seven,
            R.raw.chapter_twenty_eight, R.raw.chapter_twenty_nine, R.raw.chapter_thirty,
            R.raw.chapter_thirty_one
    };

    private MediaPlayer mediaPlayer;
    private ImageButton toggleButton;
    private TextView currentChap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_audio_screen);
        hideSystemUI();

        View rectBg = findViewById(R.id.audio_roundedbg);
        mediaPlayer = MediaPlayer.create(this, R.raw.chapter_one);
        currentChap = findViewById(R.id.audio_current_chapter);
        toggleButton = findViewById(R.id.audio_play);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        rectBg.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels, (int) (displayMetrics.heightPixels * .97)));
    }

    public void nextChapter(View view){
        if (! (Integer.parseInt( currentChap.getText().toString()) > 30 ) ){
            mediaPlayer.release();
            int chap = Integer.parseInt(currentChap.getText().toString()) + 1;
            updateMediaPlayer(chap);
            currentChap.setText(String.valueOf(chap));
        }
    }

    public void prevChapter(View view){
        if (! (Integer.parseInt( currentChap.getText().toString()) < 2 ) ){
            mediaPlayer.release();
            int chap = Integer.parseInt(currentChap.getText().toString()) - 1;
            updateMediaPlayer(chap);
            currentChap.setText(String.valueOf(chap));
        }
    }

    private void updateMediaPlayer(int chap) {
        mediaPlayer = MediaPlayer.create(this, chapters[chap-1]);
        toggleAudio(null);
    }

    public void toggleAudio(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            toggleButton.setImageResource(R.drawable.play_circle);
        } else {
            mediaPlayer.start();
            toggleButton.setImageResource(R.drawable.pause_circle);
        }

    }

    public void startReadScreen(View view){
        Intent intent = new Intent(AudioScreen.this, ReadScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startVOTScreen(View view){
        Intent intent = new Intent(AudioScreen.this, VerseOfTheDayScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startSearchScreen(View view){
        Intent intent = new Intent(AudioScreen.this, SearchScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            toggleAudio(null);
        }
    }
}