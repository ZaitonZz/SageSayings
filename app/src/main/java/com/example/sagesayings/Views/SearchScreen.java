package com.example.sagesayings.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.sagesayings.AVLImplementation.AVLChapterTree;
import com.example.sagesayings.AVLImplementation.AVLVerseTree;
import com.example.sagesayings.AVLImplementation.ChapterNode;
import com.example.sagesayings.AVLImplementation.VerseNode;
import com.example.sagesayings.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SearchScreen extends AppCompatActivity {
    private String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_search_screen);

        hideSystemUI();

        View rectBg = findViewById(R.id.search_roundedbg);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.nav_searchbar);

        rectBg.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels, (int) (displayMetrics.heightPixels * .97)));

        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Perform your search operation here
                    query = v.getText().toString();
                    startResultsScreen();
                    return true;  // Return true to indicate you have handled the action
                }
                return false;  // Return false to let the system handle the action
            }
        });
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

    public void startResultsScreen(){
        Intent intent = new Intent(SearchScreen.this, ResultsScreen.class);
        intent.putExtra("search", query);
        startActivity(intent);
    }

    public void searchLove(View view){
        query = "love";
        startResultsScreen();
    }
    public void searchWisdom(View view){
        query = "wisdom";
        startResultsScreen();
    }
    public void searchDiligent(View view){
        query = "diligent";
        startResultsScreen();
    }
    public void searchFool(View view){
        query = "fool";
        startResultsScreen();
    }
    public void searchSlothful(View view){
        query = "sloth";
        startResultsScreen();
    }
    public void searchLife(View view){
        query = "life";
        startResultsScreen();
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }
}