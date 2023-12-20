package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.example.sagesayings.R;

public class SearchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_search_screen);

        View rectBg = findViewById(R.id.search_roundedbg);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        rectBg.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels, (int) (displayMetrics.heightPixels * .97)));
    }
    public void startReadScreen(View view){
        Intent intent = new Intent(SearchScreen.this, ReadScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startAudioScreen(View view){
        Intent intent = new Intent(SearchScreen.this, AudioScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void startVOTScreen(View view){
        Intent intent = new Intent(SearchScreen.this, VerseOfTheDayScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}